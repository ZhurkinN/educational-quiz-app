package ru.lobakina.educationalquizapp.model.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Answer extends GenericModel {

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "question_id",
            nullable = false)
    private Question question;
    private String text;
    private Boolean isRight;
}
