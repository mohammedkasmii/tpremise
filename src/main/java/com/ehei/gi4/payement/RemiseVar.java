package com.ehei.gi4.payement;
import org.springframework.stereotype.Component;

@Component("remiseVar")
public class RemiseVar implements IRemise {

    @Override
    public Double calculerRemise(Double montant) {
        if (montant <= 1000) {
            return 0.0;                  // 0%
        } else if (montant <= 2000) {
            return montant * 0.01;       // 1%
        } else {
            return montant * 0.03;       // 3%
        }
    }
}
