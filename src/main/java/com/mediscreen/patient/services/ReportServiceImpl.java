package com.mediscreen.patient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;
import com.mediscreen.patient.util.EntityToDtoConverter;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    /**
     *
     */
    public PatientDto getPatientDto(final Patient patient) {
        PatientDto patientDto = entityToDtoConverter
                .convertPatientToDto(patient);
        return patientDto;
    }
}
