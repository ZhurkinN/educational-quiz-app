package ru.lobakina.educationalquizapp.model.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "questionType",
            cascade = CascadeType.ALL)
    private Set<Question> typeQuestions = new HashSet<>();
}
