package models;

/**
 * Model class for RatingExperiencia (Experience Rating) information
 */
public class RatingExperiencia {
    private String id;
    private String experienciaId;
    private double ratingExperiencia;
    private double ratingGuia;
    
    /**
     * Constructor
     * @param id Rating ID
     * @param experienciaId Experience ID
     * @param ratingExperiencia Experience rating
     * @param ratingGuia Guide rating
     */
    public RatingExperiencia(String id, String experienciaId, double ratingExperiencia, double ratingGuia) {
        this.id = id;
        this.experienciaId = experienciaId;
        this.ratingExperiencia = ratingExperiencia;
        this.ratingGuia = ratingGuia;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getExperienciaId() {
        return experienciaId;
    }
    
    public void setExperienciaId(String experienciaId) {
        this.experienciaId = experienciaId;
    }
    
    public double getRatingExperiencia() {
        return ratingExperiencia;
    }
    
    public void setRatingExperiencia(double ratingExperiencia) {
        this.ratingExperiencia = ratingExperiencia;
    }
    
    public double getRatingGuia() {
        return ratingGuia;
    }
    
    public void setRatingGuia(double ratingGuia) {
        this.ratingGuia = ratingGuia;
    }
}
