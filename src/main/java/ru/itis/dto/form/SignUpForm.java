package ru.itis.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.annotations.Name;
import ru.itis.validation.annotations.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpForm {

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
    @Password
    private String password;

    private Boolean isAccepted;

}
