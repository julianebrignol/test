package repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Cliente;
import models.Experiencia;
import models.VendaExperiencia;
import tools.CSVFileReader;

/**
 * Repository for VendaExperiencia data
 */
public class VendaExperienciaRepository {
    private List<VendaExperiencia> vendas;
    private ClienteRepository clienteRepository;
    private ExperienciaRepository experienciaRepository;
    private static final String CSV_FILE_PATH = "src/files/vendas_experiencias.csv";
    
    /**
     * Constructor
     * @param clienteRepository Repository for Cliente data
     * @param experienciaRepository Repository for Experiencia data
     */
    public VendaExperienciaRepository(ClienteRepository clienteRepository, ExperienciaRepository experienciaRepository) {
        this.vendas = new ArrayList<>();
        this.clienteRepository = clienteRepository;
        this.experienciaRepository = experienciaRepository;
        loadFromCSV();
    }
    
    /**
     * Load venda data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String clienteId = row[1];
                String experienciaId = row[2];
                int numAdultos = Integer.parseInt(row[3]);
                int numCriancas = Integer.parseInt(row[4]);
                
                VendaExperiencia venda = new VendaExperiencia(id, clienteId, experienciaId, numAdultos, numCriancas);
                
                // Link to cliente if available
                Optional<Cliente> cliente = clienteRepository.findById(clienteId);
                cliente.ifPresent(venda::setCliente);
                
                // Link to experiencia if available
                Optional<Experiencia> experiencia = experienciaRepository.findById(experienciaId);
                experiencia.ifPresent(venda::setExperiencia);
                
                vendas.add(venda);
            } catch (Exception e) {
                System.out.println("Error parsing venda data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all vendas
     * @return List of vendas
     */
    public List<VendaExperiencia> getAllVendas() {
        return new ArrayList<>(vendas);
    }
    
    /**
     * Find venda by ID
     * @param id Venda ID
     * @return Optional containing the venda if found, empty otherwise
     */
    public Optional<VendaExperiencia> findById(String id) {
        return vendas.stream()
                .filter(venda -> venda.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Find vendas by cliente
     * @param clienteId Cliente ID
     * @return List of vendas for the specified cliente
     */
    public List<VendaExperiencia> findByCliente(String clienteId) {
        return vendas.stream()
                .filter(venda -> venda.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
    
    /**
     * Find vendas by experiencia
     * @param experienciaId Experiencia ID
     * @return List of vendas for the specified experiencia
     */
    public List<VendaExperiencia> findByExperiencia(String experienciaId) {
        return vendas.stream()
                .filter(venda -> venda.getExperienciaId().equals(experienciaId))
                .collect(Collectors.toList());
    }
    
    /**
     * Get experiencia with most bilhetes sold
     * @return Optional containing the experiencia with most bilhetes sold, empty if no vendas
     */
    public Optional<Experiencia> getTopSellerExperiencia() {
        Map<String, Long> vendasPorExperiencia = vendas.stream()
                .collect(Collectors.groupingBy(VendaExperiencia::getExperienciaId, Collectors.counting()));
        
        Optional<String> topSellerId = vendasPorExperiencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        
        if (!topSellerId.isPresent()) {
            return Optional.empty();
        }
        
        return experienciaRepository.findById(topSellerId.get());
    }
    
    /**
     * Get experiencia with most adultos
     * @return Optional containing the experiencia with most adultos, empty if no vendas
     */
    public Optional<Experiencia> getExperienciaMostAdults() {
        Map<String, Integer> adultsPorExperiencia = vendas.stream()
                .collect(Collectors.groupingBy(
                        VendaExperiencia::getExperienciaId,
                        Collectors.summingInt(VendaExperiencia::getNumAdultos)));
        
        Optional<String> topAdultsId = adultsPorExperiencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        
        if (!topAdultsId.isPresent()) {
            return Optional.empty();
        }
        
        return experienciaRepository.findById(topAdultsId.get());
    }
    
    /**
     * Get experiencia with most criancas
     * @return Optional containing the experiencia with most criancas, empty if no vendas
     */
    public Optional<Experiencia> getExperienciaMostChildren() {
        Map<String, Integer> childrenPorExperiencia = vendas.stream()
                .collect(Collectors.groupingBy(
                        VendaExperiencia::getExperienciaId,
                        Collectors.summingInt(VendaExperiencia::getNumCriancas)));
        
        Optional<String> topChildrenId = childrenPorExperiencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        
        if (!topChildrenId.isPresent()) {
            return Optional.empty();
        }
        
        return experienciaRepository.findById(topChildrenId.get());
    }
    
    /**
     * Get experiencia with most revenue
     * @return Optional containing the experiencia with most revenue, empty if no vendas
     */
    public Optional<Experiencia> getMostProfitableExperiencia() {
        Map<String, Double> revenuePorExperiencia = vendas.stream()
                .filter(v -> v.getExperiencia() != null)
                .collect(Collectors.groupingBy(
                        VendaExperiencia::getExperienciaId,
                        Collectors.summingDouble(VendaExperiencia::calcularValorTotal)));
        
        Optional<String> topRevenueId = revenuePorExperiencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        
        if (!topRevenueId.isPresent()) {
            return Optional.empty();
        }
        
        return experienciaRepository.findById(topRevenueId.get());
    }
    
    /**
     * Get experiencia with least revenue
     * @return Optional containing the experiencia with least revenue, empty if no vendas
     */
    public Optional<Experiencia> getLeastProfitableExperiencia() {
        Map<String, Double> revenuePorExperiencia = vendas.stream()
                .filter(v -> v.getExperiencia() != null)
                .collect(Collectors.groupingBy(
                        VendaExperiencia::getExperienciaId,
                        Collectors.summingDouble(VendaExperiencia::calcularValorTotal)));
        
        Optional<String> leastRevenueId = revenuePorExperiencia.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        
        if (!leastRevenueId.isPresent()) {
            return Optional.empty();
        }
        
        return experienciaRepository.findById(leastRevenueId.get());
    }
    
    /**
     * Add a new venda
     * @param venda Venda to add
     * @return true if added successfully, false otherwise
     */
    public boolean addVenda(VendaExperiencia venda) {
        // Link to cliente if available
        Optional<Cliente> cliente = clienteRepository.findById(venda.getClienteId());
        cliente.ifPresent(venda::setCliente);
        
        // Link to experiencia if available
        Optional<Experiencia> experiencia = experienciaRepository.findById(venda.getExperienciaId());
        experiencia.ifPresent(venda::setExperiencia);
        
        return vendas.add(venda);
    }
    
    /**
     * Get total revenue from all vendas
     * @return Total revenue
     */
    public double getTotalRevenue() {
        return vendas.stream()
                .filter(v -> v.getExperiencia() != null)
                .mapToDouble(VendaExperiencia::calcularValorTotal)
                .sum();
    }
}
