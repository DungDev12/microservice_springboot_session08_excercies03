package com.exam.appointmentservice;

public record PatientResponse(
        Long id,
        String firstName,
        String lastName,
        int age
) {
}
