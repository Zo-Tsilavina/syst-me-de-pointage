package com.projet1.sys_pointage.service;

import com.projet1.sys_pointage.traitement.Calendrier;

public class EmployeService {
    public int HeureDeTravailGardien(Calendrier calendrier){
        int nombreDeJourDeTravail = calendrier.getJoursDeTravaillesGardiens().size();
        return nombreDeJourDeTravail;
    }
}
