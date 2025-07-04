package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Cliente;
import models.Quarto;
import models.ReservaQuarto;
import tools.CSVFileReader;

/**
 * Repository for ReservaQuarto data
 */
public class ReservaQuartoRepository {
    private List<ReservaQuarto> reservas;
    private ClienteRepository clienteRepository;
    private QuartoRepository quartoRepository;
    private static final String CSV_FILE_PATH = "src/files/reservas_quartos.csv";
    
    /**
     * Constructor
     * @param clienteRepository Repository for Cliente data
     * @param quartoRepository Repository for Quarto data
     */
    public ReservaQuartoRepository(ClienteRepository clienteRepository, QuartoRepository quartoRepository) {
        this.reservas = new ArrayList<>();
        this.clienteRepository = clienteRepository;
        this.quartoRepository = quartoRepository;
        loadFromCSV();
    }
    
    /**
     * Load reserva data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String clienteId = row[1];
                String quartoId = row[2];
                int ano = Integer.parseInt(row[3]);
                int mes = Integer.parseInt(row[4]);
                int semana = Integer.parseInt(row[5]);
                
                ReservaQuarto reserva = new ReservaQuarto(id, clienteId, quartoId, ano, mes, semana);
                
                // Link to cliente if available
                Optional<Cliente> cliente = clienteRepository.findById(clienteId);
                cliente.ifPresent(reserva::setCliente);
                
                // Link to quarto if available
                Optional<Quarto> quarto = quartoRepository.findByNumero(quartoId);
                quarto.ifPresent(reserva::setQuarto);
                
                reservas.add(reserva);
            } catch (Exception e) {
                System.out.println("Error parsing reserva data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all reservas
     * @return List of reservas
     */
    public List<ReservaQuarto> getAllReservas() {
        return new ArrayList<>(reservas);
    }
    
    /**
     * Find reserva by ID
     * @param id Reserva ID
     * @return Optional containing the reserva if found, empty otherwise
     */
    public Optional<ReservaQuarto> findById(String id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Find reservas by cliente
     * @param clienteId Cliente ID
     * @return List of reservas for the specified cliente
     */
    public List<ReservaQuarto> findByCliente(String clienteId) {
        return reservas.stream()
                .filter(reserva -> reserva.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
    
    /**
     * Find reservas by quarto
     * @param quartoId Quarto ID
     * @return List of reservas for the specified quarto
     */
    public List<ReservaQuarto> findByQuarto(String quartoId) {
        return reservas.stream()
                .filter(reserva -> reserva.getQuartoId().equals(quartoId))
                .collect(Collectors.toList());
    }
    
    /**
     * Check if quarto is available for the specified date
     * @param quartoId Quarto ID
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return true if quarto is available, false otherwise
     */
    public boolean isQuartoAvailable(String quartoId, int ano, int mes, int semana) {
        return reservas.stream()
                .noneMatch(r -> r.getQuartoId().equals(quartoId) &&
                        r.getAno() == ano &&
                        r.getMes() == mes &&
                        r.getSemana() == semana);
    }
    
    /**
     * Get quartos disponíveis for the specified date
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return List of quarto IDs that are available
     */
    public List<String> getQuartosDisponiveis(int ano, int mes, int semana) {
        List<String> ocupados = reservas.stream()
                .filter(r -> r.getAno() == ano && r.getMes() == mes && r.getSemana() == semana)
                .map(ReservaQuarto::getQuartoId)
                .collect(Collectors.toList());
        
        return quartoRepository.getAllQuartos().stream()
                .map(Quarto::getNumQuarto)
                .filter(num -> !ocupados.contains(num))
                .collect(Collectors.toList());
    }
    
    /**
     * Add a new reserva
     * @param reserva Reserva to add
     * @return true if added successfully, false otherwise
     */
    public boolean addReserva(ReservaQuarto reserva) {
        // Check if quarto is available for the specified date
        if (!isQuartoAvailable(reserva.getQuartoId(), reserva.getAno(), reserva.getMes(), reserva.getSemana())) {
            return false;
        }
        
        // Link to cliente if available
        Optional<Cliente> cliente = clienteRepository.findById(reserva.getClienteId());
        cliente.ifPresent(reserva::setCliente);
        
        // Link to quarto if available
        Optional<Quarto> quarto = quartoRepository.findByNumero(reserva.getQuartoId());
        quarto.ifPresent(reserva::setQuarto);
        
        return reservas.add(reserva);
    }
    
    /**
     * Get reservas for the current week
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return List of reservas for the specified week
     */
    public List<ReservaQuarto> getReservasByWeek(int ano, int mes, int semana) {
        return reservas.stream()
                .filter(r -> r.getAno() == ano && r.getMes() == mes && r.getSemana() == semana)
                .collect(Collectors.toList());
    }
    
    /**
     * Get reservas for the specified month
     * @param ano Year
     * @param mes Month
     * @return List of reservas for the specified month
     */
    public List<ReservaQuarto> getReservasByMonth(int ano, int mes) {
        return reservas.stream()
                .filter(r -> r.getAno() == ano && r.getMes() == mes)
                .collect(Collectors.toList());
    }
}
