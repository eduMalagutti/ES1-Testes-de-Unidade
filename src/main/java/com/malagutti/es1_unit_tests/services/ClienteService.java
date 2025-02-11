package com.malagutti.es1_unit_tests.services;

import java.util.HashMap;
import java.util.Map;

import com.malagutti.es1_unit_tests.entities.Cliente;
import com.malagutti.es1_unit_tests.repositories.ClienteRepository;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id);
    }
}

