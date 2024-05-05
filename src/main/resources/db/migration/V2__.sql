
insert into users (registration, user_type, name, email, password, icon, slug, phone, about, cpf, active, birth_date, user_role, cv_lattes, website)
values ('123456', 'STUDENT', 'RAUL MARQUES BAPTISTA', 'rauzito@gmail.com', '$2a$10$RI9eho5vGJWCqO4lPZyUQ.xPIM09qeEeBcRgrSNdzAFMVV8PiXjqa', '/rauzito/profile-pic/123123', 'raul-marques-oliveira-neto-santos-1', '81988221213', 'Aluno da Escola Técnica Estadual Miguel Baptista', '123.212.222.11', true, '2024-05-01 11:56:19.000000', 1, '', '')
on conflict do nothing;

insert into users (registration, user_type, name, email, password, icon, slug, phone, about, cpf, active, birth_date, user_role, cv_lattes, website)
values ('334412', 'TEACHER', 'JOSÉ MARIA', 'ze@gmail.com', '$2a$10$RI9eho5vGJWCqO4lPZyUQ.xPIM09qeEeBcRgrSNdzAFMVV8PiXjqa', '/ze/profile-pic/123123', 'jose-maria-1', '81988221513', 'Professor efetivado da Escola Técnica Estadual Miguel Baptista', '123.212.212.11', true, '2024-05-02 11:56:19.000000', 0, '', '')
on conflict do nothing;

insert into groups (id, name, description, image, creator_registration)
values ('SHA1022312', 'MATEMÁTICA BÁSICA APLICADA', 'Turma de matemática básica', 'icon', '334412')
on conflict do nothing;


insert into groups_users (group_id, user_id)
values ('SHA1022312', '123456')
on conflict do nothing;


insert into questions (id, description, image, tip, difficulty, source, author_id)
values ('QUESTION-ID-001', 'Questão de teste', 'image', 'Dica número 1', 'EASY', 'www.wikipedia.com.br', '334412')
on conflict do nothing;

insert into questions (id, description, image, tip, difficulty, source, author_id)
values ('QUESTION-ID-002', 'Questão de teste numero 2', 'image', 'Dica número 1', 'EASY', 'www.wikipedia.com.br', '334412')
on conflict do nothing;

insert into exams (id, name, description, teacher_id, points, start_time, end_time, created_at, updated_at)
values ('EXAM-ID-001', 'Questionário base de matemática', 'Questionário numero 1 da turma de matematica', '334412', 10, '2024-05-02 15:56:19.000000', '2024-05-02 11:56:19.000000', default, default)
on conflict do nothing;

insert into exam_questions (exam_id, question_id)
values ('EXAM-ID-001', 'QUESTION-ID-001')
on conflict do nothing;

insert into exam_questions (exam_id, question_id)
values ('EXAM-ID-001', 'QUESTION-ID-002')
on conflict do nothing;

insert into group_exams (exam_id, group_id)
values ('EXAM-ID-001', 'SHA1022312')
on conflict do nothing;