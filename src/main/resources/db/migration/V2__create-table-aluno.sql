CREATE TABLE aluno (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_nascimento DATE,
    objetivo VARCHAR(255),
    data_cadastro TIMESTAMP NOT NULL,
    personal_id UUID,
    CONSTRAINT fk_aluno_personal FOREIGN KEY (personal_id) REFERENCES personal(id)
);