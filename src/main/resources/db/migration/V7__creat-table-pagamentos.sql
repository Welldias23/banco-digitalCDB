CREATE TABLE pagamentos (
	 id SERIAL PRIMARY KEY,
	 tipo_pagamento VARCHAR(31),
	 data_pagamento DATE NOT NULL,
	 hora_pagamento TIME NOT NULL,
	 nome_estabelecimento VARCHAR(255),
	 nome_objeto VARCHAR(255),
	 valor NUMERIC(19, 2),
	 cartao_credito_id BIGINT REFERENCES cartoes(id),
	 cartao_debito_id BIGINT REFERENCES cartoes(id)
);
