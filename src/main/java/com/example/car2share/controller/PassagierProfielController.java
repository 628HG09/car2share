package com.example.car2share.controller;

import com.example.car2share.dto.IdInputDto;
import com.example.car2share.dto.PassagierProfielDto;
import com.example.car2share.dto.PasssagierProfielInputDto;
import com.example.car2share.model.PassagierProfiel;
import com.example.car2share.service.PassagierProfielService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PassagierProfielController {
//  @Autowired
//    PassagierProfielRepository repos;

    private final PassagierProfielService service;

    public PassagierProfielController(PassagierProfielService service) {
        this.service = service;
    }

    @PostMapping("/passagierprofielen")
    public ResponseEntity<Object> createPassengerProfile(@Valid @RequestBody PassagierProfielDto passagierProfielDto, BindingResult br) {
        //volgende code hoeven wij niet echt te snappen:
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);

        } else {


            //moet het Long id zijn?
            Long passagierProfielId = service.createPassengerProfile(passagierProfielDto);
            return new ResponseEntity<>(passagierProfielId, HttpStatus.CREATED);
        }
    }

    // Je ziet dat de return waarde van deze methode nu ResponseEntity<List<TelevisionDto>> is in plaats van <ResponseEntity<List<Television>>
    @GetMapping("/passagierprofielen")
    public ResponseEntity<List<PassagierProfielDto>> getAllPassagierProfielen(@RequestParam(value = "firstname", required = false) Optional<String> firstname) {

        List<PassagierProfielDto> dtos;

        if (firstname.isEmpty()) {

            // We halen niet direct uit de repository een lijst met Televisions, maar we halen uit de service een lijst met TelevisionDto's
            dtos = service.getAllPassagierProfielen();

        } else {
            // Dit is ook een service methode geworden in plaats van direct de repository aan te spreken.
            dtos = service.getAllPassagierProfielenByFirstname(firstname.get());

        }

        return ResponseEntity.ok().body(dtos);

    }

    // De return waarde is ook hier een TelevisionDto in plaats van een Television
    @GetMapping("/passagierprofielen/{id}")
    public ResponseEntity<PassagierProfielDto> getPassagierProfiel(@PathVariable("id") Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        PassagierProfielDto passagierProfiel = service.getPassagierProfielById(id);

        return ResponseEntity.ok().body(passagierProfiel);

    }

    // Hier veranderd niks aan de methode. We hebben niet meer de naam van de pathvariabele expliciet genoemd, omdat de
    // parameter-naam overeen komt met de naam van de pathvariabele.
    @DeleteMapping("/passagierprofielen/{id}")
    public ResponseEntity<Object> deletePassagierProfiel(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        service.deletePassagierProfiel(id);

        return ResponseEntity.noContent().build();

    }

    // Deze methode returned nu een ResponseEntity<TelevisionDto> in plaats van een ResponseEntity<Television> en deze
    // methode vraagt nu om een Long en een TelevisionInputDto in de parameters in plaats van een Long en een Television.
    @PutMapping("/passagierprofielen/{id}")
    public ResponseEntity<PassagierProfielDto> updatePassagierProfiel(@PathVariable Long id, @RequestBody PasssagierProfielInputDto newPassagierProfiel) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        PassagierProfielDto dto = service.updatePassagierProfiel(id, newPassagierProfiel);

        return ResponseEntity.ok().body(dto);
    }
    @PutMapping("/passagierprofielen/{id}/voertuig")
    public void assignVoertuigToPassagierProfiel(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        service.assignVoertuigToPassagierProfiel(id, input.id);
    }

//    @GetMapping("/passagierprofielen/{id}/ritverzoeken")
//    public ResponseEntity<Object> getRitverzoekenForPassagierProfiel(@PathVariable Long id ){
//        PassagierProfiel p = service.getPassagierProfiel(id);
//        return new ResponseEntity<>(p.getRitverzoeken(), HttpStatus.OK);
//    }

}

//   volgende GET is direct connected met repsoitory methode!
//   @GetMapping("/passagierprofielen/oud")
//    public ResponseEntity<Object> getOldFarts(){
//       return new ResponseEntity<>(repos.findByDobBefore(LocalDate.of(1980,1, 1)), HttpStatus.OK);
//   }


