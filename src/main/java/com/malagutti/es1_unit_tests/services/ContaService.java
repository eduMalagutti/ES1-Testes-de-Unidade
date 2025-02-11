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
}

