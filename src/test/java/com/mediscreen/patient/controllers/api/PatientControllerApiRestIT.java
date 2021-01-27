package com.mediscreen.patient.controllers.api;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.services.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientControllerApiRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URI_BASE = "/api/patient";
    private static final String URI_LIST = "/api/patient/list";
    private static final String URI_SEARCH = "/api/patient/searchPatient";
    private static final String URI_GET_PATIENT_INFOS = "/api/patient/getPatientPersonalInformations/";

    @BeforeEach
    public void setUpPerTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        patientRepository.deleteAll();
    }

    @Test
    @Tag("/getPatientPersonalInformations/{patId}")
    @DisplayName("GET PatientPersonalInformations - OK - 200 - Existing patId")
    public void givenExistingPatientId_whenGetInfos_thenReturnOk()
            throws Exception {
        Patient patient = new Patient(1L, "Boyd", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI_GET_PATIENT_INFOS + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("/getPatientPersonalInformations/{patId}")
    @DisplayName("GET PatientPersonalInformations - Error - 404 - Nonexistant patId")
    public void givenNonExistantPatientId_whenGetInfos_thenReturnNotFound()
            throws Exception {
        Patient patient = new Patient(1L, "Boyd", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get(URI_GET_PATIENT_INFOS + "111")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    @Tag("list")
    @DisplayName("GET list - OK - 200")
    public void givenZeroUser_whenGetList_thenReturnOk() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI_LIST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("searchPatient")
    @DisplayName("GET searchPatient - OK - 200")
    public void givenExistingUser_whenGetHisLastname_thenReturnOk()
            throws Exception {
        Patient patient = new Patient("Boyd", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI_SEARCH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("lastName", "boyd"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("GET")
    @DisplayName("GET Patient - OK - 200")
    public void givenExistingPatient_whenGetWithHisId_thenReturnOk()
            throws Exception {
        Patient patient = new Patient("Generic1", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("patId", "1"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("PUT")
    @DisplayName("PUT Update patient - OK - 200")
    public void givenUser_whenUpdate_thenReturnOk() throws Exception {
        Patient patient = new Patient("Generic1", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);

        Patient patientToCreate = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "new address", "0238222222", "new usename");
        String jsonContent = objectMapper.writeValueAsString(patientToCreate);
        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent).param("patId", "1"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Tag("PUT")
    @DisplayName("PUT Update patient - ERROR - Bad id")
    public void aaaaa() throws Exception {
        Patient patient = new Patient("Generic1", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);

        Patient patientToCreate = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "new address", "0238222222", "new usename");
        String jsonContent = objectMapper.writeValueAsString(patientToCreate);
        this.mockMvc
                .perform(MockMvcRequestBuilders.put(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent).param("patId", "111"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @Tag("POST")
    @DisplayName("POST add patient - OK - 201")
    public void givenValidUser_whenAdd_thenReturnCreated() throws Exception {
        Patient patientToCreate = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans", "0101010101",
                "");
        String jsonContent = objectMapper.writeValueAsString(patientToCreate);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    @Tag("POST")
    @DisplayName("POST add patient - OK - 201 - Not the same birthdate")
    public void givenValidUser_whenAddWithOtherBirthdate_thenReturnCreated()
            throws Exception {
        Patient patient = new Patient("Generic1", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);

        Patient patientToCreate = new Patient("Generic1", "Patient1",
                "200-12-12", "M", "11 rue albert, 45000 Orleans", "0101010101",
                "");
        String jsonContent = objectMapper.writeValueAsString(patientToCreate);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    @Tag("POST")
    @DisplayName("POST add patient - Error - Patient already existing")
    public void givenUser_whenAddTheSame_thenReturnBadRequest()
            throws Exception {
        Patient patient = new Patient("Generic1", "Patient1", "1990-12-31", "M",
                "11 rue albert, 45000 Orleans", "0101010101", "");
        patientService.addPatient(patient);

        Patient patientToCreate = new Patient("Generic1", "Patient1",
                "1990-12-31", "M", "11 rue albert, 45000 Orleans", "0101010101",
                "");
        String jsonContent = objectMapper.writeValueAsString(patientToCreate);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post(URI_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest()).andReturn();
    }
}
