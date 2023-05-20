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
values (now(), 'ivanov@mail.ru', 'Иван', 'Мужской', 'Иванов', 'ivanov', 'Иванович', '1', '89008909292', 1, 2);
insert into users(birth_date, email, first_name, gender, last_name, login, middle_name, password, telephone_number,
                  group_id, role_id)
values (now(), 'ivanov@mail.ru', 'Иван', 'Мужской', 'Иванов', 'qwe', 'Иванович', '$2y$10$a44nE.YPXrQ4IO0LuLRLZOfDCociRGbTjWbsrVB2fTMot3GAtYT2C', '89008909292', null, 1);

insert into test (deadline, description, duration, title, subject_id, teacher_id, question_quantity)
values (now(), 'best test', 30, 'Mathematics', 1, 2, 2);

insert into question (text, question_type_id, test_id)
values ('В каком году родился С.А. Пушкин?', 2, 1);
insert into question (text, question_type_id, test_id)
values ('Сколько килограмм в центнере?', 2, 1);
insert into question (text, question_type_id, test_id)
values ('Столица Италии?', 1, 1);

insert into answer (is_right, text, question_id)
values (true, 'Рим', 3);



