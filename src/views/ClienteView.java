package views;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controller.ExperienciaController;
import controller.QuartoController;
import models.Experiencia;
import models.Guia;
import models.Quarto;

/**
 * View for client operations
 */
public class ClienteView {
    private Scanner scanner;
    private QuartoController quartoController;
    private ExperienciaController experienciaController;
    
    /**
     * Constructor
     * @param quartoController Controller for quarto operations
     * @param experienciaController Controller for experiencia operations
     */
    public ClienteView(QuartoController quartoController, ExperienciaController experienciaController) {
        this.scanner = new Scanner(System.in);
        this.quartoController = quartoController;
        this.experienciaController = experienciaController;
    }
    
    /**
     * Show client menu
     */
    public void showMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n==================================");
            System.out.println("  CESAE RESORT - MENU CLIENTE");
            System.out.println("==================================");
            System.out.println("1. Consultar Quartos Disponíveis");
            System.out.println("2. Consultar Experiências Disponíveis");
            System.out.println("3. Consultar Experiência Favorita");
            System.out.println("4. Consultar Experiência Top-Seller");
            System.out.println("5. Avaliar uma Experiência");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    consultarQuartosDisponiveis();
                    break;
                case 2:
                    consultarExperienciasDisponiveis();
                    break;
                case 3:
                    consultarExperienciaFavorita();
                    break;
                case 4:
                    consultarExperienciaTopSeller();
                    break;
                case 5:
                    avaliarExperiencia();
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
     * Show available rooms
     */
    private void consultarQuartosDisponiveis() {
        System.out.println("\n==================================");
        System.out.println("  QUARTOS DISPONÍVEIS");
        System.out.println("==================================");
        
        // Actual year is 2025, month is 7 (July), week is 1
        List<Quarto> quartosDisponiveis = quartoController.getQuartosDisponiveis(2025, 7, 1);
        
        if (quartosDisponiveis.isEmpty()) {
            System.out.println("Não existem quartos disponíveis.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-15s%n", "Número", "Tipologia", "Preço/Semana");
        System.out.println("--------------------------------------------------");
        
        for (Quarto quarto : quartosDisponiveis) {
            String tipologiaDesc = quarto.getTipologia() != null ? quarto.getTipologia().getDescricao() : "N/A";
            System.out.printf("%-10s %-20s €%-15.2f%n", quarto.getNumQuarto(), tipologiaDesc, quarto.getPrecoPorSemana());
        }
    }
    
    /**
     * Show available experiences
     */
    private void consultarExperienciasDisponiveis() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIAS DISPONÍVEIS");
        System.out.println("==================================");
        
        List<Experiencia> experiencias = experienciaController.getAllExperiencias();
        
        if (experiencias.isEmpty()) {
            System.out.println("Não existem experiências disponíveis.");
            return;
        }
        
        System.out.printf("%-5s %-20s %-10s %-15s %-10s %-12s %-12s%n", 
                "ID", "Nome", "Rating Exp", "Guia", "Rating Guia", "Preço Adulto", "Preço Criança");
        System.out.println("---------------------------------------------------------------------------------");
        
        for (Experiencia exp : experiencias) {
            String guiaNome = exp.getGuia() != null ? exp.getGuia().getNome() : "N/A";
            double guiaRating = exp.getGuia() != null ? exp.getGuia().getRatingGuia() : 0;
            
            System.out.printf("%-5s %-20s %-10.1f %-15s %-10.1f €%-12.2f €%-12.2f%n", 
                    exp.getId(), exp.getNome(), exp.getRatingExperiencia(), guiaNome, guiaRating, 
                    exp.getPrecoAdulto(), exp.getPrecoCrianca());
        }
    }
    
    /**
     * Show favorite experience
     */
    private void consultarExperienciaFavorita() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA FAVORITA");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getExperienciaFavorita();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        String guiaNome = exp.getGuia() != null ? exp.getGuia().getNome() : "N/A";
        double guiaRating = exp.getGuia() != null ? exp.getGuia().getRatingGuia() : 0;
        
        System.out.printf("%-5s %-20s %-10s %-15s %-10s %-12s %-12s%n", 
                "ID", "Nome", "Rating Exp", "Guia", "Rating Guia", "Preço Adulto", "Preço Criança");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10.1f %-15s %-10.1f €%-12.2f €%-12.2f%n", 
                exp.getId(), exp.getNome(), exp.getRatingExperiencia(), guiaNome, guiaRating, 
                exp.getPrecoAdulto(), exp.getPrecoCrianca());
    }
    
    /**
     * Show top-seller experience
     */
    private void consultarExperienciaTopSeller() {
        System.out.println("\n==================================");
        System.out.println("  EXPERIÊNCIA TOP-SELLER");
        System.out.println("==================================");
        
        Optional<Experiencia> expOpt = experienciaController.getExperienciaTopSeller();
        
        if (!expOpt.isPresent()) {
            System.out.println("Não existem vendas de experiências.");
            return;
        }
        
        Experiencia exp = expOpt.get();
        String guiaNome = exp.getGuia() != null ? exp.getGuia().getNome() : "N/A";
        double guiaRating = exp.getGuia() != null ? exp.getGuia().getRatingGuia() : 0;
        
        System.out.printf("%-5s %-20s %-10s %-15s %-10s %-12s %-12s%n", 
                "ID", "Nome", "Rating Exp", "Guia", "Rating Guia", "Preço Adulto", "Preço Criança");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10.1f %-15s %-10.1f €%-12.2f €%-12.2f%n", 
                exp.getId(), exp.getNome(), exp.getRatingExperiencia(), guiaNome, guiaRating, 
                exp.getPrecoAdulto(), exp.getPrecoCrianca());
    }
    
    /**
     * Rate an experience
     */
    private void avaliarExperiencia() {
        System.out.println("\n==================================");
        System.out.println("  AVALIAR UMA EXPERIÊNCIA");
        System.out.println("==================================");
        
        List<Experiencia> experiencias = experienciaController.getAllExperiencias();
        
        if (experiencias.isEmpty()) {
            System.out.println("Não existem experiências disponíveis para avaliar.");
            return;
        }
        
        System.out.println("Experiências disponíveis:");
        System.out.printf("%-5s %-20s %-10s %-15s%n", "ID", "Nome", "Rating Exp", "Guia");
        System.out.println("-----------------------------------------------------");
        
        for (Experiencia exp : experiencias) {
            String guiaNome = exp.getGuia() != null ? exp.getGuia().getNome() : "N/A";
            System.out.printf("%-5s %-20s %-10.1f %-15s%n", exp.getId(), exp.getNome(), exp.getRatingExperiencia(), guiaNome);
        }
        
        System.out.print("\nID da experiência a avaliar: ");
        String expId = scanner.nextLine();
        
        Optional<Experiencia> expOpt = experienciaController.getExperienciaById(expId);
        
        if (!expOpt.isPresent()) {
            System.out.println("Experiência não encontrada!");
            return;
        }
        
        Experiencia exp = expOpt.get();
        Guia guia = exp.getGuia();
        
        System.out.println("\nAvaliando: " + exp.getNome());
        System.out.println("Guia: " + (guia != null ? guia.getNome() : "N/A"));
        
        System.out.print("Rating da experiência (0-5): ");
        double ratingExp = scanner.nextDouble();
        
        System.out.print("Rating do guia (0-5): ");
        double ratingGuia = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        // Generate a simple ID for the rating
        String ratingId = "R" + System.currentTimeMillis();
        
        boolean success = experienciaController.addRating(ratingId, expId, ratingExp, ratingGuia);
        
        if (success) {
            System.out.println("Avaliação registada com sucesso!");
        } else {
            System.out.println("Erro ao registar avaliação.");
        }
    }
}
