CREATE TABLE contas (
    id SERIAL PRIMARY KEY,
    tipo_conta VARCHAR(31),
    agencia BIGINT,
    numero_conta VARCHAR(255),
    saldo NUMERIC(19, 2),
    ativa BOOLEAN,
    data_criacao DATE,
    chave_pix VARCHAR(255),
    cliente_id BIGINT NOT NULL REFERENCES clientes(id),
    rendimento FLOAT
);