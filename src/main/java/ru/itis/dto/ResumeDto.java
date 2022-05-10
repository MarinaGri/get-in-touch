package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.annotations.MobileNumber;
import ru.itis.validation.annotations.Name;
import ru.itis.validation.annotations.Naming;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {

    private Long id;

    @NotNull
    @Naming
    private String position;

    @NotNull
    @Name
    private String firstName;

    @NotNull
    @Name
    private String lastName;

    @NotNull
    @Email(message = "{validation.email}")
    private String email;

    @NotNull
    @MobileNumber
    private String mobileNumber;

    private String currentCity;

    private LocalDate dateOfBirth;

    private Boolean gender;

    private Boolean experience;

    private List<String> skills;

    private UUID userId;
}
