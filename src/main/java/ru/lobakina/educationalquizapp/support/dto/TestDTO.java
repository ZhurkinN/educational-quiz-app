package ru.lobakina.educationalquizapp.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.support.dto.generic.GenericDTO;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TestDTO extends GenericDTO {

    private String title;
    private String description;
    private Date deadline;
    private Integer duration;
    private Integer questionQuantity;
    private String subject;
    private Long teacher;
}
