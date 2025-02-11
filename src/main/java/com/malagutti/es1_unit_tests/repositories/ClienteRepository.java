package com.malagutti.es1_unit_tests.repositories;

import com.malagutti.es1_unit_tests.entities.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ClienteRepository {
    private Map<Long, Cliente> clientes = new HashMap<>();
    private Long idCounter = 1L;

    public Cliente save(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(idCounter++);
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public Cliente findById(Long id) {
        return clientes.get(id);
    }

    public Cliente findByEmail(String usuario) {
        return clientes.values().stream().filter(cliente -> cliente.getEmail().equals(usuario)).findFirst().orElse(null);
    }
}
