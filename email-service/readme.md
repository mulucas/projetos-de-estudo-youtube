# API de Envio de E-mail com Spring Boot e AWS SES

Este README.md descreve o endpoint da API de envio de e-mails e o passo-a-passo de como testar utilizando a ferramenta Postman.

## Sobre o Projeto

Este projeto foi desenvolvido com base nos conceitos apresentados na videoaula:

![Imagem da Videoaula](Imagem-aula.png)


**Referência:** <https://www.youtube.com/watch?v=eFgeO9M9lLw&list=PLNCSWIsR6ADKaT1cO6XUJkRy0_v9p-h0Z>

## Endpoints da API

A base da URL para todos os endpoints será `http://localhost:8080`.

| Método HTTP | Descrição        | URL e Parâmetros         |
| :--- |:-----------------|:-------------------------|
| `POST` | Envia um e-mail. | `/api/email` (Body JSON) |

## Pré-requisitos para Testar

Antes de começar, certifique-se de que você tem:

1. **Postman:** Instalado em sua máquina. Você pode baixá-lo em <https://www.postman.com/downloads/>.

2. **Aplicação Rodando:** Sua aplicação Spring Boot deve estar em execução (geralmente em `http://localhost:8080`).
3. **application.properties** atualizar esse arquivo colocando suas credenciais da AWS

## Passos para Testar no Postman

### EmailSenderController - Envio de E-mail

Este Controller é responsável por receber uma requisição e disparar um e-mail utilizando o serviço configurado.

#### POST `/api/email` (Enviar um E-mail)

1. Selecione o método `POST`.

2. **URL:** `http://localhost:8080/api/email`.

3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.

4. Cole o seguinte corpo JSON:

   ```json
    {
    "to": "destinatario@exemplo.com",
    "subject": "Teste de Envio de E-mail",
    "body": "Olá, este é um e-mail de teste enviado pela API Spring Boot."
    }
   ```

5. Clique em **`Send`**. O status esperado é `200 OK`. Resposta esperada em caso de sucesso: "Email sent successfully".

6. Verifique a resposta para confirmar que o email foi enviado com sucesso e confira a caixa de entrada no endereço de email informado.

7. Em caso de erro: Se a API não conseguir se conectar ao serviço de e-mail (por problemas com credenciais AWS), o status retornado será 500 Internal Server Error, com uma mensagem detalhando a falha.