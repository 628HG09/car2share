package com.example.car2share.service;

import com.example.car2share.dto.PassagierProfielDto;
import com.example.car2share.dto.PasssagierProfielInputDto;
import com.example.car2share.exception.RecordNotFoundException;
import com.example.car2share.model.PassagierProfiel;
import com.example.car2share.repository.PassagierProfielRepository;
import com.example.car2share.repository.RitverzoekRepository;
import com.example.car2share.repository.VoertuigRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassagierProfielService {
//dependecy injection nieuwe manier:
//    bij de testcode gaan we autowired gebruikenn
    private final PassagierProfielRepository passagierProfielRepository;

    private final VoertuigRepository voertuigRepository;

    private final VoertuigService voertuigService;

    private final RitverzoekRepository ritverzoekRepository;

    private final RitverzoekService ritverzoekService;

    public PassagierProfielService(PassagierProfielRepository passagierProfielRepository, VoertuigRepository voertuigRepository, VoertuigService voertuigService, RitverzoekRepository ritverzoekRepository, RitverzoekService ritverzoekService) {
        this.passagierProfielRepository = passagierProfielRepository;
        this.voertuigRepository = voertuigRepository;
        this.voertuigService = voertuigService;
        this.ritverzoekRepository = ritverzoekRepository;
        this.ritverzoekService = ritverzoekService;
    }
    // hier zit het probleem. Heeft te maken met de relatie en G&S in PassagierProfiel
//    public List<PassagierProfielDto> transferPassagierProfielListToDtoList(List<PassagierProfiel> passagierprofielen){
//        List<PassagierProfielDto> ppDtoList = new ArrayList<>();
//
//        for(PassagierProfiel pp : passagierprofielen) {
//            PassagierProfielDto dto = transferToDto(pp);
//            if(pp.getRitverzoeken() != null){
//                dto.setRitverzoekDto(ritverzoekService.transferToDto(pp.getRitverzoeken()));
//            }
//            if(pp.getVoertuig() != null){
//                dto.setVoertuigDto(voertuigService.transferToDto(pp.getVoertuig()));
//            }
//            ppDtoList.add(dto);
//        }
//        return ppDtoList;
//    }

    public Long createPassengerProfile(PassagierProfielDto passagierProfielDto) {
        PassagierProfiel passagierProfiel = new PassagierProfiel();

        passagierProfiel.setFirstname(passagierProfielDto.firstname);
        passagierProfiel.setLastname(passagierProfielDto.lastname);
        passagierProfiel.setDob(passagierProfielDto.dob);

        PassagierProfiel savedPassagierProfiel = this.passagierProfielRepository.save(passagierProfiel);

        return savedPassagierProfiel.getId();
// we tikken het hierboven uit omdat het een kleine Dto is, maar als er veel attributen zijn maken wij er een methode van
//  meestal in eindopdracht meth. : transferDtoToEntity(dto) en transferEntityToDto(entity)
    }

    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<PassagierProfielDto> getAllPassagierProfielen() {
        List<PassagierProfiel> passagierProfielList = passagierProfielRepository.findAll();
        List<PassagierProfielDto> passagierProfielDtoList = new ArrayList<>();

        for(PassagierProfiel pp : passagierProfielList) {
            PassagierProfielDto dto = transferToDto(pp);
            passagierProfielDtoList.add(dto);
        }
        return passagierProfielDtoList;
    }

    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<PassagierProfielDto> getAllPassagierProfielenByFirstname(String firstname) {
        List<PassagierProfiel> tvList = passagierProfielRepository.findAllPassagierProfielenByFirstnameEqualsIgnoreCase(firstname);
        List<PassagierProfielDto> tvDtoList = new ArrayList<>();

        for(PassagierProfiel tv : tvList) {
            PassagierProfielDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    // Deze methode is inhoudelijk hetzelfde als het was in de vorige opdracht. Wat verandert is, is dat we nu checken
    // op optional.isPresent in plaats van optional.isEmpty en we returnen een TelevisionDto in plaats van een Television.
    public PassagierProfielDto getPassagierProfielById(Long id) {
        Optional<PassagierProfiel> passagierProfielOptional = passagierProfielRepository.findById(id);
        if (passagierProfielOptional.isPresent()){
            PassagierProfiel passagierProfiel = passagierProfielOptional.get();
            return transferToDto(passagierProfiel);
        } else {
            throw new RecordNotFoundException("geen televisie gevonden");
        }
    }


//    public Optional<PassagierProfiel> getPassagierProfielById(Long id){
//        return repos.findById(id);
//
//        // de optional gaat zeker terugkomen in de eindopdracht
//    }

    public void deletePassagierProfiel(@RequestBody Long id) {

        passagierProfielRepository.deleteById(id);
    }


    // Deze methode is inhoudelijk niet veranderd, alleen staat het nu in de Service laag en worden er Dto's en
    // vertaal methodes gebruikt.
    public PassagierProfielDto updatePassagierProfiel(Long id, PasssagierProfielInputDto newPassagierProfiel) {

        Optional<PassagierProfiel> passagierProfielOptional = passagierProfielRepository.findById(id);
        if (passagierProfielOptional.isPresent()) {

            PassagierProfiel passagierProfiel1 = passagierProfielOptional.
                    get();


            passagierProfiel1.setFirstname(newPassagierProfiel.getFirstname());
            passagierProfiel1.setLastname(newPassagierProfiel.getLastname());
            passagierProfiel1.setDob(newPassagierProfiel.getDob());

            PassagierProfiel returnPassagierProfiel = passagierProfielRepository.save(passagierProfiel1);

            return transferToDto(returnPassagierProfiel);

        } else {

            throw new RecordNotFoundException("geen televisie gevonden");

        }
    }
        // Dit is de vertaal methode van TelevisionInputDto naar Television.
        public PassagierProfiel transferToPassagierProfiel(PasssagierProfielInputDto dto){
            var passagierProfiel = new PassagierProfiel();

            passagierProfiel.setFirstname(dto.getFirstname());
            passagierProfiel.setLastname(dto.getLastname());
            passagierProfiel.setDob(dto.getDob());

            return passagierProfiel;
        }

        // Dit is de vertaal methode van Television naar TelevisionDto
        public PassagierProfielDto transferToDto(PassagierProfiel passagierProfiel){
            PassagierProfielDto dto = new PassagierProfielDto();

            dto.setId(passagierProfiel.getId());
            dto.setFirstname(passagierProfiel.getFirstname());
            dto.setLastname(passagierProfiel.getLastname());
            dto.setDob(passagierProfiel.getDob());

            return dto;
        }

        //  vanaf hier nieuwe les:

    public PassagierProfiel getPassagierProfiel(Long id){
        Optional <PassagierProfiel> opp = passagierProfielRepository.findById(id);
        if (opp.isPresent()){
            return opp.get();
        }
        else {
            throw new RuntimeException("passagierprofiel niet gevonden");
        }
    }
    public void assignVoertuigToPassagierProfiel(Long id, Long voertuigId) {
        var optionalPassagierProfiel = passagierProfielRepository.findById(id);
        var optionalVoertuig = voertuigRepository.findById(voertuigId);

        if(optionalPassagierProfiel.isPresent() && optionalVoertuig.isPresent()) {
            var passagierProfiel = optionalPassagierProfiel.get();
            var voertuig = optionalVoertuig.get();

            passagierProfiel.setVoertuig(voertuig);
            passagierProfielRepository.save(passagierProfiel);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
