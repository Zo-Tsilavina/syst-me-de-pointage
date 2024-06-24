package com.projet1.sys_pointage;

import com.projet1.sys_pointage.operation.CalendrierEtSalaire;
import com.projet1.sys_pointage.traitement.Calendrier;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalendrierEtSalaire {

    @Test
    public void testCalendrierNormaux() throws ParseException {
        List<Date> joursFeries = createJoursFeriesList();

        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        Calendrier calendrier = calendrierEtSalaire.CalendrierNormaux(joursFeries);

        assertNotNull(calendrier, "Le calendrier des employés normaux ne doit pas être null.");

        assertEquals(20, calendrier.getJoursDeTravaillesNormaux().size(),
                "Le nombre de jours de travail normaux devrait être 18.");

        for (Date jourFerie : joursFeries) {
            assertFalse(calendrier.getJoursDeTravaillesNormaux().contains(jourFerie),
                    "Le jour férié " + jourFerie + " ne devrait pas être dans les jours de travail normaux.");
        }

        assertEquals(3, calendrier.getJoursFeries().size(), "Le nombre de jours fériés devrait être 3.");
    }

    @Test
    public void testCalendrierGardien() throws ParseException {
        List<Date> joursFeries = createJoursFeriesList();

        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        Calendrier calendrier = calendrierEtSalaire.CalendrierGardien(joursFeries);

        assertNotNull(calendrier, "Le calendrier des gardiens ne doit pas être null.");

        assertEquals(30, calendrier.getJoursDeTravaillesGardiens().size(),
                "Le nombre de jours de travail des gardiens devrait être 30.");

        for (Date jourFerie : joursFeries) {
            assertFalse(calendrier.getJoursDeTravaillesGardiens().contains(jourFerie),
                    "Le jour férié " + jourFerie + " ne devrait pas être dans les jours de travail des gardiens.");
        }

        assertEquals(3, calendrier.getJoursFeries().size(), "Le nombre de jours fériés devrait être 3.");
    }

    private List<Date> createJoursFeriesList() throws ParseException {
        List<Date> joursFeries = new ArrayList<>();
        SimpleDateFormat formeDeDate = new SimpleDateFormat("dd-MM-yyyy");

        joursFeries.add(formeDeDate.parse("17-06-2024"));
        joursFeries.add(formeDeDate.parse("25-06-2024"));
        joursFeries.add(formeDeDate.parse("26-06-2024"));

        return joursFeries;
    }
}

