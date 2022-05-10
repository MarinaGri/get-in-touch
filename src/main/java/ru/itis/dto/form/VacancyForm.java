package ru.itis.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyForm {

    private String text;

    private String experience;

    private String employment;

    private String schedule;

    private String salary;

    private Boolean onlyWithSalary;

}
