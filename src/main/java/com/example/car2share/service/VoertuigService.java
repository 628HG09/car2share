package com.example.car2share.service;

import com.example.car2share.dto.VoertuigDto;
import com.example.car2share.model.Voertuig;
import com.example.car2share.repository.VoertuigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoertuigService {
    private final VoertuigRepository repos;

    public VoertuigService(VoertuigRepository repos){
        this.repos = repos;
    }

//    @Autowired
//    private VoertuigRepository repos;

    public VoertuigDto addVoertuig(VoertuigDto voertuigDto) {
        Voertuig rc =  transferToVoertuig(voertuigDto);
        repos.save(rc);
        return voertuigDto;
    }

    public Voertuig transferToVoertuig(VoertuigDto dto){
        var voertuig = new Voertuig();

        voertuig.setId(dto.getId());
        voertuig.setMerk(dto.getMerk());
        voertuig.setModel(dto.getModel());
        voertuig.setKenteken(dto.getKenteken());
        voertuig.setKleur(dto.getKleur());

        return voertuig;
    }

}
