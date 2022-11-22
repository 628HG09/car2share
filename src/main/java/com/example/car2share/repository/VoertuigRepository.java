package com.example.car2share.repository;

import com.example.car2share.model.Voertuig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoertuigRepository extends JpaRepository<Voertuig, Long> {
}
