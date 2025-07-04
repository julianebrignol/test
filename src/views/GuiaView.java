package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import controller.ExperienciaController;
import models.Experiencia;
import models.Guia;
import models.User;
import models.VendaExperiencia;

/**
 * View for guide operations
 */
public class GuiaView {
    private Scanner scanner;
    private ExperienciaController experienciaController;
    private User currentUser;
    
    /**
     * Constructor
     * @param experienciaController Controller for experiencia operations
     */
    public GuiaView(ExperienciaController experienciaController) {
        this.scanner = new Scanner(System.in);
        this.experienciaController = experienciaController;
    }
    
    /**
     * Set the current user
     * @param user User currently logged in
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    /**
     * Show guide menu
     */
    public void showMenu() {
        if (currentUser == null || currentUser.getRole() != User.UserRole.GUIA) {
            System.out.println("Acesso não autorizado!");
            return;
        }
        
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n==================================");
            System.out.println("  CESAE RESORT - MENU GUIA");
            System.out.println("==================================");
            System.out.println("1. Consultar Histórico de Experiências");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    consultarHistoricoExperiencias();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    
    /**
     * Show history of experiences for the guide
     */
    private void consultarHistoricoExperiencias() {
        System.out.println("\n==================================");
        System.out.println("  HISTÓRICO DE EXPERIÊNCIAS");
        System.out.println("==================================");
        
        // Find the guide by the user ID
        // In a real implementation, we would have a proper mapping between users and guides
        String guiaId = currentUser.getId();
        
        // Get experiences for this guide
        List<Experiencia> experiencias = experienciaController.getExperienciasByGuia(guiaId);
        
        if (experiencias.isEmpty()) {
            System.out.println("Não existem experiências associadas a este guia.");
            return;
        }
        
        // For each experience, get vendas
        List<VendaExperiencia> allVendas = new ArrayList<>();
        for (Experiencia experiencia : experiencias) {
            allVendas.addAll(experienciaController.getVendasByExperiencia(experiencia.getId()));
        }
        
        // Group vendas by experiencia
        Map<String, List<VendaExperiencia>> vendasByExperiencia = allVendas.stream()
                .collect(Collectors.groupingBy(VendaExperiencia::getExperienciaId));
        
        // Print the results
        System.out.printf("%-5s %-20s %-15s %-15s %-15s%n", 
                "ID", "Nome", "Total Adultos", "Total Crianças", "Valor Total");
        System.out.println("----------------------------------------------------------------------");
        
        double valorTotal = 0;
        int totalAdultos = 0;
        int totalCriancas = 0;
        
        for (Experiencia experiencia : experiencias) {
            List<VendaExperiencia> vendas = vendasByExperiencia.getOrDefault(experiencia.getId(), new ArrayList<>());
            
            int adultos = vendas.stream().mapToInt(VendaExperiencia::getNumAdultos).sum();
            int criancas = vendas.stream().mapToInt(VendaExperiencia::getNumCriancas).sum();
            double valor = vendas.stream().mapToDouble(VendaExperiencia::calcularValorTotal).sum();
            
            totalAdultos += adultos;
            totalCriancas += criancas;
            valorTotal += valor;
            
            System.out.printf("%-5s %-20s %-15d %-15d €%-15.2f%n", 
                    experiencia.getId(), experiencia.getNome(), adultos, criancas, valor);
        }
        
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-26s %-15d %-15d €%-15.2f%n", 
                "TOTAL", totalAdultos, totalCriancas, valorTotal);
    }
}
