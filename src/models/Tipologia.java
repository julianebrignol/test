package models;

/**
 * Model class for Tipologia (Room Type) information
 */
public class Tipologia {
    private String id;
    private String descricao;
    private int capacidade;
    
    /**
     * Constructor
     * @param id Typology ID
     * @param descricao Description
     * @param capacidade Capacity
     */
    public Tipologia(String id, String descricao, int capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
