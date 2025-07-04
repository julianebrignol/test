package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Cliente;
import tools.CSVFileReader;

/**
 * Repository for Cliente data
 */
public class ClienteRepository {
    private List<Cliente> clientes;
    private static final String CSV_FILE_PATH = "src/files/clientes.csv";
    
    /**
     * Constructor
     */
    public ClienteRepository() {
        this.clientes = new ArrayList<>();
        loadFromCSV();
    }
    
    /**
     * Load cliente data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String nome = row[1];
                String email = row[2];
                String nacionalidade = row[3];
                
                clientes.add(new Cliente(id, nome, email, nacionalidade));
            } catch (Exception e) {
                System.out.println("Error parsing cliente data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all clientes
     * @return List of clientes
     */
    public List<Cliente> getAllClientes() {
        return new ArrayList<>(clientes);
    }
    
    /**
     * Find cliente by ID
     * @param id Cliente ID
     * @return Optional containing the cliente if found, empty otherwise
     */
    public Optional<Cliente> findById(String id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Add a new cliente
     * @param cliente Cliente to add
     * @return true if added successfully, false otherwise
     */
    public boolean addCliente(Cliente cliente) {
        // Check if cliente with same ID already exists
        if (findById(cliente.getId()).isPresent()) {
            return false;
        }
        
        return clientes.add(cliente);
    }
}
