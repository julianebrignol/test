package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Tipologia;
import tools.CSVFileReader;

/**
 * Repository for Tipologia data
 */
public class TipologiaRepository {
    private List<Tipologia> tipologias;
    private static final String CSV_FILE_PATH = "src/files/tipologia.csv";
    
    /**
     * Constructor
     */
    public TipologiaRepository() {
        this.tipologias = new ArrayList<>();
        loadFromCSV();
    }
    
    /**
     * Load tipologia data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String descricao = row[1];
                int capacidade = Integer.parseInt(row[2]);
                
                tipologias.add(new Tipologia(id, descricao, capacidade));
            } catch (Exception e) {
                System.out.println("Error parsing tipologia data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all tipologias
     * @return List of tipologias
     */
    public List<Tipologia> getAllTipologias() {
        return new ArrayList<>(tipologias);
    }
    
    /**
     * Find tipologia by ID
     * @param id Tipologia ID
     * @return Optional containing the tipologia if found, empty otherwise
     */
    public Optional<Tipologia> findById(String id) {
        return tipologias.stream()
                .filter(tipologia -> tipologia.getId().equals(id))
                .findFirst();
    }
}
