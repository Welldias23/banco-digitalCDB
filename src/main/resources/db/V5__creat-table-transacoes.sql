CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    tipo_transacao VARCHAR(31),
    conta_destino_id BIGINT NOT NULL REFERENCES conta(id),
    nome_destino VARCHAR(255),
    conta_origem_id  BIGINT NOT NULL REFERENCES conta(id),
    nome_origem  VARCHAR(255),
    valor NUMERIC(19, 2),
    data_transacao DATE,
    horario_transacao TIME
);

