ALTER TABLE cliente 
    ADD CONSTRAINT fk_cliente_endereco 
    FOREIGN KEY (endereco_id) 
    REFERENCES endereco (id);