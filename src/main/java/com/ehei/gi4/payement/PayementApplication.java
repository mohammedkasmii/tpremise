package com.ehei.gi4.payement;

import com.ehei.gi4.payement.model.Taux;
import com.ehei.gi4.payement.model.Transaction;
import com.ehei.gi4.payement.services.FacturationService;
import com.ehei.gi4.payement.services.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class PayementApplication implements CommandLineRunner {

    private final FacturationService facturationService;
    private final TransactionService transactionService;

    public PayementApplication(FacturationService facturationService, TransactionService transactionService) {
        this.facturationService = facturationService;
        this.transactionService = transactionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PayementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        double montant = 1500.0;

        // 1. Calcul
        double montantApres = facturationService.calculerMontant(montant, Taux.VARIABLE);
        System.out.println("Montant avant : " + montant);
        System.out.println("Montant après : " + montantApres);

        // 2. Save
        Transaction t = new Transaction(LocalDateTime.now(), montant, montantApres, 2L);
        Long id = transactionService.save(t);
        System.out.println("Transaction sauvegardée, id = " + id);

        // 3. Update
        double nouveauMontant = 2000.0;
        double nouveauApres = facturationService.calculerMontant(nouveauMontant, Taux.VARIABLE);
        transactionService.update(id, nouveauMontant, nouveauApres);
        System.out.println("Transaction mise à jour");

        // 4. Delete
        transactionService.deleteById(id);
        System.out.println("Transaction supprimée");
    }
}