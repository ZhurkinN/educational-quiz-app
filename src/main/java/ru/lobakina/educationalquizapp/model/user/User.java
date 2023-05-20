package ru.lobakina.educationalquizapp.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.lobakina.educationalquizapp.model.generic.GenericModel;
import ru.lobakina.educationalquizapp.model.test.Test;
import ru.lobakina.educationalquizapp.model.test.TestStudents;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User extends GenericModel {

    @Column(nullable = false,
            unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private String telephoneNumber;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id",
            nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "teacher",
            cascade = CascadeType.ALL)
    private Set<Test> teacherTests = new HashSet<>();

    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL)
    private Set<TestStudents> studentTests = new HashSet<>();

}
