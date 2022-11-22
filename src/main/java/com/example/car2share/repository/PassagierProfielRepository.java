package com.example.car2share.repository;


import com.example.car2share.model.PassagierProfiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PassagierProfielRepository extends JpaRepository<PassagierProfiel, Long> {

    //Methode laat passagierszien die geborden zijn voor... hoeven geen body. Wel in controller
    List<PassagierProfiel> findByDobBefore(LocalDate date);

    List<PassagierProfiel> findAllPassagierProfielenByFirstnameEqualsIgnoreCase(String firstname);
}
