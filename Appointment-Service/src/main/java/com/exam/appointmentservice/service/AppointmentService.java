package com.exam.appointmentservice.service;

import com.exam.appointmentservice.DoctorResponse;
import com.exam.appointmentservice.PatientResponse;
import com.exam.appointmentservice.exception.DoctorServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final RestTemplate restTemplate;

    @CircuitBreaker(
            name = "doctorServiceCB",
            fallbackMethod = "getDoctorFallback"
    )
    public DoctorResponse checkDoctor(Long doctorId) {

        return restTemplate.getForObject(
                "http://DOCTOR-SERVICE/api/v1/doctors/" + doctorId,
                DoctorResponse.class
        );
    }

    public DoctorResponse getDoctorFallback(
            Long doctorId,
            Throwable e
    ) {

        throw new DoctorServiceUnavailableException(e.getMessage());
    }


    @Retry(name = "patientRetry", fallbackMethod = "getPatientFallback")
    public PatientResponse checkPatient(Long patientId) {

        try {
            return restTemplate.getForObject(
                    "http://PATIENT-SERVICE/api/v1/patients/" + patientId,
                    PatientResponse.class
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PatientResponse getPatientFallback(
            Long patientId,
            Throwable e
    ) {

        System.out.println("========== PATIENT FALLBACK ==========");
        System.out.println("Exception: " + e.getClass().getName());
        System.out.println("Message: " + e.getMessage());

        return null;
    }
}
