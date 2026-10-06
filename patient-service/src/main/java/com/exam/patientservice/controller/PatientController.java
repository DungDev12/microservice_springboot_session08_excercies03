package com.exam.patientservice.controller;

import com.exam.patientservice.entity.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatients(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(new Patient(id,"A","B",45));
    }
}
