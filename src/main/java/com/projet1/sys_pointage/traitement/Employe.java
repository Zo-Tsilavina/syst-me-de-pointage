package com.projet1.sys_pointage.traitement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employe {
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private Date dateDEmbauche;
    private Date finDeContrat;
    private double montantDuSalaire;
    private Categories categories;
}
