package models;

/**
 * Model class for Cliente (Customer) information
 */
public class Cliente {
    private String id;
    private String nome;
    private String email;
    private String nacionalidade;
    
    /**
     * Constructor
     * @param id Client ID
     * @param nome Name
     * @param email Email
     * @param nacionalidade Nationality
     */
    public Cliente(String id, String nome, String email, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nacionalidade = nacionalidade;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNacionalidade() {
        return nacionalidade;
    }
    
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
