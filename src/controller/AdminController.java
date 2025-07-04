package controller;

import repositories.QuartoRepository;
import repositories.ReservaQuartoRepository;
import repositories.VendaExperienciaRepository;

/**
 * Controller for admin operations
 */
public class AdminController {
    private ReservaQuartoRepository reservaQuartoRepository;
    private VendaExperienciaRepository vendaExperienciaRepository;
    private QuartoRepository quartoRepository;
    
    /**
     * Constructor
     * @param reservaQuartoRepository Repository for ReservaQuarto data
     * @param vendaExperienciaRepository Repository for VendaExperiencia data
     * @param quartoRepository Repository for Quarto data
     */
    public AdminController(ReservaQuartoRepository reservaQuartoRepository,
            VendaExperienciaRepository vendaExperienciaRepository,
            QuartoRepository quartoRepository) {
        this.reservaQuartoRepository = reservaQuartoRepository;
        this.vendaExperienciaRepository = vendaExperienciaRepository;
        this.quartoRepository = quartoRepository;
    }
    
    /**
     * Get total number of reservas
     * @return Total number of reservas
     */
    public int getTotalReservas() {
        return reservaQuartoRepository.getAllReservas().size();
    }
    
    /**
     * Get total revenue from all reservas and vendas
     * @return Total revenue
     */
    public double getTotalReceitas() {
        double receitasQuartos = reservaQuartoRepository.getAllReservas().stream()
                .filter(r -> r.getQuarto() != null)
                .mapToDouble(r -> r.getQuarto().getPrecoPorSemana())
                .sum();
        
        double receitasExperiencias = vendaExperienciaRepository.getTotalRevenue();
        
        return receitasQuartos + receitasExperiencias;
    }
    
    /**
     * Get total number of reservas for the specified month
     * @param ano Year
     * @param mes Month
     * @return Total number of reservas for the specified month
     */
    public int getTotalReservasByMonth(int ano, int mes) {
        return reservaQuartoRepository.getReservasByMonth(ano, mes).size();
    }
    
    /**
     * Get total revenue for the specified month
     * @param ano Year
     * @param mes Month
     * @return Total revenue for the specified month
     */
    public double getTotalReceitasByMonth(int ano, int mes) {
        // Would need to implement proper month filtering for vendas
        // For now, return 0
        return 0;
    }
}
