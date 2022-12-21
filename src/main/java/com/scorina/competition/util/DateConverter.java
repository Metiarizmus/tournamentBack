package com.scorina.competition.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DateConverter {

    public LocalDate convert(String dateStringImMs) throws ParseException {
        LocalDate ld = Instant.ofEpochMilli(Long.parseLong(dateStringImMs)).atZone(ZoneId.systemDefault()).toLocalDate();
        return ld;
    }

}
