package controller;

import java.util.List;
import java.util.Optional;

import models.Quarto;
import models.ReservaQuarto;
import models.Tipologia;
import repositories.QuartoRepository;
import repositories.ReservaQuartoRepository;
import repositories.TipologiaRepository;

/**
 * Controller for operations related to rooms and room reservations
 */
public class QuartoController {
    private QuartoRepository quartoRepository;
    private ReservaQuartoRepository reservaQuartoRepository;
    private TipologiaRepository tipologiaRepository;
    
    /**
     * Constructor
     * @param quartoRepository Repository for Quarto data
     * @param reservaQuartoRepository Repository for ReservaQuarto data
     * @param tipologiaRepository Repository for Tipologia data
     */
    public QuartoController(QuartoRepository quartoRepository, ReservaQuartoRepository reservaQuartoRepository,
            TipologiaRepository tipologiaRepository) {
        this.quartoRepository = quartoRepository;
        this.reservaQuartoRepository = reservaQuartoRepository;
        this.tipologiaRepository = tipologiaRepository;
    }
    
    /**
     * Get all quartos
     * @return List of all quartos
     */
    public List<Quarto> getAllQuartos() {
        return quartoRepository.getAllQuartos();
    }
    
    /**
     * Get quartos disponíveis for the specified date
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return List of quartos that are available
     */
    public List<Quarto> getQuartosDisponiveis(int ano, int mes, int semana) {
        List<String> quartosDisponiveisIds = reservaQuartoRepository.getQuartosDisponiveis(ano, mes, semana);
        
        return quartoRepository.getAllQuartos().stream()
                .filter(quarto -> quartosDisponiveisIds.contains(quarto.getNumQuarto()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Get reservas for the specified quarto
     * @param numQuarto Quarto number
     * @return List of reservas for the specified quarto
     */
    public List<ReservaQuarto> getReservasByQuarto(String numQuarto) {
        return reservaQuartoRepository.findByQuarto(numQuarto);
    }
    
    /**
     * Get reservas for the current week
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return List of reservas for the specified week
     */
    public List<ReservaQuarto> getReservasAtuais(int ano, int mes, int semana) {
        return reservaQuartoRepository.getReservasByWeek(ano, mes, semana);
    }
    
    /**
     * Check if quarto is available for the specified date
     * @param numQuarto Quarto number
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return true if quarto is available, false otherwise
     */
    public boolean isQuartoAvailable(String numQuarto, int ano, int mes, int semana) {
        return reservaQuartoRepository.isQuartoAvailable(numQuarto, ano, mes, semana);
    }
    
    /**
     * Add a new reserva
     * @param id Reserva ID
     * @param clienteId Cliente ID
     * @param numQuarto Quarto number
     * @param ano Year
     * @param mes Month
     * @param semana Week
     * @return true if added successfully, false otherwise
     */
    public boolean addReserva(String id, String clienteId, String numQuarto, int ano, int mes, int semana) {
        ReservaQuarto reserva = new ReservaQuarto(id, clienteId, numQuarto, ano, mes, semana);
        return reservaQuartoRepository.addReserva(reserva);
    }
    
    /**
     * Get all tipologias
     * @return List of all tipologias
     */
    public List<Tipologia> getAllTipologias() {
        return tipologiaRepository.getAllTipologias();
    }
    
    /**
     * Get tipologia by ID
     * @param id Tipologia ID
     * @return Optional containing the tipologia if found, empty otherwise
     */
    public Optional<Tipologia> getTipologiaById(String id) {
        return tipologiaRepository.findById(id);
    }
    
    /**
     * Get tipologia mais reservada
     * @return Optional containing the tipologia mais reservada, empty if no reservas
     */
    public Optional<Tipologia> getTipologiaMaisReservada() {
        // Implementation would count reservas by tipologia
        // For now, return empty
        return Optional.empty();
    }
    
    /**
     * Get quarto with best price per week
     * @return Optional containing the quarto with best price per week, empty if no quartos
     */
    public Optional<Quarto> getQuartoMelhorPreco() {
        return quartoRepository.getAllQuartos().stream()
                .min((q1, q2) -> Double.compare(q1.getPrecoPorSemana(), q2.getPrecoPorSemana()));
    }
}
