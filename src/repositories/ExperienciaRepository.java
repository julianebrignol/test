package repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Experiencia;
import models.Guia;
import tools.CSVFileReader;

/**
 * Repository for Experiencia data
 */
public class ExperienciaRepository {
    private List<Experiencia> experiencias;
    private GuiaRepository guiaRepository;
    private static final String CSV_FILE_PATH = "src/files/experiencias.csv";
    
    /**
     * Constructor
     * @param guiaRepository Repository for Guia data
     */
    public ExperienciaRepository(GuiaRepository guiaRepository) {
        this.experiencias = new ArrayList<>();
        this.guiaRepository = guiaRepository;
        loadFromCSV();
    }
    
    /**
     * Load experiencia data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String nome = row[1];
                String descricao = row[2];
                double precoAdulto = Double.parseDouble(row[3]);
                double precoCrianca = Double.parseDouble(row[4]);
                double ratingExperiencia = Double.parseDouble(row[5]);
                String guiaId = row[6];
                
                Experiencia experiencia = new Experiencia(id, nome, descricao, precoAdulto, precoCrianca, ratingExperiencia, guiaId);
                
                // Link to guia if available
                Optional<Guia> guia = guiaRepository.findById(guiaId);
                guia.ifPresent(experiencia::setGuia);
                
                experiencias.add(experiencia);
            } catch (Exception e) {
                System.out.println("Error parsing experiencia data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all experiencias
     * @return List of experiencias
     */
    public List<Experiencia> getAllExperiencias() {
        return new ArrayList<>(experiencias);
    }
    
    /**
     * Find experiencia by ID
     * @param id Experiencia ID
     * @return Optional containing the experiencia if found, empty otherwise
     */
    public Optional<Experiencia> findById(String id) {
        return experiencias.stream()
                .filter(experiencia -> experiencia.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Find experiencias by guia
     * @param guiaId Guia ID
     * @return List of experiencias with the specified guia
     */
    public List<Experiencia> findByGuia(String guiaId) {
        return experiencias.stream()
                .filter(experiencia -> experiencia.getGuiaId().equals(guiaId))
                .collect(Collectors.toList());
    }
    
    /**
     * Get experiencia with best rating
     * @return Optional containing the experiencia with best rating, empty if no experiencias
     */
    public Optional<Experiencia> getExperienciaWithBestRating() {
        return experiencias.stream()
                .max(Comparator.comparing(Experiencia::getRatingExperiencia));
    }
    
    /**
     * Update experiencia rating
     * @param id Experiencia ID
     * @param novoRating New rating
     * @return true if updated successfully, false otherwise
     */
    public boolean updateRating(String id, double novoRating) {
        Optional<Experiencia> experienciaOpt = findById(id);
        
        if (!experienciaOpt.isPresent()) {
            return false;
        }
        
        Experiencia experiencia = experienciaOpt.get();
        experiencia.setRatingExperiencia(novoRating);
        return true;
    }
}
