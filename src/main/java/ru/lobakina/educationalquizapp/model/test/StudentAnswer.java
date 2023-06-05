package ru.lobakina.educationalquizapp.model.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.question.Question;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "test_students_id",
            nullable = false)
    private TestStudents test;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "question_id",
            nullable = false)
    private Question question;
    private String answer;
    private Boolean isRight;
}
