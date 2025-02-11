package com.malagutti.es1_unit_tests.services;

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

    @BeforeEach
    public void setUp() {
        contaRepository = mock(ContaRepository.class);
        contaService = new ContaService(contaRepository);
    }

    @Test
    public void testSaveConta() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(1000.0);

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

    // Teste para saque
    @Test
    public void testSacarDinheiroComSucesso() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(1000.0);

        when(contaRepository.findById(1L)).thenReturn(conta);
        when(contaRepository.save(conta)).thenReturn(conta);

        boolean resultado = contaService.sacarDinheiro(1L, 200.0);
        assertTrue(resultado);
        assertEquals(800.0, conta.getSaldo());
    }

    // Teste para saque com saldo insuficiente
    @Test
    public void testSacarDinheiroComSaldoInsuficiente() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(100.0);

        when(contaRepository.findById(1L)).thenReturn(conta);

        boolean resultado = contaService.sacarDinheiro(1L, 200.0);
        assertFalse(resultado);
        assertEquals(100.0, conta.getSaldo());
    }

    // Teste para depósito
    @Test
    public void testDepositarDinheiroComSucesso() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(500.0);

        when(contaRepository.findById(1L)).thenReturn(conta);
        when(contaRepository.save(conta)).thenReturn(conta);

        boolean resultado = contaService.depositarDinheiro(1L, 300.0);
        assertTrue(resultado);
        assertEquals(800.0, conta.getSaldo());
    }

    // Teste para depósito com valor inválido (negativo)
    @Test
    public void testDepositarDinheiroComValorInvalido() {
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setSaldo(500.0);

        when(contaRepository.findById(1L)).thenReturn(conta);

        boolean resultado = contaService.depositarDinheiro(1L, -50.0);
        assertFalse(resultado);
        assertEquals(500.0, conta.getSaldo());
    }

    // Teste para depósito em conta inexistente
    @Test
    public void testDepositarDinheiroEmContaNaoExistente() {
        when(contaRepository.findById(99L)).thenReturn(null);

        boolean resultado = contaService.depositarDinheiro(99L, 200.0);
        assertFalse(resultado);
    }
}

