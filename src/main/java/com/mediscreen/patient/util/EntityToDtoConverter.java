package com.mediscreen.patient.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.domain.dto.PatientDto;

@Component
public class EntityToDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method used to convert Patient entity to Patient DTO.
     *
     * @param patient
     * @return patientDto
     */
    public PatientDto convertPatientToDto(final Patient patient) {
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        patientDto.setId(patient.getId());
        patientDto.setLastName(patient.getLastName());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setBirthdate(patient.getBirthdate());
        patientDto.setSex(patient.getSex());
        return patientDto;
    }
}
