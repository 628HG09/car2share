package com.example.car2share.controller;

import com.example.car2share.dto.PassagierProfielDto;
import com.example.car2share.dto.RitverzoekDto;
import com.example.car2share.model.Ritverzoek;
import com.example.car2share.service.RitverzoekService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ritverzoeken")
public class RitverzoekController {

    private final RitverzoekService service;

    public RitverzoekController(RitverzoekService service){
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Object>createRitverzoek(@RequestBody RitverzoekDto ritverzoekDto, BindingResult br){
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
            Long ritverzoekId = service.createRitverzoek(ritverzoekDto);
            return new ResponseEntity<>(ritverzoekId, HttpStatus.CREATED);
        }

//        @GetMapping("")
//
//
//        public ResponseEntity<List<Object>getAllRitverzoeken(@RequestParam(value = "firstname", required = false) Optional<String> firstname) {
//
//            List<RitverzoekDto> dtos;
//
//            if (firstname.isEmpty()) {
//
//                // We halen niet direct uit de repository een lijst met Televisions, maar we halen uit de service een lijst met TelevisionDto's
//                dtos = service.getAllRitverzoeken();
//
//            } else {
//                // Dit is ook een service methode geworden in plaats van direct de repository aan te spreken.
//                dtos = service.getAllRitverzoekenByFirstname(firstname.get());
//
//            }
//
//            return ResponseEntity.ok().body(dtos);
//
//        }
    }
}
