package ru.lobakina.educationalquizapp.model.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lobakina.educationalquizapp.model.user.Group;
import ru.lobakina.educationalquizapp.model.user.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TestGroups {

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
    @JoinColumn(name = "group_id",
            nullable = false)
    private Group group;

    @Column(columnDefinition = "boolean default FALSE")
    private Boolean isDone = false;

    @Column(columnDefinition = "int default 0")
    private Integer studentsCompleted;
}
