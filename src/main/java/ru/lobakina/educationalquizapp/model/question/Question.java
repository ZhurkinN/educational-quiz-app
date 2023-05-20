package ru.lobakina.educationalquizapp.model.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.model.test.Test;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Question extends GenericModel {

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "test_id",
            nullable = false)
    private Test test;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "question_type_id",
            nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL)
    private Set<Answer> questionAnswers = new HashSet<>();
}
