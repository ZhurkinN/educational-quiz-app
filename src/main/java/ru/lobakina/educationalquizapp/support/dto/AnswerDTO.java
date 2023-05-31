package ru.lobakina.educationalquizapp.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.support.dto.generic.GenericDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AnswerDTO extends GenericDTO {

    private Long questionId;
    private String text;
    private Boolean isRight;
}
