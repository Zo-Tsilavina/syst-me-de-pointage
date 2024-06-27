package com.projet1.sys_pointage;

import com.projet1.sys_pointage.service.EmployeService;
import com.projet1.sys_pointage.traitement.*;
import com.projet1.sys_pointage.operation.CalendrierEtSalaire;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SysPointageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysPointageApplication.class, args);
        try {
            pointageGardien();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public static void afficherCalendrierGardien(Calendrier calendrier){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Jours de travail en juin 2024 pour les gardiens : ");
        for (Date date : calendrier.getJoursDeTravaillesGardiens()){
            System.out.println(sdf.format(date));
        }
        System.out.println("Jours feries en juin 2024 :");
        for (Date date : calendrier.getJoursFeries()) {
            System.out.println(sdf.format(date));
        }
    }

    public static void pointageGardien()throws ParseException {
        List<Date> joursFeries = new ArrayList<>();
        SimpleDateFormat formeDeDate = new SimpleDateFormat("dd-MM-yyyy");

        try {
            joursFeries.add(formeDeDate.parse("17-06-2024"));
            joursFeries.add(formeDeDate.parse("25-06-2024"));
            joursFeries.add(formeDeDate.parse("26-06-2024"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CalendrierEtSalaire calendrierEtSalaire = new CalendrierEtSalaire();
        Calendrier calendrier1 = calendrierEtSalaire.CalendrierGardien(joursFeries);
        afficherCalendrierGardien(calendrier1);


        Date RabeNaissance = formeDeDate.parse("15-04-1980");
        Date RabeDateDEmbauche = formeDeDate.parse("01-01-2020");
        Date RabeFinDeContrat = formeDeDate.parse("31-12-2025");
        Date RabeAbsence = formeDeDate.parse("27-06-2024");

        Categories RabeCategorie = new Categories(
                TypeCategorie.gardien,
                56,
                100.000,
                50.000
        );
        Employe Rabe = new Employe(
                "Rabe",
                "Jamin",
                RabeNaissance,
                RabeDateDEmbauche,
                RabeFinDeContrat,
                400.000,
                RabeCategorie
        );
        Pointage RabePointage = new Pointage(Rabe);
        RabePointage.EnregistrerPresence(calendrier1);
        RabePointage.EnregistrerAbsence(RabeAbsence);
        RabePointage.AfficherPointage();

        EmployeService employeService = new EmployeService();
        System.out.println(employeService.HeureDeTravailGardien(RabePointage, Rabe));
    }
}
