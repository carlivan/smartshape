CREATE TABLE users (
                       id         BIGSERIAL    PRIMARY KEY,
                       name       VARCHAR(100) NOT NULL,
                       email      VARCHAR(150) NOT NULL UNIQUE,
                       password   VARCHAR(255) NOT NULL,
                       role       VARCHAR(20)  NOT NULL DEFAULT 'ALUNO'
                           CHECK (role IN ('ALUNO', 'PERSONAL', 'NUTRICIONISTA', 'ADMIN')),
                       active     BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
                       updated_at TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE alunos (
                        id              BIGSERIAL    PRIMARY KEY,
                        user_id         BIGINT       NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
                        matricula       VARCHAR(20)  NOT NULL UNIQUE,
                        objetivo        VARCHAR(100),
                        data_nascimento DATE,
                        telefone        VARCHAR(20),
                        created_at      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_users_email     ON users(email);
CREATE INDEX idx_users_role      ON users(role);
CREATE INDEX idx_alunos_matricula ON alunos(matricula);