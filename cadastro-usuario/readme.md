# Testando a API de Usuários com Postman

Este `README.md` descreve como testar os endpoints da API de gerenciamento de usuários utilizando o Postman.

---

## Sobre o Projeto

Este projeto foi desenvolvido com base nos conceitos apresentados na videoaula:

![Imagem da Videoaula](Imagem-aula.png)

**Referência:** [https://www.youtube.com/watch?v=yW7RrWfUeHE](https://www.youtube.com/watch?v=yW7RrWfUeHE)

A API fornece funcionalidades CRUD (Create, Read, Update, Delete) para a entidade `Usuario`.

---

## Endpoints da API

A base da URL para todos os endpoints será `http://localhost:8080/usuario` (assumindo que sua aplicação Spring Boot está rodando na porta padrão 8080).

Aqui estão os endpoints disponíveis:

| Método HTTP | Descrição | Parâmetros/Corpo da Requisição |
| :--- | :--- | :--- |
| `POST` | Salva um novo usuário no sistema. | `Body` (JSON) com dados do `Usuario`. |
| `GET` | Busca um usuário por e-mail. | `Query Param` (`email`) |
| `DELETE` | Deleta um usuário por e-mail. | `Query Param` (`email`) |
| `PUT` | Atualiza um usuário existente por ID. | `Query Param` (`id`), `Body` (JSON) com dados do `Usuario` |
| `GET` | Lista todos os usuários cadastrados. | Nenhum |

---

## Pré-requisitos para Testar

Antes de começar, certifique-se de que você tem:

1.  **Postman:** Instalado em sua máquina. Você pode baixá-lo em [https://www.postman.com/downloads/](https://www.postman.com/downloads/).
2.  **Aplicação Rodando:** Sua aplicação Spring Boot com os endpoints da API de usuários deve estar em execução (geralmente em `http://localhost:8080`).

---

## Passos para Testar no Postman

Vamos detalhar como configurar e enviar cada tipo de requisição no Postman.

### 1. Salvar Usuário (`POST /`)

Este endpoint é usado para criar um novo usuário.

* **Método:** `POST`
* **URL:** `http://localhost:8080/usuario`
* **Headers:**
    * `Content-Type`: `application/json`
* **Body (raw, JSON):**
    ```json
    {
        "nome": "João Silva",
        "email": "joao.silva@example.com",
        "idade": 30
    }
    ```
    (Ajuste os campos `nome`, `email` e `idade` conforme a sua entidade `Usuario`.)

**Como fazer no Postman:**

1.  Abra o Postman e clique em **`+`** para criar uma nova requisição.
2.  Defina o método como **`POST`**.
3.  Insira a URL: `http://localhost:8080/usuario`.
4.  Vá para a aba **`Body`**, selecione a opção **`raw`** e escolha **`JSON`** no dropdown.
5.  Cole o JSON de exemplo acima no campo de texto.
6.  Clique em **`Send`**.
7.  **Esperado:** Resposta `200 OK` (vazia).

---

### 2. Buscar Usuário por Email (`GET /?email={email}`)

Este endpoint busca um usuário específico pelo seu endereço de e-mail.

* **Método:** `GET`
* **URL:** `http://localhost:8080/usuario/?email={email_do_usuario}`
    * Substitua `{email_do_usuario}` pelo e-mail do usuário que você deseja buscar (ex: `joao.silva@example.com`).
* **Headers:** Nenhum específico é necessário.
* **Body:** Nenhum.

**Como fazer no Postman:**

1.  Abra o Postman e clique em **`+`** para criar uma nova requisição.
2.  Defina o método como **`GET`**.
3.  Insira a URL, por exemplo: `http://localhost:8080/usuario/?email=joao.silva@example.com`.
    * Alternativamente, você pode digitar `http://localhost:8080/usuario` na URL, e na aba **`Params`**, adicione uma `KEY` chamada `email` e seu `VALUE` correspondente (`joao.silva@example.com`).
4.  Clique em **`Send`**.
5.  **Esperado:** Resposta `200 OK` com o JSON do usuário encontrado. Se o usuário não for encontrado, você pode receber um `404 Not Found` ou outra resposta de erro, dependendo da sua implementação.

---

### 3. Deletar Usuário por Email (`DELETE /?email={email}`)

Este endpoint remove um usuário do sistema com base no seu endereço de e-mail.

* **Método:** `DELETE`
* **URL:** `http://localhost:8080/usuario/?email={email_do_usuario}`
    * Substitua `{email_do_usuario}` pelo e-mail do usuário a ser deletado.
* **Headers:** Nenhum específico é necessário.
* **Body:** Nenhum.

**Como fazer no Postman:**

1.  Abra o Postman e clique em **`+`** para criar uma nova requisição.
2.  Defina o método como **`DELETE`**.
3.  Insira a URL, por exemplo: `http://localhost:8080/usuario/?email=joao.silva@example.com`.
    * Alternativamente, use a aba **`Params`** para adicionar a `KEY` `email` e seu `VALUE`.
4.  Clique em **`Send`**.
5.  **Esperado:** Resposta `200 OK` (vazia).

---

### 4. Atualizar Usuário por ID (`PUT /?id={id_do_usuario}`)

Este endpoint atualiza as informações de um usuário existente, identificado pelo seu ID.

* **Método:** `PUT`
* **URL:** `http://localhost:8080/usuario/?id={id_do_usuario}`
    * Substitua `{id_do_usuario}` pelo ID numérico do usuário que você deseja atualizar.
* **Headers:**
    * `Content-Type`: `application/json`
* **Body (raw, JSON):**
    ```json
    {
        "nome": "João da Silva Atualizado",
        "email": "joao.silva.atualizado@example.com",
        "idade": 31
    }
    ```
    (Envie todos os campos da entidade `Usuario`, mesmo que queira mudar apenas um.)

**Como fazer no Postman:**

1.  **Primeiro, obtenha o ID do usuário:** Você pode usar o endpoint `GET /todos` ou `GET /?email={email}` para listar os usuários e pegar o `id` de um deles.
2.  Abra o Postman e clique em **`+`** para criar uma nova requisição.
3.  Defina o método como **`PUT`**.
4.  Insira a URL, por exemplo: `http://localhost:8080/usuario/?id=1` (assumindo que o ID do usuário é 1).
    * Use a aba **`Params`** para adicionar a `KEY` `id` e seu `VALUE`.
5.  Vá para a aba **`Body`**, selecione a opção **`raw`** e escolha **`JSON`** no dropdown.
6.  Cole o JSON de exemplo acima (com as informações atualizadas do usuário) no campo de texto.
7.  Clique em **`Send`**.
8.  **Esperado:** Resposta `200 OK` (vazia).

---

### 5. Listar Todos os Usuários (`GET /todos`)

Este endpoint retorna uma lista de todos os usuários cadastrados no sistema.

* **Método:** `GET`
* **URL:** `http://localhost:8080/usuario/todos`
* **Headers:** Nenhum específico é necessário.
* **Body:** Nenhum.

**Como fazer no Postman:**

1.  Abra o Postman e clique em **`+`** para criar uma nova requisição.
2.  Defina o método como **`GET`**.
3.  Insira a URL: `http://localhost:8080/usuario/todos`.
4.  Clique em **`Send`**.
5.  **Esperado:** Resposta `200 OK` com um array JSON contendo todos os usuários. Se não houver usuários, a resposta será um array vazio (`[]`).

---

Siga estes passos para testar cada funcionalidade da sua API de usuários no Postman.
