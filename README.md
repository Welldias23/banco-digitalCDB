
# Banco Digital CDBW 🚀

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg?style=for-the-badge)](LICENSE)

---

## 📚 Sobre o Projeto

Este projeto é uma **API RESTful** que simula as principais operações de um **Banco Digital**. Foi desenvolvido como parte de um desafio prático para consolidar conhecimentos em **Java**, **Spring Boot** e arquitetura de APIs.

---

## 🐳 Docker

Este projeto utiliza Docker e Docker Compose para facilitar o setup do ambiente de desenvolvimento, especialmente o banco de dados PostgreSQL.

# 📦 Subindo o ambiente
Certifique-se de ter o Docker e o Docker Compose instalados.

Para subir os containers, execute o comando abaixo na raiz do projeto (onde está o docker-compose.yml):

bash
Copiar
Editar
docker compose up -d
Isso iniciará um container com a imagem oficial do PostgreSQL 15.

📂 Configurações do banco de dados
Chave	Valor
Host	localhost
Porta	5432
Banco de Dados	bancodigital
Usuário	postgres
Senha	postgres

Os dados do banco serão persistidos no volume Docker postgres-data.

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

## 🔗 Funcionalidades da API

### Clientes
- `POST /clientes` — Criar um cliente
- `GET /clientes/{id}` — Buscar cliente por ID
- `PUT /clientes/{id}` — Atualizar dados do cliente
- `DELETE /clientes/{id}` — Remover cliente
- `GET /clientes` — Listar todos os clientes

### Contas
- `POST /contas` — Criar conta
- `GET /contas/{id}` — Detalhes da conta
- `POST /contas/{id}/transferencia` — Transferência entre contas
- `GET /contas/{id}/saldo` — Consultar saldo
- `POST /contas/{id}/pix` — Pagamento via Pix
- `POST /contas/{id}/deposito` — Realizar depósito
- `POST /contas/{id}/saque` — Realizar saque
- `PUT /contas/{id}/manutencao` — Aplicar taxa de manutenção (conta corrente)
- `PUT /contas/{id}/rendimentos` — Aplicar rendimentos (poupança)

### Cartões
- `POST /cartoes` — Emitir cartão
- `GET /cartoes/{id}` — Detalhes do cartão
- `POST /cartoes/{id}/pagamento` — Pagamento com cartão
- `PUT /cartoes/{id}/limite` — Alterar limite
- `PUT /cartoes/{id}/status` — Ativar/desativar cartão
- `PUT /cartoes/{id}/senha` — Alterar senha
- `GET /cartoes/{id}/fatura` — Consultar fatura
- `POST /cartoes/{id}/fatura/pagamento` — Pagar fatura
- `PUT /cartoes/{id}/limite-diario` — Alterar limite diário

### Seguros (opcional)
- `POST /seguros` — Contratar seguro
- `GET /seguros/{id}` — Ver apólice
- `GET /seguros` — Listar seguros
- `PUT /seguros/{id}/cancelar` — Cancelar seguro

---

## 🔒 Funcionalidades Avançadas

- **Autenticação e Autorização** via Spring Security + JWT
- Validação de CPF
- Controle de acesso baseado em perfil (em desenvolvimento)

---

## 📟 Documentação da API

A documentação dos endpoints está disponível via **Swagger UI** utilizando **SpringDoc OpenAPI 3**.

Após iniciar a aplicação, acesse:

> 📌 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Essa interface permite testar os endpoints, visualizar schemas e entender o funcionamento da API de forma interativa.

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

## 📂 Estrutura do Projeto

```bash
src/
 ├── main/
 │    ├── java/
 │    │    └── com/
 │    │         └── seuusuario/
 │    │              └── bancodigital/
 │    │                   ├── controllers/
 │    │                   ├── services/
 │    │                   ├── models/
 │    │                   ├── repositories/
 │    │                   ├── dtos/
 │    │                   └── config/
 │    └── resources/
 │         ├── application.properties
 │         └── static/
 └── test/
```

---
<!--
## 📄 Licença

Este projeto está licenciado sob a licença **MIT**.  
Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
-->
---

## ✍️ Autor

Feito com ❤️ por **Wellington Ribeiro Dias**  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/wellington-ribeiro-dias-dev-backend/)


