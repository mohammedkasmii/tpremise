package com.ehei.gi4.payement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FacturationService {

    @Autowired
    @Qualifier("remiseBDD") // change to "remiseVar" to switch strategy
    private IRemise remise;

    public Double calculerMontant(Double montant) {
        return montant - remise.calculerRemise(montant);
    }
}