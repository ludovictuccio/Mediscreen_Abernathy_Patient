package com.mediscreen.patient.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Age calculator class, used to determine the patient's age.
 *
 * @author Ludovic Tuccio
 */
public class AgeCalculator {

    private static final Logger LOGGER = LogManager
            .getLogger(AgeCalculator.class);

    /**
     * Age calculation method.
     *
     * @param birthdate
     * @return age
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public static int ageCalculation(final String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(Constants.DATE_PATTERN);
        LocalDate personsBirthdate = LocalDate.parse(birthdate, formatter);
        LocalDate currentDate = LocalDate.now();

        int age = Period.between(personsBirthdate, currentDate).getYears();

        if (personsBirthdate.isAfter(currentDate)) {
            LOGGER.error("Patient's bithdate invalid (in the future)");
            throw new IllegalArgumentException(
                    "ERROR - Patient's bithdate in the future");
        } else if (age == 0) {
            LOGGER.debug("Baby with age less than 1 year");
            age++;
        } else {
            return age;
        }
        return age;
    }

}
