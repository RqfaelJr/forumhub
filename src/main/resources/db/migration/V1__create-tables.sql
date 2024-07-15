CREATE TABLE cursos
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)       UNIQUE   NOT NULL,
    categoria VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_cursos PRIMARY KEY (id)
);

CREATE TABLE perfis
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255)     UNIQUE     NOT NULL,
    CONSTRAINT pk_perfis PRIMARY KEY (id)
);

CREATE TABLE respostas
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    mensagem  VARCHAR(255)          NOT NULL,
    topico_id BIGINT                NOT NULL,
    data      datetime              NOT NULL,
    autor_id  BIGINT                NOT NULL,
    solucao   VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_respostas PRIMARY KEY (id)
);

CREATE TABLE topicos
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    titulo   VARCHAR(255)   UNIQUE       NOT NULL,
    mensagem VARCHAR(255)     UNIQUE     NOT NULL,
    data     datetime              NOT NULL,
    status   VARCHAR(255)          NOT NULL,
    autor_id BIGINT                NOT NULL,
    curso_id BIGINT                NOT NULL,
    CONSTRAINT pk_topicos PRIMARY KEY (id)
);

CREATE TABLE usuarios
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)   UNIQUE       NOT NULL,
    email     VARCHAR(255)   UNIQUE       NULL,
    senha     VARCHAR(255)          NOT NULL,
    perfil_id BIGINT                NOT NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (id)
);

ALTER TABLE respostas
    ADD CONSTRAINT FK_RESPOSTAS_ON_AUTOR FOREIGN KEY (autor_id) REFERENCES usuarios (id);

ALTER TABLE respostas
    ADD CONSTRAINT FK_RESPOSTAS_ON_TOPICO FOREIGN KEY (topico_id) REFERENCES topicos (id);

ALTER TABLE topicos
    ADD CONSTRAINT FK_TOPICOS_ON_AUTOR FOREIGN KEY (autor_id) REFERENCES usuarios (id);

ALTER TABLE topicos
    ADD CONSTRAINT FK_TOPICOS_ON_CURSO FOREIGN KEY (curso_id) REFERENCES cursos (id);

ALTER TABLE usuarios
    ADD CONSTRAINT FK_USUARIOS_ON_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfis (id);

INSERT INTO cursos
(id, nome, categoria)
VALUES
    (1, "Java", "PROGRAMACAO");

INSERT INTO cursos
(id, nome, categoria)
VALUES
    (2, "Python", "PROGRAMACAO");

INSERT INTO cursos
(id, nome, categoria)
VALUES
    (3, "Gestão Empresas", "GESTAO");

INSERT INTO cursos
(id, nome, categoria)
VALUES
    (4, "EaD", "EDUCACAO");