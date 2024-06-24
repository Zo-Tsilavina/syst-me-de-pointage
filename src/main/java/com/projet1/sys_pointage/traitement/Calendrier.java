package com.projet1.sys_pointage.traitement;

import lombok.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Calendrier {
    private List<Date> joursDeTravaillesNormaux;
    private List<Date> joursFeries;
    private List<Date> joursDeTravaillesGardiens;
    public Calendrier() {
        this.joursDeTravaillesNormaux = new ArrayList<>();
        this.joursFeries = new ArrayList<>();
        this.joursDeTravaillesGardiens = new ArrayList<>();
    }
}

