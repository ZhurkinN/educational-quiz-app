package ru.lobakina.educationalquizapp.support.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TestStudentsDTO {

    private Long id;
    private Date testDate;
    private Integer score;
    private Boolean isDone;
    private Long testId;
    private Long studentId;
}