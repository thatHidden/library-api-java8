package com.example.demo.service.validator;


import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientValidator {

    public void validate(Client client) {
        checkFullName(client.getFullName());
        checkBirthDate(client.getBirthDate());
    }

    private void checkFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 60) {
            throw new BadRequestException("bad full_name");
        }
    }

    private void checkBirthDate(LocalDate date) {
        LocalDate minDate = LocalDate.parse("1900-01-01");
        LocalDate maxDate = LocalDate.now().minusYears(14);

        if (date == null || date.isBefore(minDate) || date.isAfter(maxDate)) {
            throw new BadRequestException("bad date");
        }
    }
}
