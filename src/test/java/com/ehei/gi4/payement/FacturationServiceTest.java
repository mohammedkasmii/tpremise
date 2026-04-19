package com.ehei.gi4.payement;

import com.ehei.gi4.payement.exceptions.RemiseException;
import com.ehei.gi4.payement.model.Taux;
import com.ehei.gi4.payement.services.FacturationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacturationServiceTest {

    @Autowired
    private FacturationService facturationService;

    // Cas nominal — remise fixe
    @Test
    void testRemiseFixe() {
        Double result = facturationService.calculerMontant(1000.0, Taux.FIXE);
        assertEquals(980.0, result);
    }

    // Cas nominal — remise variable tranche 1% (1000-4999)
    @Test
    void testRemiseVariableTranche1() {
        Double result = facturationService.calculerMontant(1500.0, Taux.VARIABLE);
        assertEquals(1485.0, result);
    }

    // Cas nominal — remise variable tranche 5% (5000-9999)
    @Test
    void testRemiseVariableTranche2() {
        Double result = facturationService.calculerMontant(6000.0, Taux.VARIABLE);
        assertEquals(5700.0, result);
    }

    // Cas nominal — remise variable tranche 10% (10000+)
    @Test
    void testRemiseVariableTranche3() {
        Double result = facturationService.calculerMontant(12000.0, Taux.VARIABLE);
        assertEquals(10800.0, result);
    }

    // Cas nominal — remise variable tranche 0% (< 1000)
    @Test
    void testRemiseVariableTrancheZero() {
        Double result = facturationService.calculerMontant(500.0, Taux.VARIABLE);
        assertEquals(500.0, result);
    }

    // Levée d'exception — montant négatif
    @Test
    void testMontantNegatifLeveException() {
        assertThrows(RemiseException.class, () ->
                facturationService.calculerMontant(-100.0, Taux.FIXE)
        );
    }

    // Levée d'exception — montant zéro
    @Test
    void testMontantZeroLeveException() {
        assertThrows(RemiseException.class, () ->
                facturationService.calculerMontant(0.0, Taux.VARIABLE)
        );
    }

    // Vérification du message d'exception
    @Test
    void testMessageException() {
        RemiseException ex = assertThrows(RemiseException.class, () ->
                facturationService.calculerMontant(-50.0, Taux.FIXE)
        );
        assertEquals("Le montant doit être supérieur à 0", ex.getMessage());
    }
}