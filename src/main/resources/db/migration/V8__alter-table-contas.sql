ALTER TABLE contas
DROP CONSTRAINT contas_cliente_id_fkey;


ALTER TABLE contas
ADD CONSTRAINT contas_cliente_id_fkey
FOREIGN KEY (cliente_id)
REFERENCES clientes(id)
ON DELETE CASCADE;
