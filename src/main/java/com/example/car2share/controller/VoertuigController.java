package com.example.car2share.controller;

import com.example.car2share.dto.VoertuigDto;
import com.example.car2share.service.VoertuigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoertuigController {
    private final VoertuigService service;

    @Autowired
    public VoertuigController(VoertuigService service) {
        this.service = service;
    }

    @PostMapping("/voertuigen")
    public VoertuigDto addVoertuig(@RequestBody VoertuigDto dto) {
        VoertuigDto dto1 = service.addVoertuig(dto);
        return dto1;
    }
}
