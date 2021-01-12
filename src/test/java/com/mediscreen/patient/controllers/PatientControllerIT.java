package com.mediscreen.patient.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.services.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    private static final String URI_GET_PATIENT_LIST = "/patient/list";
    private static final String URI_GET_VALID_PATIENT_MEDICAL_RECORD = "/patient/medicalRecord/1";
    private static final String URI_GET_INVALID_PATIENT_MEDICAL_RECORD = "/patient/medicalRecord/111";
    private static final String URI_GET_UPDATE_VALID_MEDICAL_RECORD = "/patient/update/1";
    private static final String URI_GET_UPDATE_INVALID_MEDICAL_RECORD = "/patient/update/111";
    private static final String URI_POST_UPDATE_VALID_MEDICAL_RECORD = "/patient/update/1";

    @BeforeEach
    public void setUpPerTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        patientRepository.deleteAll();
    }

    @Test
    @Tag("/patient/list")
    @DisplayName("Get - list")
    public void givenZeroPatient_whenGetList_thenreturnOk() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI_GET_PATIENT_LIST)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andReturn();
    }

    @Test
    @Tag("/patient/medicalRecord/{patId}")
    @DisplayName("Get - Medical record - OK - Valid id")
    public void givenPatient_whenGetMedicalRecordWithValidId_thenReturnOk()
            throws Exception {
        Patient patientGeneric1 = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans",
                "0101010101");
        patientGeneric1.setId(1L);
        patientRepository.save(patientGeneric1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET_VALID_PATIENT_MEDICAL_RECORD)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.view()
                        .name("/patient/medicalRecord"))
                .andReturn();
    }

    @Test
    @Tag("/patient/medicalRecord/{patId}")
    @DisplayName("Get - Medical record - Error - Invalid id")
    public void givenPatient_whenGetMedicalRecordWithInvalidId_thenReturnOk()
            throws Exception {
        Patient patientGeneric1 = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans",
                "0101010101");
        patientGeneric1.setId(1L);
        patientRepository.save(patientGeneric1);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET_INVALID_PATIENT_MEDICAL_RECORD)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/patient/list"))
                .andReturn();
    }

    @Test
    @Tag("/patient/update")
    @DisplayName("Get - Update - OK")
    public void givenOnePatient_whenUpdate_thenReturnUpdated()
            throws Exception {
        Patient patientGeneric1 = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans",
                "0101010101");
        patientGeneric1.setId(1L);
        patientRepository.save(patientGeneric1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET_UPDATE_VALID_MEDICAL_RECORD))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("patient"))
                .andExpect(MockMvcResultMatchers.view().name("patient/update"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    @Tag("/patient/update")
    @DisplayName("Get - Update - Error - Bad id")
    public void givenOnePatient_whenUpdateWithInvalidId_thenReturnNotUpdated()
            throws Exception {
        Patient patientGeneric1 = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans",
                "0101010101");
        patientGeneric1.setId(1L);
        patientRepository.save(patientGeneric1);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET_UPDATE_INVALID_MEDICAL_RECORD))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .redirectedUrl("/patient/medicalRecord"))
                .andReturn();
    }

    @Test
    @Tag("/patient/update")
    @DisplayName("Post - Update - OK")
    public void givenOnePatient_whenUpdate_thenReturnOk() throws Exception {
        Patient patientGeneric1 = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans",
                "0101010101");
        patientGeneric1.setId(1L);
        patientRepository.save(patientGeneric1);
        assertThat(patientRepository.findAll().get(0).getId()).isEqualTo(1l);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post(URI_POST_UPDATE_VALID_MEDICAL_RECORD)
                        .contentType(MediaType.ALL).param("useName", "Boyd")
                        .param("address", "11 rue olive")
                        .param("phone", "02568155"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers
                        .redirectedUrl("/patient/medicalRecord/1"))
                .andReturn();
    }

}
