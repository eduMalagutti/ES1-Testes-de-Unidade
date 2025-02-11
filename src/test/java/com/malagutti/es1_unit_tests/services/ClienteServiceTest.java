package com.malagutti.es1_unit_tests.services;

import com.malagutti.es1_unit_tests.entities.Cliente;
import com.malagutti.es1_unit_tests.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void testSaveCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@exemplo.com");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.saveCliente(cliente);
        assertNotNull(savedCliente);
        assertEquals("João", savedCliente.getNome());
        assertEquals("joao@exemplo.com", savedCliente.getEmail());
    }

    @Test
    public void testFindClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setEmail("maria@exemplo.com");

        when(clienteRepository.findById(1L)).thenReturn(cliente);

        Cliente foundCliente = clienteService.findClienteById(1L);
        assertNotNull(foundCliente);
        assertEquals(1L, foundCliente.getId());
        assertEquals("Maria", foundCliente.getNome());
    }

    @Test
    public void testFindClienteByIdNaoExistente() {
        when(clienteRepository.findById(99L)).thenReturn(null);

        Cliente foundCliente = clienteService.findClienteById(99L);
        assertNull(foundCliente);
    }
}
