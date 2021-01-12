package com.mediscreen.patient.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;

/**
 * PatientServiceImpl class.
 *
 * @author Ludovic Tuccio
 */
@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger("PatientServiceImpl");

    @Autowired
    private PatientRepository patientRepository;

    /**
     * {@inheritDoc}
     */
    public List<Patient> getAllPatientsWithSameLastname(final String lastName) {
        return patientRepository.findAllPatientByLastName(lastName);
    }

    /**
     * {@inheritDoc}
     */
    public Patient getPatientMedicalRecord(final Long patId) {
        return patientRepository.findById(patId).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateMedicalRecord(final Patient patient) {
        boolean isUpdated = false;

        Patient existingPatient = patientRepository.findById(patient.getId())
                .orElse(null);

        if (existingPatient == null) {
            LOGGER.error("Unknow patient with id: {}", patient.getId());
            return isUpdated;
        }
        existingPatient.setUseName(patient.getUseName());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setPhone(patient.getPhone());
        patientRepository.save(existingPatient);
        isUpdated = true;
        return isUpdated;
    }

}
