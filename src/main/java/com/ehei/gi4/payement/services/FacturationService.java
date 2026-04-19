package com.ehei.gi4.payement.services;

import com.ehei.gi4.payement.exceptions.RemiseException;
import com.ehei.gi4.payement.model.Taux;
import com.ehei.gi4.payement.strategy.IRemise;
import com.ehei.gi4.payement.strategy.RemiseFixe;
import com.ehei.gi4.payement.strategy.RemiseVar;
import org.springframework.stereotype.Service;

@Service
public class FacturationService {

    private final RemiseFixe remiseFixe;
    private final RemiseVar remiseVar;

    public FacturationService(RemiseFixe remiseFixe, RemiseVar remiseVar) {
        this.remiseFixe = remiseFixe;
        this.remiseVar = remiseVar;
    }

    public Double calculerMontant(Double montant, Taux taux) {
        if (montant <= 0) throw new RemiseException("Le montant doit être supérieur à 0");
        IRemise remise = switch (taux) {
            case FIXE -> remiseFixe;
            case VARIABLE -> remiseVar;
        };
        return montant - remise.calculerRemise(montant);
    }
}