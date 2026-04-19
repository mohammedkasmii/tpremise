package com.ehei.gi4.payement.controllers;

import com.ehei.gi4.payement.Dtos.TransactionRequest;
import com.ehei.gi4.payement.Dtos.TransactionResponse;
import com.ehei.gi4.payement.model.Taux;
import com.ehei.gi4.payement.model.Transaction;
import com.ehei.gi4.payement.services.FacturationService;
import com.ehei.gi4.payement.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final FacturationService facturationService;
    private final TransactionService transactionService;

    public TransactionController(FacturationService facturationService, TransactionService transactionService) {
        this.facturationService = facturationService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Long> creerTransaction(@RequestBody TransactionRequest request) {
        double montantApres = facturationService.calculerMontant(request.getMontant(), Taux.VARIABLE);
        Transaction t = new Transaction(LocalDateTime.now(), request.getMontant(), montantApres, null);
        Long id = transactionService.save(t);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }
}
