package com.mediscreen.patient.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mediscreen.patient.domain.Patient;

/**
 * This class is used to valid constraints entities.
 *
 * @author Ludovic Tuccio
 */
public class ConstraintsValidator {

    private static final Logger LOGGER = LogManager
            .getLogger("ConstraintsValidator");

    /**
     * Method used to validate constraints for the adding patient method.
     *
     * @param patient
     */
    public static Patient checkValidPatient(final Patient patient) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Patient>> constraintViolations = validator
                .validate(patient);
        if (constraintViolations.size() > 0) {
            LOGGER.error(
                    "ERROR: a constraint was violated. Please check the informations entered.");
            for (ConstraintViolation<Patient> contraintes : constraintViolations) {
                LOGGER.error(contraintes.getRootBeanClass().getSimpleName()
                        + "." + contraintes.getPropertyPath() + " "
                        + contraintes.getMessage());
            }
            return null;
        }
        return patient;
    }

}
