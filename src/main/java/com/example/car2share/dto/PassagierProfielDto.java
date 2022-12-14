package com.example.car2share.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PassagierProfielDto {
    public Long id;
    @NotBlank
    public String firstname;
    @Size(min = 3, max = 128)
    public String lastname;
    @Past
    public LocalDate dob;
// deze geprobeerd:
    public VoertuigDto voertuigDto;

    //deze constructor geprobeerd:
    public PassagierProfielDto() {
    }

    public PassagierProfielDto(Long id, String firstname, String lastname, LocalDate dob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
    }

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

    public VoertuigDto getVoertuigDto() {
        return voertuigDto;
    }
    public void setVoertuigDto(VoertuigDto voertuigDto) {
        this.voertuigDto = voertuigDto;
    }
}
