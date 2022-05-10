package ru.itis.dto.vacancy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    private String id;

    private String name;

    private Area area;

    private Schedule schedule;

    private Salary salary;

    private Snippet snippet;

}
