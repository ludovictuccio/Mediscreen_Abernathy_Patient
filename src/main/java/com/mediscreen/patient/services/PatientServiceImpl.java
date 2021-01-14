package com.mediscreen.patient.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.util.ConstraintsValidator;

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
    public Patient addPatient(final Patient patient) {
        if (ConstraintsValidator.checkValidPatient(patient) == null) {
            return null;
        }
        List<Patient> allPatientsWithSameLastNameList = getAllPatientsWithSameLastname(
                patient.getLastName());

        if (!allPatientsWithSameLastNameList.isEmpty()) {

            for (Patient patientRetrieved : allPatientsWithSameLastNameList) {
                if (patientRetrieved.getFirstName().toUpperCase()
                        .contentEquals(patient.getFirstName().toUpperCase())
                        && patientRetrieved.getSex().toUpperCase()
                                .contentEquals(patient.getSex().toUpperCase())
                        && patientRetrieved.getBirthdate()
                                .contentEquals(patient.getBirthdate())) {
                    LOGGER.error(
                            "ERROR: this patient's medical record already exists.");
                    return null;
                }
            }
        }
        // Set id with last id added in db +1
        List<Patient> allPatients = patientRepository.findAll();
        if (allPatients.size() > 0) {
            Patient lastPatientAdded = allPatients.get(allPatients.size() - 1);
            patient.setId(lastPatientAdded.getId() + 1);
        } else {
            patient.setId(1l);
        }
        patientRepository.save(patient);
        return patient;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateMedicalRecord(final Patient patient,
            final Long patId) {
        boolean isUpdated = false;

        Patient existingPatient = patientRepository.findById(patId)
                .orElse(null);

        if (existingPatient == null) {
            LOGGER.error("Unknow patient with id: {}", patId);
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
