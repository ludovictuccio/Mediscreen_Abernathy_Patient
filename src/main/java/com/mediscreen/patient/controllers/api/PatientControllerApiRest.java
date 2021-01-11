package com.mediscreen.patient.controllers.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.patient.services.PatientService;

@RestController
@Validated
@RequestMapping(value = "/patient")
public class PatientControllerApiRest {

    private static final Logger LOGGER = LogManager
            .getLogger("PatientControllerApiRest");

    @Autowired
    private PatientService patientService;

//    @ApiOperation(value = "GET a list of all patients with the same lastname", notes = "Need param lastName - Return   or  bad request", response = Patient.class)
//    @GetMapping("/searchPatient")
//    public List<Patient> getAllUsers(@RequestParam String lastName) {
//
//        return patientService.getAllPatientsWithLastname(lastName);
//    }
//
//    @ApiOperation(value = "GET the patient medical record", notes = "", response = Patient.class)
//    @GetMapping("/medicalRecord")
//    public Optional<Patient> getAllUsers(@PathVariable Long patId) {
//
//        return patientService.getPatientMedicalRecord(patId);
//    }

}
