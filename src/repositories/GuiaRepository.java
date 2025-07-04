package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Guia;
import tools.CSVFileReader;

/**
 * Repository for Guia data
 */
public class GuiaRepository {
    private List<Guia> guias;
    private static final String CSV_FILE_PATH = "src/files/guias_experiencias.csv";
    
    /**
     * Constructor
     */
    public GuiaRepository() {
        this.guias = new ArrayList<>();
        loadFromCSV();
    }
    
    /**
     * Load guia data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String nome = row[1];
                double ratingGuia = Double.parseDouble(row[2]);
                
                guias.add(new Guia(id, nome, ratingGuia));
            } catch (Exception e) {
                System.out.println("Error parsing guia data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all guias
     * @return List of guias
     */
    public List<Guia> getAllGuias() {
        return new ArrayList<>(guias);
    }
    
    /**
     * Find guia by ID
     * @param id Guia ID
     * @return Optional containing the guia if found, empty otherwise
     */
    public Optional<Guia> findById(String id) {
        return guias.stream()
                .filter(guia -> guia.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Update guia rating
     * @param id Guia ID
     * @param novoRating New rating
     * @return true if updated successfully, false otherwise
     */
    public boolean updateRating(String id, double novoRating) {
        Optional<Guia> guiaOpt = findById(id);
        
        if (!guiaOpt.isPresent()) {
            return false;
        }
        
        Guia guia = guiaOpt.get();
        guia.setRatingGuia(novoRating);
        return true;
    }
}
