package ru.lobakina.educationalquizapp.model.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.user.User;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class TestStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "test_id",
            nullable = false)
    private Test test;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id",
            nullable = false)
    private User student;
    private Date testDate;
    private Integer score;

    @Column(columnDefinition = "boolean default FALSE")
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "test_group_id")
    private TestGroups testGroups;

    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL)
    private Set<StudentAnswer> studentAnswers = new HashSet<>();
}
