package com.mediscreen.patient.services;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;

public interface ReportService {

    /**
     * Method used to get a patient dto.
     *
     * @param patient
     * @return PatientDto
     */
    PatientDto getPatientDto(final Patient patient);
}
