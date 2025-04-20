CREATE TABLE endereco (
    id BIGSERIAL PRIMARY KEY,
    cep VARCHAR(9)
    cidade VARCHAR(255),
    estado VARCHAR(2),
    rua VARCHAR(255),
    numero INT,
    complemento VARCHAR(255),
    bairro VARCHAR(255),
);