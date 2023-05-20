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
public class QuestionDTO extends GenericDTO {

    private String text;
    private Long testId;
    private Long questionTypeId;
}
