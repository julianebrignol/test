package controller;

import models.User;
import repositories.UserRepository;

/**
 * Controller for authentication operations
 */
public class AuthController {
    private UserRepository userRepository;
    private User currentUser;
    
    /**
     * Constructor
     * @param userRepository Repository for User data
     */
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currentUser = null;
    }
    
    /**
     * Login with username and password
     * @param username Username
     * @param password Password
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        return userRepository.authenticate(username, password)
                .map(user -> {
                    currentUser = user;
                    return true;
                })
                .orElse(false);
    }
    
    /**
     * Logout current user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Get current user
     * @return Current user or null if not logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if current user has the specified role
     * @param role Role to check
     * @return true if current user has the specified role, false otherwise
     */
    public boolean hasRole(User.UserRole role) {
        return currentUser != null && currentUser.getRole() == role;
    }
    
    /**
     * Add a new user
     * @param id User ID
     * @param username Username
     * @param password Password
     * @param role User role
     * @return true if added successfully, false otherwise
     */
    public boolean addUser(String id, String username, String password, User.UserRole role) {
        return userRepository.addUser(new User(id, username, password, role));
    }
}
