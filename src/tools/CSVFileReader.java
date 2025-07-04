package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Cliente;
import models.Experiencia;
import models.Guia;
import models.Quarto;
import models.RatingExperiencia;
import models.ReservaQuarto;
import models.Tipologia;
import models.User;
import models.VendaExperiencia;

/**
 * Utility class for reading CSV files
 * Provides specific methods for reading each entity type from corresponding CSV files
 */
public class CSVFileReader {
    
    /**
     * Reads a clientes CSV file and returns a list of Cliente objects
     * @param filePath Path to the clientes CSV file
     * @return ArrayList of Cliente objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Cliente> clientesFileReader(String filePath) throws FileNotFoundException {
        File clienteFile = new File(filePath);
        Scanner scanner = new Scanner(clienteFile);
        
        ArrayList<Cliente> clienteList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String nome = splitLine[1];
            String email = splitLine[2];
            String telefone = splitLine[3];
            
            Cliente cliente = new Cliente(id, nome, email, telefone);
            clienteList.add(cliente);
        }
        
        scanner.close();
        return clienteList;
    }
    
    /**
     * Reads a quartos CSV file and returns a list of Quarto objects
     * @param filePath Path to the quartos CSV file
     * @return ArrayList of Quarto objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Quarto> quartosFileReader(String filePath) throws FileNotFoundException {
        File quartoFile = new File(filePath);
        Scanner scanner = new Scanner(quartoFile);
        
        ArrayList<Quarto> quartoList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String numQuarto = splitLine[0];
            String idTipologia = splitLine[1];
            
            // O preço por semana será associado posteriormente com base na tipologia
            // Inicializamos com 0.0 para evitar valores nulos
            Quarto quarto = new Quarto(numQuarto, idTipologia, 0.0);
            quartoList.add(quarto);
        }
        
        scanner.close();
        return quartoList;
    }
    
    /**
     * Reads a tipologias CSV file and returns a list of Tipologia objects
     * @param filePath Path to the tipologias CSV file
     * @return ArrayList of Tipologia objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Tipologia> tipologiasFileReader(String filePath) throws FileNotFoundException {
        File tipologiaFile = new File(filePath);
        Scanner scanner = new Scanner(tipologiaFile);
        
        ArrayList<Tipologia> tipologiaList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String descricao = splitLine[1];
            int capacidade = Integer.parseInt(splitLine[2]);
            
            Tipologia tipologia = new Tipologia(id, descricao, capacidade);
            tipologiaList.add(tipologia);
        }
        
        scanner.close();
        return tipologiaList;
    }
    
    /**
     * Reads a experiencias CSV file and returns a list of Experiencia objects
     * @param filePath Path to the experiencias CSV file
     * @return ArrayList of Experiencia objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Experiencia> experienciasFileReader(String filePath) throws FileNotFoundException {
        File experienciaFile = new File(filePath);
        Scanner scanner = new Scanner(experienciaFile);
        
        ArrayList<Experiencia> experienciaList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String nome = splitLine[1];
            String guiaId = splitLine[2];
            double precoAdulto = Double.parseDouble(splitLine[3]);
            double precoCrianca = Double.parseDouble(splitLine[4]);
            
            // Assumindo que a descrição e o rating começam vazios/zeros
            String descricao = "";
            double ratingExperiencia = 0.0;
            
            Experiencia experiencia = new Experiencia(id, nome, descricao, precoAdulto, precoCrianca, ratingExperiencia, guiaId);
            experienciaList.add(experiencia);
        }
        
        scanner.close();
        return experienciaList;
    }
    
    /**
     * Reads a guias_experiencias CSV file and returns a list of Guia objects
     * @param filePath Path to the guias_experiencias CSV file
     * @return ArrayList of Guia objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Guia> guiasFileReader(String filePath) throws FileNotFoundException {
        File guiaFile = new File(filePath);
        Scanner scanner = new Scanner(guiaFile);
        
        ArrayList<Guia> guiaList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String nome = splitLine[1];
            // Inicializar rating com 0.0, pode ser atualizado posteriormente
            double ratingGuia = 0.0;
            
            Guia guia = new Guia(id, nome, ratingGuia);
            guiaList.add(guia);
        }
        
        scanner.close();
        return guiaList;
    }
    
    /**
     * Reads a reservas_quartos CSV file and returns a list of ReservaQuarto objects
     * @param filePath Path to the reservas_quartos CSV file
     * @return ArrayList of ReservaQuarto objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<ReservaQuarto> reservasQuartoFileReader(String filePath) throws FileNotFoundException {
        File reservaFile = new File(filePath);
        Scanner scanner = new Scanner(reservaFile);
        
        ArrayList<ReservaQuarto> reservaList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String clienteId = splitLine[1];
            String quartoId = splitLine[2];
            int ano = Integer.parseInt(splitLine[3]);
            int mes = Integer.parseInt(splitLine[4]);
            int semana = Integer.parseInt(splitLine[5]);
            
            ReservaQuarto reserva = new ReservaQuarto(id, clienteId, quartoId, ano, mes, semana);
            reservaList.add(reserva);
        }
        
        scanner.close();
        return reservaList;
    }
    
    /**
     * Reads a vendas_experiencias CSV file and returns a list of VendaExperiencia objects
     * @param filePath Path to the vendas_experiencias CSV file
     * @return ArrayList of VendaExperiencia objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<VendaExperiencia> vendasExperienciaFileReader(String filePath) throws FileNotFoundException {
        File vendaFile = new File(filePath);
        Scanner scanner = new Scanner(vendaFile);
        
        ArrayList<VendaExperiencia> vendaList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String experienciaId = splitLine[1];
            String tipoCliente = splitLine[2];
            
            // Assumimos clienteId vazio e números baseados no tipo de cliente
            String clienteId = "";
            int numAdultos = tipoCliente.equals("adulto") ? 1 : 0;
            int numCriancas = tipoCliente.equals("crianca") ? 1 : 0;
            
            VendaExperiencia venda = new VendaExperiencia(id, clienteId, experienciaId, numAdultos, numCriancas);
            vendaList.add(venda);
        }
        
        scanner.close();
        return vendaList;
    }
    
    /**
     * Reads a ratings_experiencias CSV file and returns a list of RatingExperiencia objects
     * @param filePath Path to the ratings_experiencias CSV file
     * @return ArrayList of RatingExperiencia objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<RatingExperiencia> ratingsExperienciaFileReader(String filePath) throws FileNotFoundException {
        File ratingFile = new File(filePath);
        Scanner scanner = new Scanner(ratingFile);
        
        ArrayList<RatingExperiencia> ratingList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String id = splitLine[0];
            String experienciaId = splitLine[1];
            double ratingExperiencia = Double.parseDouble(splitLine[2]);
            double ratingGuia = Double.parseDouble(splitLine[3]);
            
            RatingExperiencia rating = new RatingExperiencia(id, experienciaId, ratingExperiencia, ratingGuia);
            ratingList.add(rating);
        }
        
        scanner.close();
        return ratingList;
    }
    
    /**
     * Reads a logins CSV file and returns a list of User objects
     * @param filePath Path to the logins CSV file
     * @return ArrayList of User objects
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<User> usersFileReader(String filePath) throws FileNotFoundException {
        File userFile = new File(filePath);
        Scanner scanner = new Scanner(userFile);
        
        ArrayList<User> userList = new ArrayList<>();
        
        // Skip header
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(",");
            
            String username = splitLine[0];
            String password = splitLine[1];
            String roleStr = splitLine[2];
            
            // Convertendo a string de role para o enum UserRole
            User.UserRole role = User.UserRole.valueOf(roleStr);
            
            // Como o id não está no CSV, estamos usando o username como id
            String id = username;
            
            User user = new User(id, username, password, role);
            userList.add(user);
        }
        
        scanner.close();
        return userList;
    }
}
