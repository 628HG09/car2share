package com.example.car2share.dto;

public class VoertuigDto {

    public Long id;
    public String merk;
    public String model;
    public String kenteken;
    public String kleur;

    public  VoertuigDto(){
    }

    public VoertuigDto(Long id, String merk, String model, String kenteken, String kleur) {
        this.id = id;
        this.merk = merk;
        this.model = model;
        this.kenteken = kenteken;
        this.kleur = kleur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }
}
