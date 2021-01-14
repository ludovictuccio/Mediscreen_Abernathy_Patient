package com.mediscreen.patient.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.services.PatientService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    private static final Logger LOGGER = LogManager
            .getLogger("PatientController");

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "GET a list of all patients", notes = "THYMELEAF - Return response 200")
    @GetMapping("/list")
    public String home(final Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient/list";
    }

    @ApiOperation(value = "GET the patient medical record", notes = "THYMELEAF - Need PathVariable with patient id. Return response 200")
    @GetMapping("/medicalRecord/{patId}")
    public String getMedicalRecord(@PathVariable("patId") final Long patId,
            final Model model) {
        Patient patient = patientService.getPatientMedicalRecord(patId);

        if (patient == null) {
            LOGGER.error("Invalid patient Id: {}", patId);
            return "redirect:/patient/list";
        }
        model.addAttribute("patientSelected", patient);
        return "/patient/medicalRecord";
    }

    @ApiOperation(value = "ADD Patient (get)", notes = "THYMELEAF - Add new patient's medical record")
    @GetMapping("/add")
    public String addPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }

    @ApiOperation(value = "VALIDATE add Patient (post)", notes = "THYMELEAF - Validate - save/add the new Patient")
    @PostMapping("/validate")
    public String validate(@Valid final Patient patient,
            final BindingResult result, final Model model) {

        if (!result.hasErrors()) {
            Patient patientToAdd = patientService.addPatient(patient);
            model.addAttribute("patient", patientToAdd);
            return "redirect:/patient/list";
        }
        LOGGER.info("POST request FAILED for: /patient/validate");
        return "patient/add";
    }

    @ApiOperation(value = "UPDATE Patient personals informations (Get)", notes = "THYMELEAF - Get a Patient by id and retrieve to update it. Need PathVariable with patient id. Return response 200 or 404 not found")
    @GetMapping("/update/{patId}")
    public String showUpdateForm(@PathVariable("patId") final Long patId,
            final Model model) {
        Patient patient = patientService.getPatientMedicalRecord(patId);

        if (patient == null) {
            LOGGER.error("Invalid patient Id: {}", patId);
            return "redirect:/patient/medicalRecord";
        }
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @ApiOperation(value = "UPDATE Patient personals informations (post)", notes = "THYMELEAF - Update the Patient. Only usename, address and phone can be changed. Return response 200")
    @PostMapping("/update/{patId}")
    public String updatePatient(@PathVariable("patId") final Long patId,
            final Patient patient, final BindingResult result,
            final Model model) {
        if (result.hasErrors()) {
            LOGGER.info("POST request FAILED for: /patient/update/{patId}");
            return "patient/update";
        }
        patient.setId(patId);
        patientService.updateMedicalRecord(patient, patId);
        model.addAttribute("patient", patient);
        return "redirect:/patient/medicalRecord/{patId}";
    }

}
