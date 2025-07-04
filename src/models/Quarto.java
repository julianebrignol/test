package models;

/**
 * Model class for Quarto (Room) information
 */
public class Quarto {
    private String numQuarto;
    private String tipologiaId;
    private double precoPorSemana;
    private Tipologia tipologia;
    
    /**
     * Constructor
     * @param numQuarto Room number
     * @param tipologiaId Typology ID
     * @param precoPorSemana Price per week
     */
    public Quarto(String numQuarto, String tipologiaId, double precoPorSemana) {
        this.numQuarto = numQuarto;
        this.tipologiaId = tipologiaId;
        this.precoPorSemana = precoPorSemana;
    }
    
    // Getters and Setters
    
    public String getNumQuarto() {
        return numQuarto;
    }
    
    public void setNumQuarto(String numQuarto) {
        this.numQuarto = numQuarto;
    }
    
    public String getTipologiaId() {
        return tipologiaId;
    }
    
    public void setTipologiaId(String tipologiaId) {
        this.tipologiaId = tipologiaId;
    }
    
    public double getPrecoPorSemana() {
        return precoPorSemana;
    }
    
    public void setPrecoPorSemana(double precoPorSemana) {
        this.precoPorSemana = precoPorSemana;
    }
    
    public Tipologia getTipologia() {
        return tipologia;
    }
    
    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }
}
