package ru.lobakina.educationalquizapp.model.enums;

import lombok.Getter;

@Getter
public enum Roles {

    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    ADMIN("Администратор");

    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }
}
