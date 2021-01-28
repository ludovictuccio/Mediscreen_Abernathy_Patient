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
     * Method used to get all patients with the same last name.
     *
     * @param lastName
     * @return a Patient list
     */
    List<Patient> getAllPatientsWithSameLastname(final String lastName);

    /**
     * Method used to get a patient's medical record with his id.
     *
     * @param id
     * @return patient
     */
    Patient getPatientMedicalRecord(final Long id);

    /**
     * Method used to add a new patient's medical record, and check that patient
     * not already exists.
     *
     * @param patient
     * @return patient
     */
    Patient addPatient(final Patient patient);

    /**
     * Method used to update a patient's medical record.
     *
     * @param patient
     * @return boolean isUpdated
     */
    boolean updateMedicalRecord(final Patient patient);

    /**
     * Method used to update a patient's medical record by patient's id.
     *
     * @param patient
     * @return boolean isUpdated
     */
    boolean updateMedicalRecordByPatId(final Patient patient, final Long patId);

}
