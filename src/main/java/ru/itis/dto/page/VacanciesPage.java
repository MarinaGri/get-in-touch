package ru.itis.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dto.vacancy.Vacancy;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacanciesPage {

    private Integer page;

    private List<Vacancy> items;

    private Integer pages;

}
