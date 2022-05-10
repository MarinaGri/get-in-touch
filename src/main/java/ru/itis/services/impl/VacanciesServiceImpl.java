package ru.itis.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.ConnectionLostException;
import ru.itis.services.VacanciesService;
import ru.itis.dto.page.VacanciesPage;
import ru.itis.dto.form.VacancyForm;

import java.io.IOException;
import java.util.*;

@Service
public class VacanciesServiceImpl implements VacanciesService {
    private final String USER_AGENT = "Mozilla/5.0";

    @Value("${app.default-page-size}")
    private int defaultPageSize;

    @Value("${url.hh-ru.vacancies}")
    private String urlVacancies;

    @Override
    public VacanciesPage getVacanciesPage(VacancyForm vacancy, Integer page) {
        Gson gson = new Gson();
        JsonObject vacanciesData = getVacanciesData(vacancy, page);
        return gson.fromJson(vacanciesData, VacanciesPage.class);
    }

    private JsonObject getVacanciesData(VacancyForm vacancy, Integer page) throws ConnectionLostException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getUrl(vacancy, page))
                .addHeader("User-Agent", USER_AGENT)
                .build();

        Call call = client.newCall(request);

        try {
            Gson gson = new Gson();
            Response response = call.execute();
            return gson.fromJson(Objects.requireNonNull(response.body()).string(), JsonObject.class);
        } catch (IOException ex) {
            throw new ConnectionLostException("Can't access hh.ru", ex);
        }
    }

    private String getUrl(VacancyForm vacancy, Integer page) {
        page = page == null ? 0 : page;
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(urlVacancies)).newBuilder();

        urlBuilder.addQueryParameter("page", String.valueOf(page));
        urlBuilder.addQueryParameter("per_page", String.valueOf(defaultPageSize));

        if (vacancy != null) {
            urlBuilder.addQueryParameter("experience", vacancy.getExperience());
            urlBuilder.addQueryParameter("employment", vacancy.getEmployment());
            urlBuilder.addQueryParameter("schedule", vacancy.getSchedule());
            urlBuilder.addQueryParameter("text", vacancy.getText());
            urlBuilder.addQueryParameter("only_with_salary", String.valueOf(vacancy.getOnlyWithSalary()));
        }

        return urlBuilder.toString();
    }
}
