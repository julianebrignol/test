package controller;

import java.util.List;
import java.util.Optional;

import models.Experiencia;
import models.Guia;
import models.RatingExperiencia;
import models.VendaExperiencia;
import repositories.ExperienciaRepository;
import repositories.GuiaRepository;
import repositories.RatingExperienciaRepository;
import repositories.VendaExperienciaRepository;

/**
 * Controller for operations related to experiences and experience sales
 */
public class ExperienciaController {
    private ExperienciaRepository experienciaRepository;
    private VendaExperienciaRepository vendaExperienciaRepository;
    private GuiaRepository guiaRepository;
    private RatingExperienciaRepository ratingExperienciaRepository;
    
    /**
     * Constructor
     * @param experienciaRepository Repository for Experiencia data
     * @param vendaExperienciaRepository Repository for VendaExperiencia data
     * @param guiaRepository Repository for Guia data
     * @param ratingExperienciaRepository Repository for RatingExperiencia data
     */
    public ExperienciaController(ExperienciaRepository experienciaRepository,
            VendaExperienciaRepository vendaExperienciaRepository,
            GuiaRepository guiaRepository,
            RatingExperienciaRepository ratingExperienciaRepository) {
        this.experienciaRepository = experienciaRepository;
        this.vendaExperienciaRepository = vendaExperienciaRepository;
        this.guiaRepository = guiaRepository;
        this.ratingExperienciaRepository = ratingExperienciaRepository;
    }
    
    /**
     * Get all experiencias
     * @return List of all experiencias
     */
    public List<Experiencia> getAllExperiencias() {
        return experienciaRepository.getAllExperiencias();
    }
    
    /**
     * Get experiencia by ID
     * @param id Experiencia ID
     * @return Optional containing the experiencia if found, empty otherwise
     */
    public Optional<Experiencia> getExperienciaById(String id) {
        return experienciaRepository.findById(id);
    }
    
    /**
     * Get experiencia favorita (with best rating)
     * @return Optional containing the experiencia with best rating, empty if no experiencias
     */
    public Optional<Experiencia> getExperienciaFavorita() {
        return experienciaRepository.getExperienciaWithBestRating();
    }
    
    /**
     * Get experiencia top-seller (with most bilhetes sold)
     * @return Optional containing the experiencia with most bilhetes sold, empty if no vendas
     */
    public Optional<Experiencia> getExperienciaTopSeller() {
        return vendaExperienciaRepository.getTopSellerExperiencia();
    }
    
    /**
     * Add rating for experiencia
     * @param id Rating ID
     * @param experienciaId Experiencia ID
     * @param ratingExperiencia Experience rating
     * @param ratingGuia Guide rating
     * @return true if added successfully, false otherwise
     */
    public boolean addRating(String id, String experienciaId, double ratingExperiencia, double ratingGuia) {
        RatingExperiencia rating = new RatingExperiencia(id, experienciaId, ratingExperiencia, ratingGuia);
        return ratingExperienciaRepository.addRating(rating);
    }
    
    /**
     * Add venda for experiencia
     * @param id Venda ID
     * @param clienteId Cliente ID
     * @param experienciaId Experiencia ID
     * @param numAdultos Number of adults
     * @param numCriancas Number of children
     * @return true if added successfully, false otherwise
     */
    public boolean addVenda(String id, String clienteId, String experienciaId, int numAdultos, int numCriancas) {
        VendaExperiencia venda = new VendaExperiencia(id, clienteId, experienciaId, numAdultos, numCriancas);
        return vendaExperienciaRepository.addVenda(venda);
    }
    
    /**
     * Get experiencias by guia
     * @param guiaId Guia ID
     * @return List of experiencias for the specified guia
     */
    public List<Experiencia> getExperienciasByGuia(String guiaId) {
        return experienciaRepository.findByGuia(guiaId);
    }
    
    /**
     * Get vendas by experiencia
     * @param experienciaId Experiencia ID
     * @return List of vendas for the specified experiencia
     */
    public List<VendaExperiencia> getVendasByExperiencia(String experienciaId) {
        return vendaExperienciaRepository.findByExperiencia(experienciaId);
    }
    
    /**
     * Get guia by ID
     * @param id Guia ID
     * @return Optional containing the guia if found, empty otherwise
     */
    public Optional<Guia> getGuiaById(String id) {
        return guiaRepository.findById(id);
    }
    
    /**
     * Get all guias
     * @return List of all guias
     */
    public List<Guia> getAllGuias() {
        return guiaRepository.getAllGuias();
    }
    
    /**
     * Get experiencia with most adults
     * @return Optional containing the experiencia with most adults, empty if no vendas
     */
    public Optional<Experiencia> getExperienciaMostAdults() {
        return vendaExperienciaRepository.getExperienciaMostAdults();
    }
    
    /**
     * Get experiencia with most children
     * @return Optional containing the experiencia with most children, empty if no vendas
     */
    public Optional<Experiencia> getExperienciaMostChildren() {
        return vendaExperienciaRepository.getExperienciaMostChildren();
    }
    
    /**
     * Get most profitable experiencia
     * @return Optional containing the most profitable experiencia, empty if no vendas
     */
    public Optional<Experiencia> getMostProfitableExperiencia() {
        return vendaExperienciaRepository.getMostProfitableExperiencia();
    }
    
    /**
     * Get least profitable experiencia
     * @return Optional containing the least profitable experiencia, empty if no vendas
     */
    public Optional<Experiencia> getLeastProfitableExperiencia() {
        return vendaExperienciaRepository.getLeastProfitableExperiencia();
    }
}
