package ru.itis.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.itis.validation.annotations.Naming;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostForm {

    @NotNull
    @Naming
    @Length(min= 5, max = 255, message = "{validation.length}")
    private String title;

    private String text;

}
