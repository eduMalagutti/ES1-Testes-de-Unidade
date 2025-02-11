package com.malagutti.es1_unit_tests.services;

import com.malagutti.es1_unit_tests.entities.Cliente;
import com.malagutti.es1_unit_tests.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutenticacaoServiceTest {
    private AutenticacaoService autenticacaoService;
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        autenticacaoService = new AutenticacaoService(clienteRepository);
    }

    @Test
    public void testAutenticarComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setEmail("usuario@exemplo.com");
        cliente.setSenha("senha123");

        when(clienteRepository.findByEmail("usuario@exemplo.com")).thenReturn(cliente);

        boolean resultado = autenticacaoService.autenticar("usuario@exemplo.com", "senha123");
        assertTrue(resultado);
    }

    @Test
    public void testAutenticarComFalhaSenhaErrada() {
        Cliente cliente = new Cliente();
        cliente.setEmail("usuario@exemplo.com");
        cliente.setSenha("senha123");

        when(clienteRepository.findByEmail("usuario@exemplo.com")).thenReturn(cliente);

        boolean resultado = autenticacaoService.autenticar("usuario@exemplo.com", "senhaErrada");
        assertFalse(resultado);
    }

    @Test
    public void testAutenticarComUsuarioNaoExistente() {
        when(clienteRepository.findByEmail("usuario@exemplo.com")).thenThrow(new RuntimeException("Usuário não encontrado"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> autenticacaoService.autenticar("usuario@exemplo.com", "senha123"));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }
}


