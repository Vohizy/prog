package com.prog.prog.model;
import lombok.ToString;
import java.time.LocalDateTime;
public class Patrimoine  {
    private String possesseur;
    private LocalDatTime derniereModification;

    public String getPossesseur(){
        return possesseur
    }
    public void setPossesseur(){
        this.possesseur = possesseur;
    }
    public LocalDateTime getDerniereModification(){
        return derniereModification;
    }
    public void setDerniereModification(){
        this.derniereModification = derniereModification
    }
}
