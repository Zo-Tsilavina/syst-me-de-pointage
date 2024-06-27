package com.projet1.sys_pointage.service;

import com.projet1.sys_pointage.traitement.Calendrier;
import com.projet1.sys_pointage.traitement.Employe;
import com.projet1.sys_pointage.traitement.Pointage;

import java.util.Date;
import java.util.HashMap;

public class EmployeService {
    public int HeureDeTravailGardien(Pointage pointage, Employe employe){
        Pointage pointage1 = new Pointage();
        int nombre = pointage1.getPointageMap().size();

        return nombre;
    }
}
