import controller.AdminController;
import controller.AuthController;
import controller.ClienteController;
import controller.ExperienciaController;
import controller.QuartoController;
import repositories.ClienteRepository;
import repositories.ExperienciaRepository;
import repositories.GuiaRepository;
import repositories.QuartoRepository;
import repositories.RatingExperienciaRepository;
import repositories.ReservaQuartoRepository;
import repositories.TipologiaRepository;
import repositories.UserRepository;
import repositories.VendaExperienciaRepository;
import views.AdminView;
import views.ClienteView;
import views.GestaoView;
import views.GuiaView;
import views.LoginView;

/**
 * Main class for the CESAE Resort application
 */
public class Main {
    
    /**
     * Main method
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Iniciando CESAE Resort...");
        
        // Initialize repositories
        UserRepository userRepository = new UserRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        TipologiaRepository tipologiaRepository = new TipologiaRepository();
        QuartoRepository quartoRepository = new QuartoRepository(tipologiaRepository);
        ReservaQuartoRepository reservaQuartoRepository = new ReservaQuartoRepository(clienteRepository, quartoRepository);
        GuiaRepository guiaRepository = new GuiaRepository();
        ExperienciaRepository experienciaRepository = new ExperienciaRepository(guiaRepository);
        VendaExperienciaRepository vendaExperienciaRepository = new VendaExperienciaRepository(clienteRepository, experienciaRepository);
        RatingExperienciaRepository ratingExperienciaRepository = new RatingExperienciaRepository(experienciaRepository, guiaRepository);
        
        // Initialize controllers
        AuthController authController = new AuthController(userRepository);
        ClienteController clienteController = new ClienteController(clienteRepository);
        QuartoController quartoController = new QuartoController(quartoRepository, reservaQuartoRepository, tipologiaRepository);
        ExperienciaController experienciaController = new ExperienciaController(experienciaRepository, vendaExperienciaRepository, guiaRepository, ratingExperienciaRepository);
        AdminController adminController = new AdminController(reservaQuartoRepository, vendaExperienciaRepository, quartoRepository);
        
        // Initialize views
        ClienteView clienteView = new ClienteView(quartoController, experienciaController);
        GestaoView gestaoView = new GestaoView(quartoController, clienteController, experienciaController);
        GuiaView guiaView = new GuiaView(experienciaController);
        AdminView adminView = new AdminView(adminController, quartoController, experienciaController, authController);
        
        // Initialize login view
        LoginView loginView = new LoginView(authController, clienteView, gestaoView, guiaView, adminView);
        
        // Start the application
        loginView.showLoginMenu();
    }
}
