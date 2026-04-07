package com.ehei.gi4.payement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("remiseBDD")
public class RemiseBDD implements IRemise {

    private final JdbcTemplate jdbcTemplate;

    public RemiseBDD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Double calculerRemise(Double montant) {
        String sql = "SELECT taux FROM Remise WHERE ? BETWEEN montant_min AND montant_max";

        List<Double> results = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getDouble("taux"),
                montant
        );

        double taux = results.isEmpty() ? 0.0 : results.get(0);
        return montant * taux;
    }
}