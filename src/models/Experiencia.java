package models;

/**
 * Model class for Experiencia (Experience) information
 */
public class Experiencia {
    private String id;
    private String nome;
    private String descricao;
    private double precoAdulto;
    private double precoCrianca;
    private double ratingExperiencia;
    private String guiaId;
    private Guia guia;
    
    /**
     * Constructor
     * @param id Experience ID
     * @param nome Name
     * @param descricao Description
     * @param precoAdulto Adult price
     * @param precoCrianca Child price
     * @param ratingExperiencia Experience rating
     * @param guiaId Guide ID
     */
    public Experiencia(String id, String nome, String descricao, double precoAdulto, double precoCrianca, double ratingExperiencia, String guiaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoAdulto = precoAdulto;
        this.precoCrianca = precoCrianca;
        this.ratingExperiencia = ratingExperiencia;
        this.guiaId = guiaId;
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getPrecoAdulto() {
        return precoAdulto;
    }
    
    public void setPrecoAdulto(double precoAdulto) {
        this.precoAdulto = precoAdulto;
    }
    
    public double getPrecoCrianca() {
        return precoCrianca;
    }
    
    public void setPrecoCrianca(double precoCrianca) {
        this.precoCrianca = precoCrianca;
    }
    
    public double getRatingExperiencia() {
        return ratingExperiencia;
    }
    
    public void setRatingExperiencia(double ratingExperiencia) {
        this.ratingExperiencia = ratingExperiencia;
    }
    
    public String getGuiaId() {
        return guiaId;
    }
    
    public void setGuiaId(String guiaId) {
        this.guiaId = guiaId;
    }
    
    public Guia getGuia() {
        return guia;
    }
    
    public void setGuia(Guia guia) {
        this.guia = guia;
    }
}
