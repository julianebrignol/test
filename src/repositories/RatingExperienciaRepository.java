package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Experiencia;
import models.Guia;
import models.RatingExperiencia;
import tools.CSVFileReader;

/**
 * Repository for RatingExperiencia data
 */
public class RatingExperienciaRepository {
    private List<RatingExperiencia> ratings;
    private ExperienciaRepository experienciaRepository;
    private GuiaRepository guiaRepository;
    private static final String CSV_FILE_PATH = "src/files/ratings_experiencias.csv";
    
    /**
     * Constructor
     * @param experienciaRepository Repository for Experiencia data
     * @param guiaRepository Repository for Guia data
     */
    public RatingExperienciaRepository(ExperienciaRepository experienciaRepository, GuiaRepository guiaRepository) {
        this.ratings = new ArrayList<>();
        this.experienciaRepository = experienciaRepository;
        this.guiaRepository = guiaRepository;
        loadFromCSV();
    }
    
    /**
     * Load rating data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String experienciaId = row[1];
                double ratingExperiencia = Double.parseDouble(row[2]);
                double ratingGuia = Double.parseDouble(row[3]);
                
                ratings.add(new RatingExperiencia(id, experienciaId, ratingExperiencia, ratingGuia));
            } catch (Exception e) {
                System.out.println("Error parsing rating data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all ratings
     * @return List of ratings
     */
    public List<RatingExperiencia> getAllRatings() {
        return new ArrayList<>(ratings);
    }
    
    /**
     * Find ratings by experiencia
     * @param experienciaId Experiencia ID
     * @return List of ratings for the specified experiencia
     */
    public List<RatingExperiencia> findByExperiencia(String experienciaId) {
        return ratings.stream()
                .filter(rating -> rating.getExperienciaId().equals(experienciaId))
                .collect(Collectors.toList());
    }
    
    /**
     * Add a new rating
     * @param rating Rating to add
     * @return true if added successfully, false otherwise
     */
    public boolean addRating(RatingExperiencia rating) {
        boolean added = ratings.add(rating);
        
        if (added) {
            // Update experiencia rating
            updateExperienciaRating(rating.getExperienciaId());
            
            // Update guia rating
            Optional<Experiencia> experienciaOpt = experienciaRepository.findById(rating.getExperienciaId());
            if (experienciaOpt.isPresent()) {
                String guiaId = experienciaOpt.get().getGuiaId();
                updateGuiaRating(guiaId);
            }
        }
        
        return added;
    }
    
    /**
     * Update experiencia rating based on all ratings
     * @param experienciaId Experiencia ID
     */
    private void updateExperienciaRating(String experienciaId) {
        List<RatingExperiencia> experienciaRatings = findByExperiencia(experienciaId);
        
        if (!experienciaRatings.isEmpty()) {
            double avgRating = experienciaRatings.stream()
                    .mapToDouble(RatingExperiencia::getRatingExperiencia)
                    .average()
                    .orElse(0);
            
            experienciaRepository.updateRating(experienciaId, avgRating);
        }
    }
    
    /**
     * Update guia rating based on all ratings
     * @param guiaId Guia ID
     */
    private void updateGuiaRating(String guiaId) {
        List<String> experienciaIds = experienciaRepository.findByGuia(guiaId)
                .stream()
                .map(Experiencia::getId)
                .collect(Collectors.toList());
        
        List<RatingExperiencia> guiaRatings = ratings.stream()
                .filter(rating -> experienciaIds.contains(rating.getExperienciaId()))
                .collect(Collectors.toList());
        
        if (!guiaRatings.isEmpty()) {
            double avgRating = guiaRatings.stream()
                    .mapToDouble(RatingExperiencia::getRatingGuia)
                    .average()
                    .orElse(0);
            
            guiaRepository.updateRating(guiaId, avgRating);
        }
    }
}
