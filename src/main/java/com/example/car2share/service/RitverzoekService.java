package com.example.car2share.service;

import com.example.car2share.dto.RitverzoekDto;
import com.example.car2share.exception.RecordNotFoundException;
import com.example.car2share.model.PassagierProfiel;
import com.example.car2share.model.Ritverzoek;
import com.example.car2share.repository.PassagierProfielRepository;
import com.example.car2share.repository.RitverzoekRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RitverzoekService {
    private final RitverzoekRepository ritverzoekRepository;

    public RitverzoekService(RitverzoekRepository repos) {
        this.ritverzoekRepository = repos;
    }

    public Long createRitverzoek(RitverzoekDto ritverzoekDto) {
        Ritverzoek ritverzoek = new Ritverzoek();

        // BIJ ONDERSTAAND MOEST EIGENLIJK DE get weg!
        ritverzoek.setVertrekLocatie(ritverzoekDto.getVertrekLocatie());
        ritverzoek.setBestemming(ritverzoekDto.getBestemming());
        ritverzoek.setReisDatum(ritverzoekDto.getReisDatum());
        ritverzoek.setAantalPersonen(ritverzoekDto.getAantalPersonen());

        Ritverzoek savedRitverzoek = this.ritverzoekRepository.save(ritverzoek);

        return savedRitverzoek.getId();

    }
public List<RitverzoekDto> getAllRitverzoeken() {
    List<Ritverzoek> ritverzoeken = ritverzoekRepository.findAll();
    List<RitverzoekDto> dtos = new ArrayList<>();
    for (Ritverzoek rv : ritverzoeken) {
        dtos.add(transferToDto(rv));
    }
    return dtos;
}

    public RitverzoekDto getRitverzoek(long id) {
        Optional<Ritverzoek> ritverzoek = ritverzoekRepository.findById(id);
        if(ritverzoek.isPresent()) {
            RitverzoekDto rv = transferToDto(ritverzoek.get());
            return rv;
        } else {
            throw new RecordNotFoundException("Geen ritverzoek gevonden");
        }
    }

    public RitverzoekDto addRitverzoek(RitverzoekDto ritverzoekDto) {
        ritverzoekRepository.save(transferToRitverzoek(ritverzoekDto));
        return ritverzoekDto;
    }

    public void deleteRitverzoek(Long id) {
        ritverzoekRepository.deleteById(id);
    }

    public void updateRitverzoek(Long id, RitverzoekDto ritverzoekDto) {
        if(!ritverzoekRepository.existsById(id)) {
            throw new RecordNotFoundException("Geen ritverzoek gevonden");
        }
        Ritverzoek storedRitverzoek = ritverzoekRepository.findById(id).orElse(null);
        storedRitverzoek.setId(ritverzoekDto.getId());
        storedRitverzoek.setAantalPersonen(ritverzoekDto.getAantalPersonen());
        storedRitverzoek.setReisDatum(ritverzoekDto.getReisDatum());
        storedRitverzoek.setBestemming(ritverzoekDto.getBestemming());
        storedRitverzoek.setVertrekLocatie(ritverzoekDto.getVertrekLocatie());
        ritverzoekRepository.save(storedRitverzoek);
    }

    public Ritverzoek transferToRitverzoek(RitverzoekDto dto){
        Ritverzoek ritverzoek = new Ritverzoek();

        ritverzoek.setId(dto.getId());
        ritverzoek.setAantalPersonen(dto.getAantalPersonen());
        ritverzoek.setReisDatum(dto.getReisDatum());
        ritverzoek.setBestemming(dto.getBestemming());
        ritverzoek.setVertrekLocatie(dto.getVertrekLocatie());

        return ritverzoek;
    }

    public static RitverzoekDto transferToDto(Ritverzoek ritverzoek){
        var dto = new RitverzoekDto();

        dto.id = ritverzoek.getId();
        dto.aantalPersonen = ritverzoek.getAantalPersonen();
        dto.reisDatum = ritverzoek.getReisDatum();
        dto.bestemming = ritverzoek.getBestemming();
        dto.vertrekLocatie = ritverzoek.getVertrekLocatie();

        return dto;
    }


}
