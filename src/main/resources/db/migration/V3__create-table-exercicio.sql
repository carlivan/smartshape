CREATE TABLE exercicio (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    grupo_muscular VARCHAR(50) NOT NULL,
    descricao TEXT,
    video_url VARCHAR(255)
);