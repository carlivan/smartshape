CREATE TABLE personal (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cref VARCHAR(20) UNIQUE NOT NULL,
    especialidade VARCHAR(50),
    data_cadastro TIMESTAMP NOT NULL
);