CREATE TABLE conta (
    id SERIAL PRIMARY KEY,
    agencia BIGINT,
    numero_conta VARCHAR(255),
    saldo NUMERIC(19, 2),
    ativa BOOLEAN,
    data_criacao DATE,
    chave_pix VARCHAR(255),
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_conta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);