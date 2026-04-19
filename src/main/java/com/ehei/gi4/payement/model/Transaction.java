package com.ehei.gi4.payement.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private LocalDateTime date;
    private Double montantAvant;
    private Double montantApres;
    private Long remiseId;

    public Transaction(LocalDateTime date, Double montantAvant, Double montantApres, Long remiseId) {
        this.date = date;
        this.montantAvant = montantAvant;
        this.montantApres = montantApres;
        this.remiseId = remiseId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDate() { return date; }
    public Double getMontantAvant() { return montantAvant; }
    public Double getMontantApres() { return montantApres; }
    public Long getRemiseId() { return remiseId; }
}