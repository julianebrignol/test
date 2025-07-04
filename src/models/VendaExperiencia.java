package models;

/**
 * Model class for VendaExperiencia (Experience Sale) information
 */
public class VendaExperiencia {
    private String id;
    private String clienteId;
    private String experienciaId;
    private int numAdultos;
    private int numCriancas;
    private Cliente cliente;
    private Experiencia experiencia;
    
    /**
     * Constructor
     * @param id Sale ID
     * @param clienteId Client ID
     * @param experienciaId Experience ID
     * @param numAdultos Number of adults
     * @param numCriancas Number of children
     */
    public VendaExperiencia(String id, String clienteId, String experienciaId, int numAdultos, int numCriancas) {
        this.id = id;
        this.clienteId = clienteId;
        this.experienciaId = experienciaId;
        this.numAdultos = numAdultos;
        this.numCriancas = numCriancas;
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
    
    public String getExperienciaId() {
        return experienciaId;
    }
    
    public void setExperienciaId(String experienciaId) {
        this.experienciaId = experienciaId;
    }
    
    public int getNumAdultos() {
        return numAdultos;
    }
    
    public void setNumAdultos(int numAdultos) {
        this.numAdultos = numAdultos;
    }
    
    public int getNumCriancas() {
        return numCriancas;
    }
    
    public void setNumCriancas(int numCriancas) {
        this.numCriancas = numCriancas;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Experiencia getExperiencia() {
        return experiencia;
    }
    
    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
    
    /**
     * Calculate the total value of this sale
     * @return The total value
     */
    public double calcularValorTotal() {
        if (experiencia == null) {
            return 0;
        }
        return (numAdultos * experiencia.getPrecoAdulto()) + (numCriancas * experiencia.getPrecoCrianca());
    }
}
