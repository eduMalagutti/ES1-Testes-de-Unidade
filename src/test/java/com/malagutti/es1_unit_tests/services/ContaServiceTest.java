package com.malagutti.es1_unit_tests.services;

import com.malagutti.es1_unit_tests.entities.Cliente;
import com.malagutti.es1_unit_tests.entities.Conta;
import com.malagutti.es1_unit_tests.repositories.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContaServiceTest {
    private ContaService contaService;
    private ContaRepository contaRepository;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        contaRepository = mock(ContaRepository.class);
        contaService = new ContaService(contaRepository);

        // Criando cliente para associar a conta
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setEmail("joao@exemplo.com");
    }

    @Test
    public void testSaveConta() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(1000.0);
        conta.setCliente(cliente);

        when(contaRepository.save(conta)).thenReturn(conta);

        Conta savedConta = contaService.saveConta(conta);
        assertNotNull(savedConta);
        assertEquals(1000.0, savedConta.getSaldo());
        assertEquals(1L, savedConta.getId());
    }

    @Test
    public void testFindContaById() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(500.0);
        conta.setCliente(cliente);

        when(contaRepository.findById(1L)).thenReturn(conta);

        Conta foundConta = contaService.findContaById(1L);
        assertNotNull(foundConta);
        assertEquals(500.0, foundConta.getSaldo());
    }

    @Test
    public void testFindContaByIdNaoExistente() {
        when(contaRepository.findById(99L)).thenReturn(null);

        Conta foundConta = contaService.findContaById(99L);
        assertNull(foundConta);
    }
}

