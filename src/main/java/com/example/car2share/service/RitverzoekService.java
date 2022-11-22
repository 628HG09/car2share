package com.example.car2share.service;

import com.example.car2share.dto.RitverzoekDto;
import com.example.car2share.model.PassagierProfiel;
import com.example.car2share.model.Ritverzoek;
import com.example.car2share.repository.PassagierProfielRepository;
import com.example.car2share.repository.RitverzoekRepository;
import org.springframework.stereotype.Service;

@Service
public class RitverzoekService {
    private final RitverzoekRepository repos;

    public RitverzoekService(RitverzoekRepository repos) {
        this.repos = repos;
    }

    public Long createRitverzoek(RitverzoekDto ritverzoekDto) {
        Ritverzoek ritverzoek = new Ritverzoek();

        // BIJ ONDERSTAAND MOEST EIGENLIJK DE get weg!
        ritverzoek.setVertrekLocatie(ritverzoekDto.getVertrekLocatie());
        ritverzoek.setBestemming(ritverzoekDto.getBestemming());
        ritverzoek.setReisDatum(ritverzoekDto.getReisDatum());
        ritverzoek.setAantalPersonen(ritverzoekDto.getAantalPersonen());

        Ritverzoek savedRitverzoek = this.repos.save(ritverzoek);

        return savedRitverzoek.getId();

    }


}
