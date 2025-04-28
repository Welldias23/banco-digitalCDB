
# Banco Digital CDBW 🚀

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg?style=for-the-badge)](LICENSE)

---

## 📚 Sobre o Projeto

Este projeto é uma **API RESTful** que simula as principais operações de um **Banco Digital**. Foi desenvolvido como parte de um desafio prático para consolidar conhecimentos em **Java**, **Spring Boot** e arquitetura de APIs.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security + JWT (opcional)
- Banco de Dados PostgreSQL
- Maven

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
<!-- - **Perfis de Usuário** (`ADMIN`, `CLIENTE`) com permissões diferentes 
- **Integração com APIs externas**:-->
- Validação de CPF
<!-- - Consulta de Taxa de Câmbio -->

---

## ⚙️ Como Executar Localmente

```bash
# Clone o repositório
git clone https://git@github.com:Welldias23/banco-digitalCDB.git

# Acesse o diretório
cd seu-repositorio

# Execute o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em:  
> http://localhost:8080

---

## 🗂️ Estrutura do Projeto

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

<!-- ## 📷 Screenshots (opcional)

| Endpoint de Criação de Cliente | Endpoint de Consulta de Saldo |
|:-------------------------------:|:-----------------------------:|
| ![POST Cliente](https://via.placeholder.com/400x200.png?text=POST+/clientes) | ![GET Saldo](https://via.placeholder.com/400x200.png?text=GET+/contas/{id}/saldo) |

*(Troque as imagens acima por prints reais depois que você tiver.)*

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**.  
Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

--- -->

## ✍️ Autor

Feito com ❤️ por Wellington Ribeiro Dias.  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/seu-linkedin)
