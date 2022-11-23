package com.example.car2share.service;

import com.example.car2share.dto.VoertuigDto;
import com.example.car2share.model.Voertuig;
import com.example.car2share.repository.VoertuigRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoertuigService {
    private final VoertuigRepository voertuigRepository;

    public VoertuigService(VoertuigRepository voertuigRepository){
        this.voertuigRepository = voertuigRepository;
    }

//    @Autowired
//    private VoertuigRepository voertuigRepository;
public List<VoertuigDto> getAllVoertuigen() {
    List<VoertuigDto> dtos = new ArrayList<>();
    List<Voertuig> voertuigen = voertuigRepository.findAll();
    for (Voertuig rc : voertuigen) {
        dtos.add(transferToDto(rc));
    }
    return dtos;
}

    public VoertuigDto addVoertuig(VoertuigDto voertuigDto) {
        Voertuig rc =  transferToVoertuig(voertuigDto);
        voertuigRepository.save(rc);
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
    public VoertuigDto transferToDto(Voertuig voertuig){
        var dto = new VoertuigDto();

        dto.id = voertuig.getId();
        dto.merk = voertuig.getMerk();
        dto.model = voertuig.getModel();
        dto.kenteken = voertuig.getKenteken();
        dto.kleur = voertuig.getKleur();


        return dto;
    }

}
