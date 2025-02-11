package com.malagutti.es1_unit_tests.repositories;

import com.malagutti.es1_unit_tests.entities.Conta;

import java.util.HashMap;
import java.util.Map;

public class ContaRepository {
    private Map<Long, Conta> contas = new HashMap<>();
    private Long idCounter = 1L;

    public Conta save(Conta conta) {
        if (conta.getId() == null) {
            conta.setId(idCounter++);
        }
        contas.put(conta.getId(), conta);
        return conta;
    }

    public Conta findById(Long id) {
        return contas.get(id);
    }
}
