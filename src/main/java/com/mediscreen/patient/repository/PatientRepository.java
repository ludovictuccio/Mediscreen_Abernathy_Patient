package com.mediscreen.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.patient.domain.Patient;

/**
 * Patient repository class.
 *
 * @author Ludovic Tuccio
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Method repository used to find a patient by his lastName.
     *
     * @param lastName
     */
    Patient findByLastName(String lastName);

    /**
     * Method repository used to find all patients with the lastName.
     *
     * @param lastName
     */
    List<Patient> findAllPatientByLastName(String lastName);

}
