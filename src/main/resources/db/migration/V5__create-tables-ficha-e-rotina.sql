CREATE TABLE ficha_treino (
    id UUID PRIMARY KEY,
    aluno_id UUID NOT NULL,
    nome VARCHAR(100) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_ficha_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

CREATE TABLE rotina_treino (
    id UUID PRIMARY KEY,
    ficha_id UUID NOT NULL,
    nome VARCHAR(100), -- Ex: "Peito e Tríceps"
    identificador VARCHAR(1) NOT NULL, -- Ex: "A", "B", "C"
    CONSTRAINT fk_rotina_ficha FOREIGN KEY (ficha_id) REFERENCES ficha_treino(id)
);