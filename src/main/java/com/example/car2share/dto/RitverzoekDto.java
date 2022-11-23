package com.example.car2share.dto;

import java.time.LocalDate;

public class RitverzoekDto {

    public Long id;

    public String vertrekLocatie;
    public String bestemming;
    public LocalDate reisDatum;
    public int aantalPersonen;

    public RitverzoekDto(){
    }

    public RitverzoekDto(Long id, String vertrekLocatie, String bestemming, LocalDate reisDatum, int aantalPersonen) {
        this.id = id;
        this.vertrekLocatie = vertrekLocatie;
        this.bestemming = bestemming;
        this.reisDatum = reisDatum;
        this.aantalPersonen = aantalPersonen;
    }

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
}
