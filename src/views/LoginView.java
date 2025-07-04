package views;

import java.util.Scanner;

import controller.AuthController;
import models.User;

/**
 * View for login and menu selection
 */
public class LoginView {
    private Scanner scanner;
    private AuthController authController;
    private ClienteView clienteView;
    private GestaoView gestaoView;
    private GuiaView guiaView;
    private AdminView adminView;
    
    /**
     * Constructor
     * @param authController Controller for authentication
     * @param clienteView View for cliente menu
     * @param gestaoView View for gestao menu
     * @param guiaView View for guia menu
     * @param adminView View for admin menu
     */
    public LoginView(AuthController authController, ClienteView clienteView, GestaoView gestaoView, GuiaView guiaView,
            AdminView adminView) {
        this.scanner = new Scanner(System.in);
        this.authController = authController;
        this.clienteView = clienteView;
        this.gestaoView = gestaoView;
        this.guiaView = guiaView;
        this.adminView = adminView;
    }
    
    /**
     * Show login menu
     */
    public void showLoginMenu() {
        System.out.println("==================================");
        System.out.println("  BEM-VINDO AO CESAE RESORT");
        System.out.println("  Hotel Temático da Programação");
        System.out.println("==================================");
        System.out.println("1. Login");
        System.out.println("2. Continuar como cliente");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (option) {
            case 1:
                doLogin();
                break;
            case 2:
                clienteView.showMenu();
                showLoginMenu();
                break;
            case 0:
                System.out.println("Obrigado por utilizar o CESAE Resort!");
                return;
            default:
                System.out.println("Opção inválida!");
                showLoginMenu();
                break;
        }
    }
    
    /**
     * Handle login
     */
    private void doLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (authController.login(username, password)) {
            User user = authController.getCurrentUser();
            System.out.println("Login bem-sucedido como " + user.getUsername() + "!");
            
            // Redirect to appropriate menu based on role
            switch (user.getRole()) {
                case ADMIN:
                    adminView.showMenu();
                    break;
                case GESTAO:
                    gestaoView.showMenu();
                    break;
                case GUIA:
                    guiaView.showMenu();
                    break;
                default:
                    clienteView.showMenu();
                    break;
            }
            
            // Logout and return to login menu
            authController.logout();
            showLoginMenu();
        } else {
            System.out.println("Username ou password inválidos!");
            showLoginMenu();
        }
    }
}
