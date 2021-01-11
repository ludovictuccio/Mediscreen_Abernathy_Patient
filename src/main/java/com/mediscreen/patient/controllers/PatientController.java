package com.mediscreen.patient.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.services.PatientService;

import io.swagger.annotations.ApiOperation;

@Controller
@Validated
@RequestMapping(value = "/patient")
public class PatientController {

    private static final Logger LOGGER = LogManager
            .getLogger("PatientController");

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "GET a list of all patients with the same lastname", notes = "Need param lastName - Return   or  bad request", response = Patient.class)
    @GetMapping("/list")
    public String home(final Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient/list";
    }

//    @GetMapping("/search")
//    public String search(final String lastName, final BindingResult result,
//            final Model model) {
//
//        if (!result.hasErrors()) {
//            List<Patient> patients = patientService
//                    .getAllPatientsWithLastname(lastName);
//            if (patients != null) {
//                model.addAttribute("patients", patients);
//                return "patients";
//            }
//        }
//        LOGGER.error("POST request FAILED for: /patient/validate");
//        return "patient/search";
//    }
//    @GetMapping("/validate")
//    public String validate(final String lastName, final BindingResult result,
//            final Model model) {
//
//        if (!result.hasErrors()) {
//            List<Patient> patientResults = patientService
//                    .getAllPatientsWithLastname(lastName);
//            if (patientResults != null) {
//                model.addAttribute("patients", patientResults);
//                return "redirect:/patient";
//            }
//        }
//        LOGGER.error("POST request FAILED for: /patient/validate");
//        return "patient/search";
//    }

    @ApiOperation(value = "GET the patient medical record", notes = "", response = Patient.class)
    @GetMapping("/medicalRecord/{patId}")
    public String getMedicalRecord(@PathVariable("patId") final Long patId,
            final Model model) {
        Patient patient = patientService.getPatientMedicalRecord(patId);

        if (patient == null) {
            LOGGER.error("Invalid patient Id: {}", patId);
            return "redirect:/patient/list";
        }
        model.addAttribute("patientSelected", patient);
        return "/patient/medicalrecord";
    }

}
