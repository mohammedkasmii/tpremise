package com.ehei.gi4.payement.services;

import com.ehei.gi4.payement.Dtos.TransactionResponse;
import com.ehei.gi4.payement.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Service
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    public TransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Transaction transaction) {
        String sql = "INSERT INTO TRANSACTION (date, montant_avant, montant_apres, remise_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(transaction.getDate()));
            ps.setDouble(2, transaction.getMontantAvant());
            ps.setDouble(3, transaction.getMontantApres());
            ps.setObject(4, transaction.getRemiseId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void update(Long id, Double montantAvant, Double montantApres) {
        String sql = "UPDATE TRANSACTION SET montant_avant = ?, montant_apres = ? WHERE id = ?";
        jdbcTemplate.update(sql, montantAvant, montantApres, id);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TRANSACTION WHERE id = ?", id);
    }

    public TransactionResponse findById(Long id) {
        String sql = "SELECT date, montant_avant, montant_apres FROM TRANSACTION WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new TransactionResponse(
                        rs.getDouble("montant_avant"),
                        rs.getDouble("montant_apres"),
                        rs.getTimestamp("date").toLocalDateTime()
                ), id);
    }
}