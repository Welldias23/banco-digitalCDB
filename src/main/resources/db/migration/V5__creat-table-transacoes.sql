CREATE TABLE transacoes (
    id SERIAL PRIMARY KEY,
    tipo_transacao VARCHAR(31),
    conta_destino_id BIGINT REFERENCES contas(id),
    nome_destino VARCHAR(255),
    conta_origem_id  BIGINT REFERENCES contas(id),
    nome_origem  VARCHAR(255),
    valor NUMERIC(19, 2),
    data_transacao DATE,
    horario_transacao TIME
);

