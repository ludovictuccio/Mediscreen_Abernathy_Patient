package com.mediscreen.patient.services;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;

public interface ReportService {

    PatientDto getPatientDto(final Patient patient);
}
