package com.exam.appointmentservice.controller;

import com.exam.appointmentservice.DoctorResponse;
import com.exam.appointmentservice.PatientResponse;
import com.exam.appointmentservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/check-doctor/{doctorId}")
    public DoctorResponse checkDoctor(
            @PathVariable Long doctorId
    ) {
        return appointmentService.checkDoctor(doctorId);
    }

    @GetMapping("/check-patitent/{patientId}")
    public PatientResponse checkPatient(
            @PathVariable Long patientId
    ){
        return appointmentService.checkPatient(patientId);
    }
}
