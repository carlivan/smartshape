CREATE TABLE item_treino (
    id UUID PRIMARY KEY,
    rotina_id UUID NOT NULL,
    exercicio_id UUID NOT NULL,
    series INTEGER NOT NULL,
    repeticoes VARCHAR(20) NOT NULL, -- Usamos String para permitir "10-12" ou "Até a Falha"
    carga VARCHAR(20),               -- String para permitir "40Kg" ou "Placa 5"
    descanso INTEGER,
    ordem INTEGER NOT NULL,          -- Para definir a sequência
    observacoes TEXT,
    CONSTRAINT fk_item_rotina FOREIGN KEY (rotina_id) REFERENCES rotina_treino(id),
    CONSTRAINT fk_item_exercicio FOREIGN KEY (exercicio_id) REFERENCES exercicio(id)
);