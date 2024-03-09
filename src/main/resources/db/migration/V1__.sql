CREATE TABLE public."user"
(
    registration VARCHAR(255) NOT NULL,
    user_type    VARCHAR(31),
    name         VARCHAR(255),
    email        VARCHAR(255),
    password     VARCHAR(255),
    phone        VARCHAR(255),
    about        VARCHAR(255),
    cpf          VARCHAR(255),
    active       BOOLEAN,
    birth_date   TIMESTAMP WITHOUT TIME ZONE,
    user_role    SMALLINT,
    cv_lattes    VARCHAR(255),
    website      VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (registration)
);

ALTER TABLE public."user"
    ADD CONSTRAINT uc_user_cpf UNIQUE (cpf);

ALTER TABLE public."user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);