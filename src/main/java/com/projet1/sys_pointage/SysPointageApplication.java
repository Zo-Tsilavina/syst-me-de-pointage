package com.projet1.sys_pointage;

import com.projet1.sys_pointage.traitement.Calendrier;
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
		Calendrier calendrier = calendrierEtSalaire.CreationDeCalendrier(joursFeries);
		afficherCalendrier(calendrier);
	}

	public static void afficherCalendrier(Calendrier calendrier) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		System.out.println("Jours de travail en juin 2024 pour les employes normaux :");
		for (Date date : calendrier.getJoursDeTravaillesNormaux()) {
			System.out.println(sdf.format(date));
		}

		System.out.println("Jours feries en juin 2024 :");
		for (Date date : calendrier.getJoursFeries()) {
			System.out.println(sdf.format(date));
		}

		System.out.println("Jours de travail en juin 2024 pour les gardiens : ");
		for (Date date : calendrier.getJoursDeTravaillesGardiens()){
			System.out.println(sdf.format(date));
		}
	}
}
