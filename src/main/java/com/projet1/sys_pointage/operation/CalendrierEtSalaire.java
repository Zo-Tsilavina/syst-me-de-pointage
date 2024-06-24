package com.projet1.sys_pointage.operation;

import com.projet1.sys_pointage.traitement.Calendrier;
import com.projet1.sys_pointage.traitement.Categories;
import com.projet1.sys_pointage.traitement.TypeCategorie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendrierEtSalaire {

    public Calendrier CalendrierNormaux(List<Date> joursFeries) {
        List<Date> jourDeTravailleNormaux = new ArrayList<>();
        List<Date> joursDeTravailleGardien = new ArrayList<>();

        joursDeTravailleGardien = null;

        Calendar calendrier = Calendar.getInstance();
        calendrier.set(Calendar.MONTH, Calendar.JUNE);
        calendrier.set(Calendar.YEAR, 2024);

        for (int i = 1; i <= calendrier.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            calendrier.set(Calendar.DAY_OF_MONTH, i);
            int jourDeLaSemaine = calendrier.get(Calendar.DAY_OF_WEEK);

            if (jourDeLaSemaine != Calendar.SATURDAY && jourDeLaSemaine != Calendar.SUNDAY) {
                jourDeTravailleNormaux.add(calendrier.getTime());

            }
        }

        Calendrier calendrier1 = new Calendrier(jourDeTravailleNormaux, joursFeries, joursDeTravailleGardien);
        return calendrier1;
    }
    public Calendrier CalendrierGardien(List<Date> joursFeries) {
        List<Date> jourDeTravailleNormaux = new ArrayList<>();
        List<Date> joursDeTravailleGardien = new ArrayList<>();

        jourDeTravailleNormaux = null;

        Calendar calendrier = Calendar.getInstance();
        calendrier.set(Calendar.MONTH, Calendar.JUNE);
        calendrier.set(Calendar.YEAR, 2024);

        for (int i = 1; i <= calendrier.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            calendrier.set(Calendar.DAY_OF_MONTH, i);
            joursDeTravailleGardien.add(calendrier.getTime());
        }

        Calendrier calendrier1 = new Calendrier(jourDeTravailleNormaux, joursFeries, joursDeTravailleGardien);
        return calendrier1;
    }

    public void employeesCategorie(TypeCategorie typeCategorie){
        Calendrier calendrier = new Calendrier();
        List<Date> jourDeTravailNormal = calendrier.getJoursDeTravaillesNormaux();
        List<Date> jourDeTravailGardien = calendrier.getJoursDeTravaillesGardiens();
        switch (typeCategorie){
            case cadre_superieur :
                CalendrierNormaux(jourDeTravailNormal);
                break;
            case normal:
                CalendrierNormaux(jourDeTravailNormal);
                break;
            case chauffeur:
                CalendrierNormaux(jourDeTravailNormal);
                break;
            case gardien:
                CalendrierGardien(jourDeTravailGardien);
                break;
        }
    }
}
