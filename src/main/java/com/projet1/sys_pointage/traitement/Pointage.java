package com.projet1.sys_pointage.traitement;


import com.projet1.sys_pointage.operation.CalendrierEtSalaire;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Pointage {
    private Employe employe;
    private static HashMap<Date, Boolean> pointageMap = new HashMap<>();

    public void EnregistrerPresence(Calendrier calendrier){
        List<Date> jourDeTravail = calendrier.getJoursDeTravaillesGardiens();
        for (Date jour: jourDeTravail){
            pointageMap.put(jour, true);
        }
    }

    public void EnregistrerAbsence(Date dateAbsence){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Date jour: pointageMap.keySet()){
            if (sdf.format(jour).equals(sdf.format(dateAbsence))){
                pointageMap.put(jour, false);
            }
        }
    }

    public void AfficherPointage(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Date date:  pointageMap.keySet()){
            System.out.println(sdf.format(date) + ": " + (pointageMap.get(date) ? "Present":"Absent") );
        }
    }
}