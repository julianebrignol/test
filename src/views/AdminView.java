package views;

import java.util.Optional;
import java.util.Scanner;

import controller.AdminController;
import controller.AuthController;
import controller.ExperienciaController;
import controller.QuartoController;
import models.Experiencia;
import models.Quarto;
import models.Tipologia;
import models.User;

/**
 * View for admin operations
 */
public class AdminView {
    private Scanner scanner;
    private AdminController adminController;
    private QuartoController quartoController;
    private ExperienciaController experienciaController;
    private AuthController authController;
    
    /**
     * Constructor
     * @param adminController Controller for admin operations
     * @param quartoController Controller for quarto operations
     * @param experienciaController Controller for experiencia operations
     * @param authController Controller for authentication operations
     */
    public AdminView(AdminController adminController, QuartoController quartoController,
            ExperienciaController experienciaController, AuthController authController) {
        this.scanner = new Scanner(System.in);
        this.adminController = adminController;
        this.quartoController = quartoController;
        this.experienciaController = experienciaController;
        this.authController = authController;
    }
    
    /**
     * Show admin menu
     */
    public void showMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n==================================");
            System.out.println("  CESAE RESORT - MENU ADMIN");
            System.out.println("==================================");
            System.out.println("1. Consultar Total de Reservas");
            System.out.println("2. Consultar Total de Receitas");
            System.out.println("3. Consultar Reservas/Receitas Mensais");
            System.out.println("4. Consultar Tipologia de Quarto Mais Reservada");
            System.out.println("5. Consultar Experiência Mais Procurada (Adultos)");
            System.out.println("6. Consultar Experiência Mais Procurada (Crianças)");
            System.out.println("7. Consultar Experiência Mais Lucrativa");
            System.out.println("8. Consultar Experiência Menos Lucrativa");
            System.out.println("9. Consultar Quarto com Melhor Preço/Semana");
            System.out.println("10. Adicionar Novo Login");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    consultarTotalReservas();
                    break;
                case 2:
                    consultarTotalReceitas();
                    break;
                case 3:
                    consultarReservasReceitasMensais();
                    break;
                case 4:
                    consultarTipologiaMaisReservada();
                    break;
                case 5:
                    consultarExperienciaMaisProcuradaAdultos();
                    break;
                case 6:
                    consultarExperienciaMaisProcuradaCriancas();
                    break;
                case 7:
                    consultarExperienciaMaisLucrativa();
                    break;
                case 8:
                    consultarExperienciaMenosLucrativa();
                    break;
                case 9:
                    consultarQuartoMelhorPreco();
                    break;
                case 10:
                    adicionarNovoLogin();
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
     * Show total reservations
     */
    private void consultarTotalReservas() {
        System.out.println("\n==================================");
        System.out.println("  TOTAL DE RESERVAS");
        System.out.println("==================================");
        
        int totalReservas = adminController.getTotalReservas();
        System.out.println("Total de reservas: " + totalReservas);
    }
    
    /**
     * Show total revenue
     */
    private void consultarTotalReceitas() {
        System.out.println("\n==================================");
        System.out.println("  TOTAL DE RECEITAS");
        System.out.println("==================================");
        
        double totalReceitas = adminController.getTotalReceitas();
        System.out.printf("Total de receitas: €%.2f%n", totalReceitas);
    }
    
    /**
     * Show monthly reservations and revenue
     */
    private void consultarReservasReceitasMensais() {
        System.out.println("\n==================================");
        System.out.println("  RESERVAS/RECEITAS MENSAIS");
        System.out.println("==================================");
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        int totalReservas = adminController.getTotalReservasByMonth(ano, mes);
        double totalReceitas = adminController.getTotalReceitasByMonth(ano, mes);
        
        System.out.println("Total de reservas: " + totalReservas);
        System.out.printf("Total de receitas: €%.2f%n", totalReceitas);
    }
    
    /**
     * Show most reserved room typology
     */
    private void consultarTipologiaMaisReservada() {
        System.out.println("\n==================================");
        System.out.println("  TIPOLOGIA DE QUARTO MAIS RESERVADA");
        System.out.println("==================================");
        
        Optional<Tipologia> tipologiaOpt = quartoController.getTipologiaMaisReservada();
        
        if (!tipologiaOpt.isPresent()) {
            System.out.println("Não existem reservas.");
            return;
        }
        
        Tipologia tipologia = tipologiaOpt.get();
        System.out.println("Tipologia mais reservada: " + tipologia.getDescricao());
        System.out.println("Capacidade: " + tipologia.getCapacidade() + " pessoas");
    }
    
    /**
     * Show most popular experience for adults
     */
    private void consultarExperienciaMaisProcuradaAdultos() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA MAIS PROCURADA (ADULTOS)");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getExperienciaMostAdults();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem vendas de experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        System.out.println("Experiência mais procurada por adultos: " + exp.getNome());
        System.out.printf("Preço por adulto: €%.2f%n", exp.getPrecoAdulto());
    }
    
    /**
     * Show most popular experience for children
     */
    private void consultarExperienciaMaisProcuradaCriancas() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA MAIS PROCURADA (CRIANÇAS)");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getExperienciaMostChildren();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem vendas de experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        System.out.println("Experiência mais procurada por crianças: " + exp.getNome());
        System.out.printf("Preço por criança: €%.2f%n", exp.getPrecoCrianca());
    }
    
    /**
     * Show most profitable experience
     */
    private void consultarExperienciaMaisLucrativa() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA MAIS LUCRATIVA");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getMostProfitableExperiencia();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem vendas de experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        System.out.println("Experiência mais lucrativa: " + exp.getNome());
    }
    
    /**
     * Show least profitable experience
     */
    private void consultarExperienciaMenosLucrativa() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA MENOS LUCRATIVA");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getLeastProfitableExperiencia();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem vendas de experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        System.out.println("Experiência menos lucrativa: " + exp.getNome());
    }
    
    /**
     * Show room with best price per week
     */
    private void consultarQuartoMelhorPreco() {
        System.out.println("\n==================================");
        System.out.println("  QUARTO COM MELHOR PREÇO/SEMANA");
        System.out.println("==================================");
        
        Optional<Quarto> quartoOpt = quartoController.getQuartoMelhorPreco();
        
        if (!quartoOpt.isPresent()) {
            System.out.println("Não existem quartos.");
            return;
        }
        
        Quarto quarto = quartoOpt.get();
        String tipologiaDesc = quarto.getTipologia() != null ? quarto.getTipologia().getDescricao() : "N/A";
        
        System.out.println("Quarto com melhor preço/semana:");
        System.out.println("Número: " + quarto.getNumQuarto());
        System.out.println("Tipologia: " + tipologiaDesc);
        System.out.printf("Preço/semana: €%.2f%n", quarto.getPrecoPorSemana());
    }
    
    /**
     * Add a new login
     */
    private void adicionarNovoLogin() {
        System.out.println("\n==================================");
        System.out.println("  ADICIONAR NOVO LOGIN");
        System.out.println("==================================");
        
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.println("Perfil:");
        System.out.println("1. ADMIN");
        System.out.println("2. GESTAO");
        System.out.println("3. GUIA");
        System.out.print("Escolha uma opção: ");
        
        int roleOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        User.UserRole role;
        switch (roleOption) {
            case 1:
                role = User.UserRole.ADMIN;
                break;
            case 2:
                role = User.UserRole.GESTAO;
                break;
            case 3:
                role = User.UserRole.GUIA;
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        
        boolean success = authController.addUser(id, username, password, role);
        
        if (success) {
            System.out.println("Login adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar login.");
        }
    }
}
