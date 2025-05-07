CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    renda_mensal NUMERIC(19, 2) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    endereco_id BIGINT
);