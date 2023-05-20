package ru.lobakina.educationalquizapp.model.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.model.user.User;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Test extends GenericModel {

    private String title;
    private String description;
    private Date deadline;
    private Integer duration;

//    @Column(columnDefinition = "boolean default FALSE")
//    private Boolean isDone = false;
//
//    @Column(columnDefinition = "boolean default FALSE")
//    private Boolean isStarted = false;

    @Column(columnDefinition = "int default 0")
    private Integer questionQuantity;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id",
            nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "teacher_id",
            nullable = false)
    private User teacher;

    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL)
    private Set<TestStudents> testStudents = new HashSet<>();

    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL)
    private Set<TestGroups> testGroups = new HashSet<>();
}
