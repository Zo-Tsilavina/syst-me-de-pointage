package com.projet1.sys_pointage.traitement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Categories {
    private TypeCategorie nom;
    private int heureParSemaine;
    private double salaireParSemaine;
    private double indemnite;
}