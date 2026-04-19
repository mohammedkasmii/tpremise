package com.ehei.gi4.payement.Dtos;

import java.time.LocalDateTime;

public class TransactionResponse {
    private Double montantAvant;
    private Double montantApres;
    private LocalDateTime date;

    public TransactionResponse(Double montantAvant, Double montantApres, LocalDateTime date) {
        this.montantAvant = montantAvant;
        this.montantApres = montantApres;
        this.date = date;
    }

    public Double getMontantAvant() { return montantAvant; }
    public Double getMontantApres() { return montantApres; }
    public LocalDateTime getDate() { return date; }
}
