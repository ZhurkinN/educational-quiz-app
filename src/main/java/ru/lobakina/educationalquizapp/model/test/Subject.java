package ru.lobakina.educationalquizapp.model.test;

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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "subject",
            cascade = CascadeType.ALL)
    private Set<Test> subjectTests = new HashSet<>();
}
