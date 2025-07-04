package models;

/**
 * Model class for ReservaQuarto (Room Reservation) information
 */
public class ReservaQuarto {
    private String id;
    private String clienteId;
    private String quartoId;
    private int ano;
    private int mes;
    private int semana;
    private Cliente cliente;
    private Quarto quarto;
    
    /**
     * Constructor
     * @param id Reservation ID
     * @param clienteId Client ID
     * @param quartoId Room ID
     * @param ano Year
     * @param mes Month
     * @param semana Week
     */
    public ReservaQuarto(String id, String clienteId, String quartoId, int ano, int mes, int semana) {
        this.id = id;
        this.clienteId = clienteId;
        this.quartoId = quartoId;
        this.ano = ano;
        this.mes = mes;
        this.semana = semana;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
    
    public String getQuartoId() {
        return quartoId;
    }
    
    public void setQuartoId(String quartoId) {
        this.quartoId = quartoId;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int getMes() {
        return mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getSemana() {
        return semana;
    }
    
    public void setSemana(int semana) {
        this.semana = semana;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Quarto getQuarto() {
        return quarto;
    }
    
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
}
