insert into role(role_name)
values ('Преподаватель');
insert into role(role_name)
values ('Студент');
insert into role(role_name)
values ('Администратор');

insert into subject(title)
values ('Физика');
insert into subject(title)
values ('Математика');
insert into subject(title)
values ('Java');

insert into question_type(type_name)
values ('Обычный вопрос');
insert into question_type(type_name)
values ('Тестовый вопрос');

insert into groups(group_number, faculty)
values ('9413', 'FVT');

insert into users(birth_date, email, first_name, gender, last_name, login, middle_name, password, telephone_number,
                  group_id, role_id)
values (now(), 'ivanov@mail.ru', 'Иван', 'Мужской', 'Иванов', 'ivanov', 'Иванович',
        '$2y$10$J3FI9sLhOpIoBr.NA0uSPOi7tGxpf9R7SzHkY4.YKKKx3mrX31uhe', '89008909292', 1, 2);
insert into users(birth_date, email, first_name, gender, last_name, login, middle_name, password, telephone_number,
                  group_id, role_id)
values (now(), 'ivanov@mail.ru', 'Иван', 'Мужской', 'Иванов', 'qwe', 'Иванович',
        '$2y$10$a44nE.YPXrQ4IO0LuLRLZOfDCociRGbTjWbsrVB2fTMot3GAtYT2C', '89008909292', null, 1);
insert into users(birth_date, email, first_name, gender, last_name, login, middle_name, password, telephone_number,
                  group_id, role_id)
values (now(), 'petrov@mail.ru', 'Пётр', 'Мужской', 'Петров', 'petrov', 'Иванович', '2', '89008909292', 1, 2);

insert into test (deadline, description, duration, title, subject_id, teacher_id, question_quantity)
values ('2023-06-10', 'best test', 30, 'Дифференциальные уравнения', 1, 2, 3);

insert into test (deadline, description, duration, title, subject_id, teacher_id, question_quantity)
values ('2023-06-10', 'java core', 1, 'Программирование на Java', 3, 2, 3);

insert into question (text, question_type_id, test_id)
values ('В каком году родился С.А. Пушкин?', 2, 1);
insert into question (text, question_type_id, test_id)
values ('Сколько килограмм в центнере?', 2, 1);
insert into question (text, question_type_id, test_id)
values ('Столица Италии?', 1, 1);

insert into question (text, question_type_id, test_id)
values ('Самый скрытный модификатор доступа?', 2, 2);
insert into question (text, question_type_id, test_id)
values ('Сколько примитивных типов в языке Java?', 1, 2);
insert into question (text, question_type_id, test_id)
values ('В каком годы вышла Java 8?', 1, 2);


insert into answer (is_right, text, question_id)
values (true, '1799', 1);
insert into answer (is_right, text, question_id)
values (false, '1845', 1);
insert into answer (is_right, text, question_id)
values (false, '1800', 1);
insert into answer (is_right, text, question_id)
values (false, '1899', 1);

insert into answer (is_right, text, question_id)
values (false, '1000', 2);
insert into answer (is_right, text, question_id)
values (false, '10', 2);
insert into answer (is_right, text, question_id)
values (true, '100', 2);
insert into answer (is_right, text, question_id)
values (false, '1', 2);

insert into answer (is_right, text, question_id)
values (true, 'Рим', 3);

insert into answer (is_right, text, question_id)
values (true, 'private', 4);
insert into answer (is_right, text, question_id)
values (false, 'public', 4);
insert into answer (is_right, text, question_id)
values (false, 'protected', 4);
insert into answer (is_right, text, question_id)
values (false, 'package-default', 4);

insert into answer (is_right, text, question_id)
values (true, '7', 5);

insert into answer (is_right, text, question_id)
values (true, 'хз', 6);

insert into test_students (score, test_date, student_id, test_id, is_done)
values (2, now(), 1, 1, true);
insert into test_students (student_id, test_id)
values (1, 2);

insert into student_answer (answer, is_right, question_id, test_students_id)
values ('1799', true, 1, 1);
insert into student_answer (answer, is_right, question_id, test_students_id)
values ('100', true, 2, 1);
insert into student_answer (answer, is_right, question_id, test_students_id)
values ('Мадрид', false, 3, 1);
