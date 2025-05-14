CREATE TABLE conta (
    id SERIAL PRIMARY KEY,
    tipo_conta VARCHAR(31),
    agencia BIGINT,
    numero_conta VARCHAR(255),
    saldo NUMERIC(19, 2),
    ativa BOOLEAN,
    data_criacao DATE,
    chave_pix VARCHAR(255),
    cliente_id BIGINT NOT NULL REFERENCES cliente(id),
    rendimento FLOAT
);