package ru.itis.services;

import ru.itis.dto.page.VacanciesPage;
import ru.itis.dto.form.VacancyForm;

public interface VacanciesService {

    VacanciesPage getVacanciesPage(VacancyForm vacancy, Integer page);

}
