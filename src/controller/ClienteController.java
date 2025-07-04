package controller;

import java.util.List;
import java.util.Optional;

import models.Cliente;
import repositories.ClienteRepository;

/**
 * Controller for operations related to clients
 */
public class ClienteController {
    private ClienteRepository clienteRepository;
    
    /**
     * Constructor
     * @param clienteRepository Repository for Cliente data
     */
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    /**
     * Get all clientes
     * @return List of all clientes
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.getAllClientes();
    }
    
    /**
     * Get cliente by ID
     * @param id Cliente ID
     * @return Optional containing the cliente if found, empty otherwise
     */
    public Optional<Cliente> getClienteById(String id) {
        return clienteRepository.findById(id);
    }
    
    /**
     * Add a new cliente
     * @param id Cliente ID
     * @param nome Nome
     * @param email Email
     * @param nacionalidade Nacionalidade
     * @return true if added successfully, false otherwise
     */
    public boolean addCliente(String id, String nome, String email, String nacionalidade) {
        Cliente cliente = new Cliente(id, nome, email, nacionalidade);
        return clienteRepository.addCliente(cliente);
    }
}
