package ru.lobakina.educationalquizapp.support.dto.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericDTO {

    protected Long id;
    protected LocalDateTime createdWhen;
}
