package ru.lobakina.educationalquizapp.support.constants;

import java.util.List;

public interface RoleAccessEndpointConstantsKeeper {

    List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/js/**",
            "/css/**",
            "/",
            "/error",
            "/login",
            "/register"
    );
    List<String> USERS_PERMITTED_LIST = List.of(
            "/users",
            "/users/add",
            "/users/delete/{id}",
            "/users/update/{id}"
    );
    List<String> USERS_TEACHER_PERMITTED_LIST = List.of(
            "/users/students"
    );
    List<String> USERS_TEACHER_AND_ADMIN_PERMITTED_LIST = List.of(
            "/users/{id}",
            "/users/search"
    );

    List<String> GROUPS_ADMIN_PERMITTED_LIST = List.of(
            "/groups/add",
            "/groups/delete/{id}"
    );

    List<String> GROUPS_TEACHER_AND_ADMIN_PERMITTED_LIST = List.of(
            "/groups",
            "/groups/search"
    );

    List<String> TESTS_WHITE_LIST = List.of(
            "/tests"
    );
    List<String> TESTS_PERMITTED_LIST = List.of(
            "/tests/**"
    );

    List<String> SUBJECTS_PERMITTED_LIST = List.of(
            "/subjects/**"
    );

    List<String> ANSWERS_PERMITTED_LIST = List.of(
            "/answers/{id}",
            "answers/add/{id}",
            "answers/delete/{id}",
            "answers/add-variant/{id}"
    );

    List<String> QUESTION_PERMITTED_LIST = List.of(
            "/questions/{id}",
            "/questions/add/{id}",
            "/questions/delete/{id}",
            "/questions/update/{id}"
    );

    List<String> TEST_STUDENTS_TEACHER_PERMITTED_LIST = List.of(
            "/test-students/active/{id}",
            "/test-students/archive/{id}",
            "/test-students/start/{id}",
            "/test-students/start",
            "/test-students/return/{id}"
    );

    List<String> TEST_GROUPS_TEACHER_PERMITTED_LIST = List.of(
            "/test-groups/{id}",
            "/test-groups/results/{id}",
            "/test-groups/start/{id}",
            "/test-groups/delete/{id}"
    );
}
