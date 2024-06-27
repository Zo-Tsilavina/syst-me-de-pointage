package com.projet1.sys_pointage;

import com.projet1.sys_pointage.operation.CalendrierEtSalaire;
import com.projet1.sys_pointage.traitement.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public void pointageTest() throws ParseException{
        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        SimpleDateFormat formeDeDate = new SimpleDateFormat("dd-MM-yyyy");
        Calendrier calendrier2 = new Calendrier();
        Calendrier calendrier = calendrierEtSalaire.CalendrierGardien(calendrier2.getJoursFeries());

        Date rabeNaissance = formeDeDate.parse("15-04-1980");
        Date rabeDateDEmbauche = formeDeDate.parse("01-01-2020");
        Date rabeFinDeContrat = formeDeDate.parse("31-12-2025");
        Date rabeAbsence = formeDeDate.parse("27-06-2024");

        Categories rabeCategorie = new Categories(
                TypeCategorie.gardien,
                56,
                100.000,
                50.000
        );

        Employe rabe = new Employe(
                "Rabe",
                "Jamin",
                rabeNaissance,
                rabeDateDEmbauche,
                rabeFinDeContrat,
                400.000,
                rabeCategorie
        );

        Pointage rabePointage = new Pointage(rabe);
        rabePointage.EnregistrerPresence(calendrier);
        rabePointage.EnregistrerAbsence(rabeAbsence);

        HashMap<Date, Boolean> pointageMap = rabePointage.getPointageMap();

        assertFalse(pointageMap.get(rabeAbsence));

        for (Date jour : calendrier.getJoursDeTravaillesGardiens()) {
            if (!jour.equals(rabeAbsence)) {
                assertTrue(pointageMap.get(jour));
            }
        }

        rabePointage.AfficherPointage();
    }
}

