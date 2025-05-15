CREATE TABLE cartoes (
    id SERIAL PRIMARY KEY,
    tipo_cartao VARCHAR(31),
    numero_cartao VARCHAR(30) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_criacao DATE NOT NULL,
    hora_criacao TIME NOT NULL,
    ativo BOOLEAN NOT NULL,
    conta_id BIGINT NOT NULL REFERENCES contas(id),
    taxa_anuidade NUMERIC(19, 2),
    limite_credito_total NUMERIC(19, 2),
    limite_credito_usado NUMERIC(19, 2),
	limite_diario NUMERIC(19, 2),
	limite_diario_usado NUMERIC(19, 2)
	 
);

