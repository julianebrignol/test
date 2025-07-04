package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Quarto;
import models.Tipologia;
import tools.CSVFileReader;

/**
 * Repository for Quarto data
 */
public class QuartoRepository {
    private List<Quarto> quartos;
    private TipologiaRepository tipologiaRepository;
    private static final String CSV_FILE_PATH = "src/files/quartos.csv";
    
    /**
     * Constructor
     * @param tipologiaRepository Repository for Tipologia data
     */
    public QuartoRepository(TipologiaRepository tipologiaRepository) {
        this.quartos = new ArrayList<>();
        this.tipologiaRepository = tipologiaRepository;
        loadFromCSV();
    }
    
    /**
     * Load quarto data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String numQuarto = row[0];
                String tipologiaId = row[1];
                double precoPorSemana = Double.parseDouble(row[2]);
                
                Quarto quarto = new Quarto(numQuarto, tipologiaId, precoPorSemana);
                
                // Link to tipologia if available
                Optional<Tipologia> tipologia = tipologiaRepository.findById(tipologiaId);
                tipologia.ifPresent(quarto::setTipologia);
                
                quartos.add(quarto);
            } catch (Exception e) {
                System.out.println("Error parsing quarto data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all quartos
     * @return List of quartos
     */
    public List<Quarto> getAllQuartos() {
        return new ArrayList<>(quartos);
    }
    
    /**
     * Find quarto by numero
     * @param numQuarto Quarto number
     * @return Optional containing the quarto if found, empty otherwise
     */
    public Optional<Quarto> findByNumero(String numQuarto) {
        return quartos.stream()
                .filter(quarto -> quarto.getNumQuarto().equals(numQuarto))
                .findFirst();
    }
    
    /**
     * Find quartos by tipologia
     * @param tipologiaId Tipologia ID
     * @return List of quartos with the specified tipologia
     */
    public List<Quarto> findByTipologia(String tipologiaId) {
        return quartos.stream()
                .filter(quarto -> quarto.getTipologiaId().equals(tipologiaId))
                .collect(Collectors.toList());
    }
}
