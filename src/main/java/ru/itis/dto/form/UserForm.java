package ru.itis.dto.form;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.annotations.Name;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    @NotNull
    @Name
    private String firstName;

    @NotNull
    @Name
    private String lastName;

}
