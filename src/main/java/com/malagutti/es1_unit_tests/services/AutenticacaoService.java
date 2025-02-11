package com.malagutti.es1_unit_tests.services;

import com.malagutti.es1_unit_tests.repositories.ClienteRepository;

public class AutenticacaoService {
    private final ClienteRepository clienteRepository;

    public AutenticacaoService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public boolean autenticar(String usuario, String senha) {
        return senha.equals(clienteRepository.findByEmail(usuario).getSenha());
    }
}
