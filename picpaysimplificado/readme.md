# PicPay Simplificado API

Este projeto é uma API de backend que simula um sistema de transações financeiras simplificado, inspirado no PicPay. Ele permite o cadastro de usuários e a realização de transações entre eles, com regras de negócio específicas para cada tipo de usuário.

---

## Sobre o Projeto

Este projeto foi desenvolvido com base nos conceitos apresentados na videoaula:

![Imagem da Videoaula](Imagem-aula.png)

**Referência:** [https://www.youtube.com/watch?v=QXunBiLq2SM](https://www.youtube.com/watch?v=QXunBiLq2SM)

---

## 🚀 Tecnologias e Conceitos

* **Java com Spring Boot:** Estrutura principal do projeto.
* **JPA e Hibernate:** Para persistência de dados em banco.
* **Lombok:** Simplificação do código com anotações como `@Getter`, `@Setter` e `@NoArgsConstructor`.
* **Validações de Negócio:** Aplicação de regras, como verificar saldo e tipo de usuário.
* **Integração com API Externa:** Uso do `RestTemplate` para simular a autorização de transações.
* **Tratamento de Exceções Global:** O `@RestControllerAdvice` garante o tratamento de erros de forma centralizada.

---

## 🗺️ Endpoints da API

A API possui dois controladores principais: `UserController` para gerenciar usuários e `TransactionController` para realizar transações.

### **Controlador de Usuários (`/users`)**

| Método | URL          | Descrição                          |
| :----- | :----------- | :--------------------------------- |
| `POST` | `/users`     | Cria um novo usuário.              |
| `GET`  | `/users`     | Retorna a lista de todos os usuários. |

### **Controlador de Transações (`/transactions`)**

| Método | URL                  | Descrição                                 |
| :----- | :------------------- | :---------------------------------------- |
| `POST` | `/transactions`      | Realiza uma transação entre dois usuários. |

---

## ⚙️ Como Testar no Postman (Passo a Passo)

Para testar a API, certifique-se de que a aplicação está rodando localmente (geralmente em `http://localhost:8080`).

### **Passo 1: Criar Usuários**

Primeiro, você precisa de pelo menos dois usuários para realizar uma transação.
Um usuário precisa ser do tipo `COMMON` para enviar dinheiro, e o outro pode ser `COMMON` ou `MERCHANT` para receber.

1.  Crie um novo `POST` no Postman com a URL: `http://localhost:8080/users`
2.  Vá para a aba **`Body`**, selecione a opção **`raw`** e escolha **`JSON`** no menu.
3.  Use um dos exemplos abaixo para criar os usuários.

    **Exemplo de usuário `COMMON` (enviará dinheiro):**
    ```json
    {
        "firstName": "Joao",
        "lastName": "Silva",
        "document": "123.456.789-10",
        "email": "joao.silva@teste.com",
        "password": "senha123",
        "balance": 1000.00,
        "userType": "COMMON"
    }
    ```

    **Exemplo de usuário `MERCHANT` (receberá dinheiro):**
    ```json
    {
        "firstName": "Loja",
        "lastName": "Online",
        "document": "12.345.678/0001-90",
        "email": "loja.online@teste.com",
        "password": "senha123",
        "balance": 500.00,
        "userType": "MERCHANT"
    }
    ```
4.  Clique em **`Send`**. A resposta esperada é um status **`201 Created`** com os dados do usuário criado. Anote o `id` de cada usuário, pois você precisará deles para a transação.

---

### **Passo 2: Listar Todos os Usuários**

Se você quiser ver todos os usuários criados e seus respectivos IDs, use o endpoint `GET`.

1.  Crie um novo `GET` no Postman com a URL: `http://localhost:8080/users`
2.  Clique em **`Send`**.
3.  A resposta será um status **`200 OK`** com uma lista de todos os usuários cadastrados.

---

### **Passo 3: Realizar uma Transação**

Com os IDs dos usuários em mãos, você pode simular uma transação.

1.  Crie um novo `POST` no Postman com a URL: `http://localhost:8080/transactions`
2.  Vá para a aba **`Body`**, selecione a opção **`raw`** e escolha **`JSON`**.
3.  Use o exemplo abaixo. Substitua `senderId` e `receiverId` pelos IDs dos usuários que você criou no Passo 1, e ajuste o `value`.

    **Exemplo de transação:**
    ```json
    {
        "senderId": 1,
        "receiverId": 2,
        "value": 50.00
    }
    ```
4.  Clique em **`Send`**. A resposta esperada é um status **`200 OK`** com os detalhes da transação criada.

**Importante:** A transação só será bem-sucedida se o `sender` tiver saldo suficiente e for do tipo **`COMMON`**. Se você tentar fazer uma transação com um `MERCHANT` como `sender`, a API retornará um erro.