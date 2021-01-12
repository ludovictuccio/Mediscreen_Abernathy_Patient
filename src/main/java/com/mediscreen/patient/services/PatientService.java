package com.mediscreen.patient.services;

import java.util.List;

import com.mediscreen.patient.domain.Patient;

/**
 * PatientService interface class.
 *
 * @author Ludovic Tuccio
 */
public interface PatientService {

    /**
     * @param lastName
     * @return a Patient list
     */
    List<Patient> getAllPatientsWithSameLastname(final String lastName);

    /**
     * @param id
     * @return patient
     */
    Patient getPatientMedicalRecord(final Long id);

    /**
     * @param patient
     * @return boolean isUpdated
     */
    boolean updateMedicalRecord(final Patient patient);
}
