# Testando a API de Posto de Combustível com Postman

Este `README.md` descreve os endpoints da API de gerenciamento do posto de combustível e passo-a-passo de como testar utilizando a ferramenta Postman.

## Sobre o Projeto

Este projeto foi desenvolvido com base nos conceitos apresentados na videoaula:

![Imagem da Videoaula](Imagem-aula.png)


**Referência:** <https://www.youtube.com/watch?v=wu29IO2vmfI>

A API fornece funcionalidades de gerenciamento para as entidades `BombaCombustivel`, `Abastecimento` e `TipoCombustivel`.

## Endpoints da API

A base da URL para todos os endpoints será `http://localhost:8080` (assumindo que sua aplicação Spring Boot está rodando na porta padrão 8080).

| Método HTTP | Descrição | URL e Parâmetros |
| :--- | :--- | :--- |
| `POST` | Cria um novo tipo de combustível. | `/tiposDeCombustivel` (Body JSON) |
| `GET` | Lista todos os tipos de combustível. | `/tiposDeCombustivel` |
| `GET` | Busca um tipo de combustível por ID. | `/tiposDeCombustivel/{id}` |
| `PUT` | Atualiza um tipo de combustível por ID. | `/tiposDeCombustivel/{id}` (Body JSON) |
| `DELETE` | Deleta um tipo de combustível por ID. | `/tiposDeCombustivel/{id}` |
| `POST` | Cria uma nova bomba de combustível. | `/bombasDeCombustivel` (Body JSON) |
| `GET` | Lista todas as bombas de combustível. | `/bombasDeCombustivel` |
| `GET` | Busca uma bomba de combustível por ID. | `/bombasDeCombustivel/{id}` |
| `PUT` | Atualiza uma bomba de combustível por ID. | `/bombasDeCombustivel/{id}` (Body JSON) |
| `DELETE` | Deleta uma bomba e seus abastecimentos associados. | `/bombasDeCombustivel/{id}` |
| `POST` | Registra um novo abastecimento. | `/abastecimento?quantidadeEmLitros={litros}&idBomba={idBomba}` |
| `GET` | Lista todos os abastecimentos. | `/abastecimento` |

## Pré-requisitos para Testar

Antes de começar, certifique-se de que você tem:

1. **Postman:** Instalado em sua máquina. Você pode baixá-lo em <https://www.postman.com/downloads/>.

2. **Aplicação Rodando:** Sua aplicação Spring Boot deve estar em execução (geralmente em `http://localhost:8080`).
## Passos para Testar no Postman

### TipoCombustivelController

Este Controller gerencia os tipos de combustível.

#### POST `/tiposDeCombustivel` (Criar um novo tipo de combustível)

1. Selecione o método `POST`.

2. **URL:** `http://localhost:8080/tiposDeCombustivel`

3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.

4. Cole o seguinte corpo JSON:

   ```
   {
     "nome": "AdBlue",
     "precoPorLitro": 4.50
   }
   ```

5. Clique em **`Send`**. O status esperado é `202 Accepted`.

#### GET `/tiposDeCombustivel` (Listar todos os tipos)

1. Selecione o método `GET`.

2. **URL:** `http://localhost:8080/tiposDeCombustivel`

3. Clique em **`Send`**. O status esperado é `200 OK` e o corpo da resposta deve conter uma lista de tipos de combustível.

#### GET `/tiposDeCombustivel/{id}` (Buscar por ID)

1. Selecione o método `GET`.

2. **URL:** `http://localhost:8080/tiposDeCombustivel/1` (ou qualquer ID existente).

3. Clique em **`Send`**. O status esperado é `200 OK` e o corpo deve retornar os detalhes do combustível com ID 1.

#### PUT `/tiposDeCombustivel/{id}` (Atualizar)

1. Selecione o método `PUT`.

2. **URL:** `http://localhost:8080/tiposDeCombustivel/1`

3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.

4. Cole o JSON com as informações atualizadas (o ID no corpo será ignorado):

   ```
   {
     "nome": "Gasolina Turbo",
     "precoPorLitro": 6.10
   }
   ```

5. Clique em **`Send`**. O status esperado é `200 OK`.

#### DELETE `/tiposDeCombustivel/{id}` (Deletar)

1. Selecione o método `DELETE`.

2. **URL:** `http://localhost:8080/tiposDeCombustivel/5` (substitua por um ID que você criou).

3. Clique em **`Send`**. O status esperado é `200 OK`.

### BombasCombustivelController

Este Controller gerencia as bombas de combustível.

#### POST `/bombasDeCombustivel` (Criar uma nova bomba)

1. Selecione o método `POST`.

2. **URL:** `http://localhost:8080/bombasDeCombustivel`

3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.

4. Cole o seguinte JSON, usando um id de um TipoCombustivel que você já criou (ex: 1 para Gasolina Comum):

   ```
   {
     "nome": "Bomba Premium",
     "tipoCombustivel": {
       "id": 1
     }
   }
   ```

5. Clique em **`Send`**. O status esperado é `202 Accepted`.

#### GET `/bombasDeCombustivel` (Listar todas as bombas)

1. Selecione o método `GET`.

2. **URL:** `http://localhost:8080/bombasDeCombustivel`

3. Clique em **`Send`**. O status esperado é `200 OK` e o corpo deve conter a lista de bombas.

#### GET `/bombasDeCombustivel/{id}` (Buscar por ID)

1. Selecione o método `GET`.

2. **URL:** `http://localhost:8080/bombasDeCombustivel/1`

3. Clique em **`Send`**. O status esperado é `200 OK` e o corpo deve retornar a bomba de ID 1.

#### PUT `/bombasDeCombustivel/{id}` (Atualizar)

1. Selecione o método `PUT`.

2. **URL:** `http://localhost:8080/bombasDeCombustivel/1`

3. Vá para a aba **`Body`**, selecione `raw` e escolha **`JSON`**.

4. Cole o JSON com o nome atualizado e a referência para outro tipo de combustível, se desejar:

   ```
   {
     "nome": "Bomba Principal - Gasolina Aditivada",
     "tipoCombustivel": {
       "id": 2
     }
   }
   ```

5. Clique em **`Send`**. O status esperado é `200 OK`.

### AbastecimentoController

Este Controller gerencia os registros de abastecimento.


#### GET `/abastecimento` (Listar todos os abastecimentos)

1. Selecione o método `GET`.

2. **URL:** `http://localhost:8080/abastecimento`

3. Clique em **`Send`**. O status esperado é `200 OK` e o corpo deve conter a lista de todos os abastecimentos registrados.
