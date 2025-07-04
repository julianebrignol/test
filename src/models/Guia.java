package models;

/**
 * Model class for Guia (Guide) information
 */
public class Guia {
    private String id;
    private String nome;
    private double ratingGuia;
    
    /**
     * Constructor
     * @param id Guide ID
     * @param nome Name
     * @param ratingGuia Guide rating
     */
    public Guia(String id, String nome, double ratingGuia) {
        this.id = id;
        this.nome = nome;
        this.ratingGuia = ratingGuia;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getRatingGuia() {
        return ratingGuia;
    }
    
    public void setRatingGuia(double ratingGuia) {
        this.ratingGuia = ratingGuia;
    }
}
