package com.malagutti.es1_unit_tests.services;

import com.malagutti.es1_unit_tests.entities.Conta;
import com.malagutti.es1_unit_tests.repositories.ContaRepository;

public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta saveConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta findContaById(Long id) {
        return contaRepository.findById(id);
    }

    public boolean sacarDinheiro(Long id, double valor) {
        Conta conta = contaRepository.findById(id);
        if (conta != null && conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            contaRepository.save(conta);  // Atualiza a conta no repositório
            return true;  // Operação realizada com sucesso
        }
        return false;  // Falha no saque (saldo insuficiente ou conta não encontrada)
    }

    public boolean depositarDinheiro(Long id, double valor) {
        Conta conta = contaRepository.findById(id);
        if (conta != null && valor > 0) {
            conta.setSaldo(conta.getSaldo() + valor);
            contaRepository.save(conta);  // Atualiza a conta no repositório
            return true;  // Operação realizada com sucesso
        }
        return false;  // Falha no depósito (valor inválido ou conta não encontrada)
    }
}

