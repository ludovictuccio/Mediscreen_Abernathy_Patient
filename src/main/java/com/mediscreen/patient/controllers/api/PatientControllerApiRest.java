package com.mediscreen.patient.controllers.api;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.services.PatientService;
import com.mediscreen.patient.services.ReportService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = "/api/patient")
public class PatientControllerApiRest {

    private static final Logger LOGGER = LogManager
            .getLogger("PatientControllerApiRest");

    @Autowired
    private PatientService patientService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "GET the list of all patients", notes = "Return response 200", response = Patient.class)
    @GetMapping("/list")
    public List<Patient> getAllMedicalRecords() {
        return patientRepository.findAll();
    }

    @ApiOperation(value = "GET a list of all patients with the same lastname", notes = "Need param lastName - Return a list (empty if no lastName found) - Return 200", response = Patient.class)
    @GetMapping("/searchPatient")
    public List<Patient> getAllPatientsWithSameLastname(
            @RequestParam final String lastName) {
        return patientService.getAllPatientsWithSameLastname(lastName);
    }

    @ApiOperation(value = "GET the patient medical record", notes = "Need param long 'patId' with patient's id - Return response 200", response = Patient.class)
    @GetMapping
    public Patient getMedicalRecord(@RequestParam final Long patId) {
        return patientService.getPatientMedicalRecord(patId);
    }

    @ApiOperation(value = "POST Add a new patient's medical record", notes = "Need Patient body - Return response 201 created or 400 bad request")
    @PostMapping
    public ResponseEntity<Patient> addPatient(
            @Valid @RequestBody final Patient patient) {
        Patient result = patientService.addPatient(patient);

        if (result != null) {
            return new ResponseEntity<Patient>(HttpStatus.CREATED);
        }
        LOGGER.error("POST request FAILED for: /api/patient");
        return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "PUT Update a patient's medical record", notes = "Need Patient body & param long 'patId' with patient's id - Return response 200 Ok or 400 bad request")
    @PutMapping
    public ResponseEntity<Boolean> updateMedicalRecord(
            @Valid @RequestBody final Patient patient,
            @RequestParam final Long patId) {
        boolean result = patientService.updateMedicalRecord(patient, patId);

        if (result == true) {
            return new ResponseEntity<Boolean>(HttpStatus.OK);
        }
        LOGGER.error("PUT request FAILED for: /api/patient");
        return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPatientPersonalInformations/{patId}")
    public com.mediscreen.patient.domain.dto.PatientDto getPatientPersonalInformations(
            @PathVariable("patId") Long patId) {
        Patient patient = patientService.getPatientMedicalRecord(patId);
        return reportService.getPatientDto(patient);

    }

}
