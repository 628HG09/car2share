package com.example.car2share.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Passagierprofielen")
public class PassagierProfiel {
    @Id
// als ik onderstaande GenerratioType.. weghaal begint de nieuwe cyfering vanaf 1
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstname;
    private String lastname;
    private LocalDate dob;
    @OneToOne
    Voertuig voertuig;

//    @OneToMany(mappedBy = "passagierProfiel")
//    @JsonIgnore
//    List<Ritverzoek>ritverzoeken;


//    Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Voertuig getVoertuig() {
        return voertuig;
    }

    public void setVoertuig(Voertuig voertuig) {
        this.voertuig = voertuig;
    }

    //    public List<Ritverzoek> getRitverzoeken() {
//        return ritverzoeken;
//    }

//    public void setRitverzoeken(List<Ritverzoek> ritverzoeken) {
//        this.ritverzoeken = ritverzoeken;
//    }
}
