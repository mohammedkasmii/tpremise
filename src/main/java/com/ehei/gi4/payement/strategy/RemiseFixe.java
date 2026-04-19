package com.ehei.gi4.payement.strategy;
import org.springframework.stereotype.Component;

@Component("remiseFixe")
public class RemiseFixe implements IRemise {

    private static final double TAUX = 0.02; // 2% flat rate

    @Override
    public Double calculerRemise(Double montant) {
        return montant * TAUX;
    }
}
