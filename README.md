
# Banco Digital CDBW 🚀

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

---

## 📚 Sobre o Projeto

Este projeto é uma **API RESTful** que simula as principais operações de um **Banco Digital**. Foi desenvolvido como parte de um desafio prático para consolidar conhecimentos em **Java**, **Spring Boot** e arquitetura de APIs.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security + JWT
- Bean Validation (Hibernate Validator)
- PostgreSQL
- Flyway (migração de banco de dados)
- Lombok
- Swagger (SpringDoc OpenAPI)
- Maven
- MapStruct

---

## ⚙️ Como Executar Localmente

```bash
# Clone o repositório
git clone https://git@github.com:Welldias23/banco-digitalCDB.git

# Acesse o diretório
cd banco-digitalCDB

# Execute o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em:

> http://localhost:8080

---


## 🐳 Executando o Projeto com Docker

Este projeto usa Docker e Docker Compose para facilitar a execução da aplicação e do banco de dados Postgres em containers.

---

### ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

### 📦 Build e execução dos containers

No terminal, execute o seguinte comando na raiz do projeto (onde está o `docker-compose.yml`):

```bash
docker compose up --build
```
Este comando irá:

Construir a imagem da API usando o Dockerfile do projeto.

Subir o banco de dados Postgres e a API da aplicação em containers separados.

A API será acessível na porta 8080.

🛑 Parar os containers
Para parar os containers, pressione CTRL+C no terminal ou execute:

```bash
docker compose down
```

🔁 Executar novamente sem rebuild (caso a imagem já esteja criada)

```bash
docker compose up
```

🌐 Endpoints
Após subir a aplicação, você pode acessar os endpoints da API via:

```arduino
http://localhost:8080
```

---



## 📟 Documentação da API

A documentação dos endpoints está disponível via **Swagger UI** utilizando **SpringDoc OpenAPI 3**.

Após iniciar a aplicação, acesse:

> 📌 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Essa interface permite testar os endpoints, visualizar schemas e entender o funcionamento da API de forma interativa.

---

## 🔗 Funcionalidades da API

### Clientes
- `POST /clientes` — Criar um cliente
- - `POST /login` — Login de um cliente
- `GET /clientes` — Buscar cliente logado
- `PUT /clientes` — Atualizar dados do cliente logado
- `PATCH /clientes` — Atualizar dados do cliente logado parcialmente
- `DELETE /clientes` — Remover cliente logado


### Endereços
- `POST /endeco` — Criar endeço para cliente logado
- `GET /endeco` — Detalhes do endeço do cliente logado
- `PUT /endeco` — Atualiza totalmente endereço do cliente logado
- `PATCH /endeco` — Atualiza parcialmente endereço do cliente logado
- `DELETE /endeco` — Remove endereço


### Contas
- `POST /contas` — Criar conta e cartao de debito
- `GET /conta/{id}` — Detalhes da conta
- `POST /conta/{id}/pix/cadastrar` — Cadastra uma chave pix na conta
- `POST /conta/{id}/transferencia` — Transferência entre contas
- `GET /conta/{id}/saldo` — Consultar saldo
- `POST /conta/{id}/pix` — Pagamento/transferencia via Pix
- `POST /conta/{id}/deposito` — Realizar depósito
- `POST /conta/{id}/saque` — Realizar saque

### Cartões
- `POST /cartao` — Emitir cartão de credito
- `GET /cartao/{id}` — Detalhes do cartão
- `POST /cartao/pagamento` — Pagamento com cartão
- `PUT /cartao/{id}/limite` — Alterar limite
- `PUT /cartao/{id}/ativar` — Ativar cartão
-  `PUT /cartao/{id}/desativar` — desativar cartão
- `PUT /cartao/{id}/senha` — Alterar senha
- `GET /cartao/{id}/fatura` — Consultar fatura
- `POST /cartao/{id}/fatura/pagar` — Pagar fatura
- `PUT /cartao/{id}/limite-diario` — Alterar limite diário
- 
---

## 🔒 Funcionalidades Avançadas

- **Autenticação e Autorização** via Spring Security + JWT
- Validação de CPF
- Controle de acesso baseado em perfil (em desenvolvimento)

---



## ✍️ Autor

Feito com ❤️ por **Wellington Ribeiro Dias**  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/wellington-ribeiro-dias-dev-backend/)


