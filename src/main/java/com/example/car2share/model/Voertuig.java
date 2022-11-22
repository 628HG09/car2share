package com.example.car2share.model;

import javax.persistence.*;

@Entity
@Table(name = "Voertuigen")
public class Voertuig {

    @Id
    @GeneratedValue
    Long id;

    private String merk;
    private String model;
    private String kenteken;
    private String kleur;

    @OneToOne(mappedBy = "voertuig")
    PassagierProfiel passagierProfiel;

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

    public PassagierProfiel getPassagierProfiel() {
        return passagierProfiel;
    }

    public void setPassagierProfiel(PassagierProfiel passagierProfiel) {
        this.passagierProfiel = passagierProfiel;
    }
}
