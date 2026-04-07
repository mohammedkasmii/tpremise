package com.ehei.gi4.payement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayementApplication implements CommandLineRunner {

    private final FacturationService facturationService;

    public PayementApplication(FacturationService facturationService) {
        this.facturationService = facturationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PayementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        double[] montants = {500.0, 1500.0, 6000.0, 12000.0};

        for (double montant : montants) {
            System.out.println("Montant brut  : " + montant);
            System.out.println("Montant final : " + facturationService.calculerMontant(montant));
            System.out.println("----------------------------");
        }
    }
}