ALTER TABLE clientes 
    ADD CONSTRAINT fk_cliente_endereco 
    FOREIGN KEY (endereco_id) 
    REFERENCES enderecos (id);