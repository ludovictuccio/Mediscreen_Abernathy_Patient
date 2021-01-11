package com.mediscreen.patient.services;

import java.util.List;

import javax.validation.Validator;

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
     * Validator used to validate javax constraints in model classes (for add or
     * update a medical record).
     */
    private Validator validator;

    /**
     * {@inheritDoc}
     */
    public List<Patient> getAllPatientsWithLastname(final String lastName) {

        return patientRepository.findAllPatientByLastName(lastName);
    }

    /**
     * {@inheritDoc}
     */
    public Patient getPatientMedicalRecord(final Long patId) {

        return patientRepository.findById(patId).orElse(null);
    }
}
