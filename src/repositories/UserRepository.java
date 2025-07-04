package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.User;
import tools.CSVFileReader;

/**
 * Repository for User data
 */
public class UserRepository {
    private List<User> users;
    private static final String CSV_FILE_PATH = "src/files/logins.csv";
    
    /**
     * Constructor
     */
    public UserRepository() {
        this.users = new ArrayList<>();
        loadFromCSV();
    }
    
    /**
     * Load user data from CSV file
     */
    private void loadFromCSV() {
        List<String[]> data = CSVFileReader.readCSV(CSV_FILE_PATH, true);
        
        for (String[] row : data) {
            try {
                String id = row[0];
                String username = row[1];
                String password = row[2];
                User.UserRole role = User.UserRole.valueOf(row[3]);
                
                users.add(new User(id, username, password, role));
            } catch (Exception e) {
                System.out.println("Error parsing user data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get all users
     * @return List of users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    /**
     * Find user by ID
     * @param id User ID
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> findById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Find user by username
     * @param username Username
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
    
    /**
     * Add a new user
     * @param user User to add
     * @return true if added successfully, false otherwise
     */
    public boolean addUser(User user) {
        // Check if user with same username already exists
        if (findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        
        return users.add(user);
    }
    
    /**
     * Authenticate user
     * @param username Username
     * @param password Password
     * @return Optional containing the user if authentication successful, empty otherwise
     */
    public Optional<User> authenticate(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
    
    /**
     * Get users by role
     * @param role Role to filter by
     * @return List of users with the specified role
     */
    public List<User> getUsersByRole(User.UserRole role) {
        return users.stream()
                .filter(user -> user.getRole() == role)
                .collect(Collectors.toList());
    }
}
