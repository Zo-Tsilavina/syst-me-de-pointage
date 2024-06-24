package com.projet1.sys_pointage;

import com.projet1.sys_pointage.operation.CalendrierEtSalaire;
import com.projet1.sys_pointage.traitement.Calendrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDeCalendrier {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreationDeCalendrier() throws ParseException {
        List<Date> joursFeries = new ArrayList<>();
        SimpleDateFormat formeDeDate = new SimpleDateFormat("dd-MM-yyyy");

        joursFeries.add(formeDeDate.parse("17-06-2024"));
        joursFeries.add(formeDeDate.parse("25-06-2024"));
        joursFeries.add(formeDeDate.parse("26-06-2024"));

        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        Calendrier calendrier = calendrierEtSalaire.CreationDeCalendrier(joursFeries);

        List<Date> joursDeTravailNormaux = calendrier.getJoursDeTravaillesNormaux();
        assertNotNull(joursDeTravailNormaux, "La liste des jours de travail normaux ne doit pas être null.");

        // Vérification du nombre de jours de travail normaux
        assertEquals(18, joursDeTravailNormaux.size(), "Le nombre de jours de travail normaux devrait être 18.");

        // Vérification que les jours fériés ne sont pas inclus dans les jours de travail normaux
        for (Date jour : joursFeries) {
            assertFalse(joursDeTravailNormaux.contains(jour), "Le jour férié " + jour + " ne devrait pas être dans les jours de travail normaux.");
        }

        List<Date> joursDeTravailGardiens = calendrier.getJoursDeTravaillesGardiens();
        assertNotNull(joursDeTravailGardiens, "La liste des jours de travail des gardiens ne doit pas être null.");
        assertEquals(30, joursDeTravailGardiens.size(), "Le nombre de jours de travail des gardiens devrait être 30.");

        // Vérification que les jours fériés ne sont pas inclus dans les jours de travail des gardiens
        for (Date jour : joursFeries) {
            assertFalse(joursDeTravailGardiens.contains(jour), "Le jour férié " + jour + " ne devrait pas être dans les jours de travail des gardiens.");
        }

        List<Date> joursFeriesResult = calendrier.getJoursFeries();
        assertNotNull(joursFeriesResult, "La liste des jours fériés ne doit pas être null.");
        assertEquals(3, joursFeriesResult.size(), "Le nombre de jours fériés devrait être 3.");
    }

    @Test
    public void testAfficherCalendrier() throws ParseException {
        List<Date> joursFeries = new ArrayList<>();
        SimpleDateFormat formeDeDate = new SimpleDateFormat("dd-MM-yyyy");

        joursFeries.add(formeDeDate.parse("17-06-2024"));
        joursFeries.add(formeDeDate.parse("25-06-2024"));
        joursFeries.add(formeDeDate.parse("26-06-2024"));

        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        Calendrier calendrier = calendrierEtSalaire.CreationDeCalendrier(joursFeries);

        SysPointageApplication.afficherCalendrier(calendrier);
    }
}
