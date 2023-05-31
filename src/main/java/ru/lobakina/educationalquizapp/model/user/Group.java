package ru.lobakina.educationalquizapp.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.model.test.TestGroups;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "groups")
public class Group extends GenericModel {

    @Column(nullable = false)
    private String groupNumber;
    private String faculty;

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL)
    private Set<User> groupStudents = new HashSet<>();

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL)
    private Set<TestGroups> groupTests = new HashSet<>();
}
