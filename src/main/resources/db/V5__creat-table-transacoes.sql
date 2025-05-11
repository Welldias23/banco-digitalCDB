CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    conta_destino_id BIGINT NOT NULL,
    nome_destino VARCHAR(255),
    conta_origem_id  BIGINT NOT NULL,
    nome_origem  VARCHAR(255),
    valor NUMERIC(19, 2),
    data_transacao DATE,
    horario TIME
);

