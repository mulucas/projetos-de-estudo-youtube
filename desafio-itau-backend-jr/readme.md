# Desafio de Vaga Backend: API de Estatísticas de Transações (Itaú)


O `README.md` abaixo descreve os endpoints da API para gerenciamento de transações e visualização de estatísticas, com um guia de como testar utilizando ferramentas como Postman ou Apidog.

## Sobre o Repositório

Este repositório foi desenvolvido com base na videoaula:
![Imagem da Videoaula](Imagem-aula.png)

**Referência:** https://www.youtube.com/watch?v=VqsXgE2RVtQ

A API fornece funcionalidades para registrar transações e calcular estatísticas em tempo real (transações com mais de 60 segundos são ignoradas), conforme o requisito do desafio.

## Endpoints da API

A base da URL para todos os endpoints será `http://localhost:8080` (assumindo que sua aplicação Spring Boot está rodando na porta padrão 8080).

| Método HTTP | Descrição | URL e Status de Resposta |
| :--- | :--- | :--- |
| **`POST`** | Cria uma nova transação. **Status 201 Created** (sucesso) ou **422 Unprocessable Entity** (data futura/valor inválido). | `/transacao` (Body JSON) |
| **`DELETE`** | Apaga todas as transações cadastradas. **Status 200 OK**. | `/transacao` |
| **`GET`** | Retorna as estatísticas (soma, média, max, min, count) das transações nos últimos 60 segundos. **Status 200 OK**. | `/estatistica` |

## Pré-requisitos para Testar

Antes de começar, certifique-se de que você tem:

1. **Cliente HTTP (Postman/Apidog):** Instalado em sua máquina.
2. **Aplicação Rodando:** Sua aplicação Spring Boot deve estar em execução (geralmente em `http://localhost:8080`).

## Passos para Testar

### 1. TransactionController (`/transacao`)

Este Controller gerencia o registro e a exclusão das transações.

#### 1.1. POST `/transacao` (Criar uma nova transação)

**Objetivo:** Adicionar uma nova transação.

1. Selecione o método `POST`.
2. **URL:** `http://localhost:8080/transacao`
3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.
4. Cole o seguinte corpo JSON. **Atenção:** A data/hora deve estar no formato ISO 8601 (OffsetDateTime) e não pode ser uma data futura. O valor deve ser maior que zero.

   ```json
   {
     "valor": 150.75,
     "dataHora": "2025-10-22T11:00:00.000-03:00" 
     // Use o timestamp atual ou um recente
   }
5. Clique em **`Send`**. O status esperado é **`201 Created`**.
6. **Cenário de Erro (422):** Se a `dataHora` for futura ou `valor` <= 0.

#### 1.2. DELETE `/transacao` (Apagar todas as transações)

**Objetivo:** Limpar o registro de transações.

1. Selecione o método `DELETE`.
2. **URL:** `http://localhost:8080/transacao`
3. Clique em **`Send`**. O status esperado é **`200 OK`**.

### 2. StatisticsController (`/estatistica`)

Este Controller fornece dados agregados sobre as transações recentes.

#### 2.1. GET `/estatistica` (Obter Estatísticas)

**Objetivo:** Visualizar as estatísticas (soma, média, min, max, contagem) das transações registradas nos **últimos 60 segundos**.

1. Selecione o método `GET`.
2. **URL:** `http://localhost:8080/estatistica`
3. Clique em **`Send`**.
4. O status esperado é **`200 OK`**. O corpo da resposta deve ser um JSON contendo as estatísticas:

    ```json 
    {
      "soma": 350.50,
      "media": 116.83,
      "max": 200.00,
      "min": 50.50,
      "count": 3
    }
---

### 💡 Dica e aprendizado de Produtividade

Para testar e debugar rapidamente a API Spring Boot no seu ambiente de desenvolvimento, recomendo o uso do plugin **Apidog Fast Request** para IDEs como IntelliJ IDEA. Ele permite enviar requisições HTTP e ver a resposta diretamente na sua IDE, sem a necessidade de alternar para ferramentas externas como Postman.
