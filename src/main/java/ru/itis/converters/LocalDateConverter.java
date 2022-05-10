package ru.itis.converters;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import ru.itis.exceptions.DateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateConverter implements Converter<String, LocalDate> {

    private final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public LocalDate convert(@NotNull String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException ex) {
            throw new DateFormatException("Only supported format: " + DATE_FORMAT, ex);
        }

    }
}
