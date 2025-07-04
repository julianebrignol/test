package views;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import controller.ClienteController;
import controller.ExperienciaController;
import controller.QuartoController;
import models.Cliente;
import models.Quarto;
import models.ReservaQuarto;

/**
 * View for reception operations
 */
public class GestaoView {
    private Scanner scanner;
    private QuartoController quartoController;
    private ClienteController clienteController;
    private ExperienciaController experienciaController;
    
    /**
     * Constructor
     * @param quartoController Controller for quarto operations
     * @param clienteController Controller for cliente operations
     * @param experienciaController Controller for experiencia operations
     */
    public GestaoView(QuartoController quartoController, ClienteController clienteController,
            ExperienciaController experienciaController) {
        this.scanner = new Scanner(System.in);
        this.quartoController = quartoController;
        this.clienteController = clienteController;
        this.experienciaController = experienciaController;
    }
    
    /**
     * Show reception menu
     */
    public void showMenu() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n==================================");
            System.out.println("  CESAE RESORT - MENU RECEÇÃO");
            System.out.println("==================================");
            System.out.println("1. Consultar Quartos Disponíveis (Semana Atual)");
            System.out.println("2. Consultar Quartos Reservados");
            System.out.println("3. Consultar Reservas Atuais (Semana Atual)");
            System.out.println("4. Efetuar uma Reserva");
            System.out.println("5. Reservar uma Experiência");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    consultarQuartosDisponiveis();
                    break;
                case 2:
                    consultarQuartosReservados();
                    break;
                case 3:
                    consultarReservasAtuais();
                    break;
                case 4:
                    efetuarReserva();
                    break;
                case 5:
                    reservarExperiencia();
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
     * Show available rooms for the current week
     */
    private void consultarQuartosDisponiveis() {
        System.out.println("\n==================================");
        System.out.println("  QUARTOS DISPONÍVEIS (SEMANA ATUAL)");
        System.out.println("==================================");
        
        // Actual year is 2025, month is 7 (July), week is 1
        List<Quarto> quartosDisponiveis = quartoController.getQuartosDisponiveis(2025, 7, 1);
        
        if (quartosDisponiveis.isEmpty()) {
            System.out.println("Não existem quartos disponíveis para a semana atual.");
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
     * Show reserved rooms
     */
    private void consultarQuartosReservados() {
        System.out.println("\n==================================");
        System.out.println("  QUARTOS RESERVADOS");
        System.out.println("==================================");
        
        List<ReservaQuarto> reservas = quartoController.getReservasAtuais(2025, 7, 1);
        
        if (reservas.isEmpty()) {
            System.out.println("Não existem quartos reservados.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-10s %-20s %-25s %-15s %-6s %-6s %-6s%n",
                "Número", "Tipologia", "ID Cliente", "Nome", "Email", "Nacionalidade", "Ano", "Mês", "Semana");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (ReservaQuarto reserva : reservas) {
            String numQuarto = reserva.getQuarto() != null ? reserva.getQuarto().getNumQuarto() : "N/A";
            String tipologia = reserva.getQuarto() != null && reserva.getQuarto().getTipologia() != null ? 
                    reserva.getQuarto().getTipologia().getDescricao() : "N/A";
            String clienteId = reserva.getClienteId();
            String clienteNome = reserva.getCliente() != null ? reserva.getCliente().getNome() : "N/A";
            String clienteEmail = reserva.getCliente() != null ? reserva.getCliente().getEmail() : "N/A";
            String clienteNacionalidade = reserva.getCliente() != null ? reserva.getCliente().getNacionalidade() : "N/A";
            
            System.out.printf("%-10s %-20s %-10s %-20s %-25s %-15s %-6d %-6d %-6d%n",
                    numQuarto, tipologia, clienteId, clienteNome, clienteEmail, clienteNacionalidade,
                    reserva.getAno(), reserva.getMes(), reserva.getSemana());
        }
    }
    
    /**
     * Show current reservations (for the current week)
     */
    private void consultarReservasAtuais() {
        System.out.println("\n==================================");
        System.out.println("  RESERVAS ATUAIS (SEMANA ATUAL)");
        System.out.println("==================================");
        
        // Actual year is 2025, month is 7 (July), week is 1
        List<ReservaQuarto> reservas = quartoController.getReservasAtuais(2025, 7, 1);
        
        if (reservas.isEmpty()) {
            System.out.println("Não existem reservas para a semana atual.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-10s %-20s %-25s %-15s %-6s %-6s %-6s%n",
                "Número", "Tipologia", "ID Cliente", "Nome", "Email", "Nacionalidade", "Ano", "Mês", "Semana");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (ReservaQuarto reserva : reservas) {
            String numQuarto = reserva.getQuarto() != null ? reserva.getQuarto().getNumQuarto() : "N/A";
            String tipologia = reserva.getQuarto() != null && reserva.getQuarto().getTipologia() != null ? 
                    reserva.getQuarto().getTipologia().getDescricao() : "N/A";
            String clienteId = reserva.getClienteId();
            String clienteNome = reserva.getCliente() != null ? reserva.getCliente().getNome() : "N/A";
            String clienteEmail = reserva.getCliente() != null ? reserva.getCliente().getEmail() : "N/A";
            String clienteNacionalidade = reserva.getCliente() != null ? reserva.getCliente().getNacionalidade() : "N/A";
            
            System.out.printf("%-10s %-20s %-10s %-20s %-25s %-15s %-6d %-6d %-6d%n",
                    numQuarto, tipologia, clienteId, clienteNome, clienteEmail, clienteNacionalidade,
                    reserva.getAno(), reserva.getMes(), reserva.getSemana());
        }
    }
    
    /**
     * Make a reservation
     */
    private void efetuarReserva() {
        System.out.println("\n==================================");
        System.out.println("  EFETUAR UMA RESERVA");
        System.out.println("==================================");
        
        System.out.print("ID do cliente: ");
        String clienteId = scanner.nextLine();
        
        Optional<Cliente> clienteOpt = clienteController.getClienteById(clienteId);
        
        if (!clienteOpt.isPresent()) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        System.out.print("Número do quarto: ");
        String numQuarto = scanner.nextLine();
        
        Optional<Quarto> quartoOpt = quartoController.getAllQuartos().stream()
                .filter(q -> q.getNumQuarto().equals(numQuarto))
                .findFirst();
        
        if (!quartoOpt.isPresent()) {
            System.out.println("Quarto não encontrado!");
            return;
        }
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        
        System.out.print("Semana: ");
        int semana = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean isAvailable = quartoController.isQuartoAvailable(numQuarto, ano, mes, semana);
        
        if (!isAvailable) {
            System.out.println("O quarto não está disponível para a data especificada.");
            return;
        }
        
        // Generate a simple ID for the reservation
        String reservaId = "R" + System.currentTimeMillis();
        
        boolean success = quartoController.addReserva(reservaId, clienteId, numQuarto, ano, mes, semana);
        
        if (success) {
            System.out.println("Reserva efetuada com sucesso!");
            System.out.println("ID da reserva: " + reservaId);
        } else {
            System.out.println("Erro ao efetuar reserva.");
        }
    }
    
    /**
     * Book an experience
     */
    private void reservarExperiencia() {
        System.out.println("\n==================================");
        System.out.println("  RESERVAR UMA EXPERIÊNCIA");
        System.out.println("==================================");
        
        System.out.print("ID do cliente: ");
        String clienteId = scanner.nextLine();
        
        Optional<Cliente> clienteOpt = clienteController.getClienteById(clienteId);
        
        if (!clienteOpt.isPresent()) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        System.out.print("ID da reserva: ");
        String reservaId = scanner.nextLine();
        
        System.out.print("ID da experiência: ");
        String experienciaId = scanner.nextLine();
        
        if (experienciaController.getExperienciaById(experienciaId).isEmpty()) {
            System.out.println("Experiência não encontrada!");
            return;
        }
        
        System.out.print("Número de adultos: ");
        int numAdultos = scanner.nextInt();
        
        System.out.print("Número de crianças: ");
        int numCriancas = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Generate a simple ID for the venda
        String vendaId = "V" + System.currentTimeMillis();
        
        boolean success = experienciaController.addVenda(vendaId, clienteId, experienciaId, numAdultos, numCriancas);
        
        if (success) {
            System.out.println("Experiência reservada com sucesso!");
            System.out.println("ID da venda: " + vendaId);
        } else {
            System.out.println("Erro ao reservar experiência.");
        }
    }
}
