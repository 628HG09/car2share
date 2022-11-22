package com.example.car2share.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Ritverzoeken")
public class Ritverzoek {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String vertrekLocatie;
    private String bestemming;
    private LocalDate reisDatum;
    private int aantalPersonen;

    @ManyToOne
            //evt. joinColumn:
            @JoinColumn(name = "passagierProfiel_id")
    PassagierProfiel passagierProfiel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVertrekLocatie() {
        return vertrekLocatie;
    }

    public void setVertrekLocatie(String vertrekLocatie) {
        this.vertrekLocatie = vertrekLocatie;
    }

    public String getBestemming() {
        return bestemming;
    }

    public void setBestemming(String bestemming) {
        this.bestemming = bestemming;
    }

    public LocalDate getReisDatum() {
        return reisDatum;
    }

    public void setReisDatum(LocalDate reisDatum) {
        this.reisDatum = reisDatum;
    }

    public int getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public PassagierProfiel getPassagierProfiel() {
        return passagierProfiel;
    }

    public void setPassagierProfiel(PassagierProfiel passagierProfiel) {
        this.passagierProfiel = passagierProfiel;
    }
}
