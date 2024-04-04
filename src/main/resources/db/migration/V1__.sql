CREATE TABLE alternative
(
    id          VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    correct     BOOLEAN      NOT NULL,
    question_id VARCHAR(36),
    CONSTRAINT pk_alternative PRIMARY KEY (id)
);

CREATE TABLE answer
(
    id             VARCHAR(255) NOT NULL,
    users_id       VARCHAR(255),
    exam_id        VARCHAR(255),
    question_id    VARCHAR(36),
    alternative_id VARCHAR(255),
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE exam_questions
(
    exam_id     VARCHAR(255) NOT NULL,
    question_id VARCHAR(36)  NOT NULL
);

CREATE TABLE exams
(
    id          VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    teacher_id  VARCHAR(255),
    points      INTEGER,
    start_time  TIMESTAMP WITHOUT TIME ZONE,
    end_time    TIMESTAMP WITHOUT TIME ZONE,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_exams PRIMARY KEY (id)
);

CREATE TABLE group_exams
(
    exam_id  VARCHAR(255) NOT NULL,
    group_id VARCHAR(255) NOT NULL
);

CREATE TABLE groups
(
    id                   VARCHAR(255) NOT NULL,
    name                 VARCHAR(255),
    description          VARCHAR(255),
    image                VARCHAR(255),
    creator_registration VARCHAR(255),
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

CREATE TABLE groups_users
(
    group_id VARCHAR(255) NOT NULL,
    user_id  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_groups_users PRIMARY KEY (group_id, user_id)
);

CREATE TABLE questions
(
    id          VARCHAR(36) NOT NULL,
    description VARCHAR(255),
    image       VARCHAR(255),
    tip         VARCHAR(255),
    difficulty  VARCHAR(255),
    source      VARCHAR(255),
    author_id   VARCHAR(255),
    CONSTRAINT pk_questions PRIMARY KEY (id)
);

CREATE TABLE users
(
    registration VARCHAR(255) NOT NULL,
    user_type    VARCHAR(31),
    name         VARCHAR(255),
    email        VARCHAR(255),
    password     VARCHAR(255),
    icon         VARCHAR(255),
    slug         VARCHAR(255),
    phone        VARCHAR(255),
    about        VARCHAR(255),
    cpf          VARCHAR(255),
    active       BOOLEAN,
    birth_date   TIMESTAMP WITHOUT TIME ZONE,
    user_role    SMALLINT,
    cv_lattes    VARCHAR(255),
    website      VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (registration)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_cpf UNIQUE (cpf);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_slug UNIQUE (slug);

ALTER TABLE alternative
    ADD CONSTRAINT FK_ALTERNATIVE_ON_QUESTION FOREIGN KEY (question_id) REFERENCES questions (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_ALTERNATIVE FOREIGN KEY (alternative_id) REFERENCES alternative (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_EXAM FOREIGN KEY (exam_id) REFERENCES exams (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_QUESTION FOREIGN KEY (question_id) REFERENCES questions (id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_USERS FOREIGN KEY (users_id) REFERENCES users (registration);

ALTER TABLE exams
    ADD CONSTRAINT FK_EXAMS_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES users (registration);

ALTER TABLE groups
    ADD CONSTRAINT FK_GROUPS_ON_CREATOR_REGISTRATION FOREIGN KEY (creator_registration) REFERENCES users (registration);

ALTER TABLE questions
    ADD CONSTRAINT FK_QUESTIONS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES users (registration);

ALTER TABLE exam_questions
    ADD CONSTRAINT fk_exaque_on_exam FOREIGN KEY (exam_id) REFERENCES exams (id);

ALTER TABLE exam_questions
    ADD CONSTRAINT fk_exaque_on_question FOREIGN KEY (question_id) REFERENCES questions (id);

ALTER TABLE group_exams
    ADD CONSTRAINT fk_groexa_on_exam FOREIGN KEY (exam_id) REFERENCES exams (id);

ALTER TABLE group_exams
    ADD CONSTRAINT fk_groexa_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_users
    ADD CONSTRAINT fk_grouse_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_users
    ADD CONSTRAINT fk_grouse_on_user FOREIGN KEY (user_id) REFERENCES users (registration);