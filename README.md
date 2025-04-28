**Controle de EPI Telegram Bot**

Um bot do Telegram desenvolvido em **Java** com **Spring Boot**, que integra com **MySQL** via JDBC para gerenciar EPIs (Equipamentos de Proteção Individual) através de operações CRUD.

---

## 🚀 Visão Geral

Este projeto implementa um bot que permite cadastrar e listar EPIs pelo Telegram. Ainda está em desenvolvimento e apresenta diversos bugs; o objetivo final é suportar **CRUD completo** (Create, Read, Update, Delete).

### Funcionalidades Atuais
- **Cadastrar** EPI: passo a passo pelo comando `/cadastrar`.
- **Listar** EPIs: comando `listar` retorna todos os registros.

### Funcionalidades Futuras
- **Editar** EPI
- **Excluir** EPI
- Melhor tratamento de erros e validações

---

## 📋 Requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL Server
- Token de bot do Telegram

---

## ⚙️ Instalação e Configuração

1. **Clone** o repositório:
   ```bash
   git clone https://github.com/seu-usuario/Controle_EPI_TelegramBOT.git
   cd Controle_EPI_TelegramBOT
   ```

2. **Configure as credenciais:**
   - **Token do Telegram:** abra `src/main/java/com/controleepi/bot/MeuBot.java` e defina seu token no método `getBotToken()`.
   - **Banco de Dados:** abra `src/main/java/com/controleepi/database/Conexao.java` e ajuste a `URL`, `USUARIO` e `SENHA` para sua instância MySQL.

3. **Crie o banco de dados e tabela:**
   ```sql
   CREATE DATABASE controle_epi;
   USE controle_epi;
   CREATE TABLE epi (
     id INT AUTO_INCREMENT PRIMARY KEY,
     nome VARCHAR(100),
     validade DATE
   );
   ```

4. **Compile e execute:**
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

---

## 🐞 Bugs Conhecidos

- Podem ocorrer **`NullPointerException`** se a conexão com o banco falhar.
- **Erro 409 Conflict** no log devido a sessões antigas de polling.
- Fluxo de conversação ainda instável, especialmente em cenários de concorrência.

> **Atenção:** vários bugs estão presentes. O projeto está em fase de desenvolvimento ativo.

---

## 📌 Objetivo Final

Implementar um **CRUD completo** para EPIs diretamente pelo Telegram, com fluxo de conversação robusto e tratamento de erros apropriado.

---

## 🤝 Contribuição

1. Fork deste repositório
2. Crie uma branch para sua feature: `git checkout -b feature/nova-funcionalidade`
3. Commit suas mudanças: `git commit -m "Add nova funcionalidade"`
4. Push na branch: `git push origin feature/nova-funcionalidade`
5. Abra um Pull Request

---

© 2025 Desenvolvido por Matheus Ribeiro. Em desenvolvimento.

