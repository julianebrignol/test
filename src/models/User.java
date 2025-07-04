package models;

/**
 * Model class for User login information
 */
public class User {
    private String id;
    private String username;
    private String password;
    private UserRole role;
    
    /**
     * Enum for user roles
     */
    public enum UserRole {
        ADMIN,
        GESTAO,
        GUIA,
        CLIENTE
    }
    
    /**
     * Constructor
     * @param id User ID
     * @param username Username
     * @param password Password
     * @param role User role
     */
    public User(String id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public UserRole getRole() {
        return role;
    }
    
    public void setRole(UserRole role) {
        this.role = role;
    }
}
