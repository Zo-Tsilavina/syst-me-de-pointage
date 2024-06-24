package com.projet1.sys_pointage.operation;

import com.projet1.sys_pointage.traitement.Calendrier;
import com.projet1.sys_pointage.traitement.TypeCategorie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendrierEtSalaire {

    public Calendrier CreationDeCalendrier(List<Date> joursFeries) {
        List<Date> jourDeTravailleNormaux = new ArrayList<>();
        List<Date> joursDeTravailleGardien = new ArrayList<>();

        Calendar calendrier = Calendar.getInstance();
        calendrier.set(Calendar.MONTH, Calendar.JUNE);
        calendrier.set(Calendar.YEAR, 2024);

        for (int i = 1; i <= calendrier.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            calendrier.set(Calendar.DAY_OF_MONTH, i);
            int jourDeLaSemaine = calendrier.get(Calendar.DAY_OF_WEEK);

            joursDeTravailleGardien.add(calendrier.getTime());
            if (jourDeLaSemaine != Calendar.SATURDAY && jourDeLaSemaine != Calendar.SUNDAY) {
                jourDeTravailleNormaux.add(calendrier.getTime());

            }
        }

        Calendrier calendrier1 = new Calendrier(jourDeTravailleNormaux, joursFeries, joursDeTravailleGardien);
        return calendrier1;
    }

}
