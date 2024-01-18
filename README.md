# Custumer Service

### Requisitos

- 🌴 GitHub
- ☕ Java 8
- 📦 Maven

### Comandos Prova Prática

1. Clone o projeto: `https://github.com/filipemaulerm/segurosunimed-test.git`
2. Execute a aplicação.
3. Acesse: `http://localhost:8080/customers`
4. Neste ponto será retornado a lista de clientes pré-cadastrada.
5. Faça filtros de cliente nas buscas por nome, email e genero.
6. Adicione endpoints para criar um novo cliente, editar um cliente e excluir um cliente.
7. Valide os dados antes de cadastrar ou editar.
8. Pagine a listagem de clientes.
9. Possibilite o cadastro de múltiplos endereços para um cliente.
10. No cadastro de endereço permita inserir apenas o CEP carregando os dados via consumo do serviço: https://viacep.com.br/
11. Faça filtros de clientes nas buscas agora para os campos cidade e estado
12. Envie a url do seu repositório no github para análise.

Obs.: Será um diferencial implementações como: tratamento de exceções (RestControllerAdvice), testes, validações, uso de mecanismos modernos da linguagem, frontend, autenticação e documentação. 

# TESTE PRÁTICO FULLSTACK / JavaScript

Aplicação consiste no desenvolvimento de um CRUD simples e desenvolvimento de um frontEnd todo em HTML/JavaScript para cadastro e visualização de usuários e suas hierarquias.

## Tabela
* Customer
* Address

<br/><br/>
#### 📋 Clonando repositório

```
https://github.com/williancunhamoraes81/unimed.git
```
<br/><br/>

#### 🚢 Utilizando API

<b>LISTAR TODOS CLIENTES</b>
curl --location 'http://localhost:8080/customers'
<br/>
<br/>

<b>ALTERAR UM CLIENTE</b>
curl --location --request PUT 'http://localhost:8080/customers/2' \
--header 'Content-Type: application/json' \
--data-raw ' {  
    "name": "Thorr",
    "email": "thorr@vingadores.com",
    "gender": "M"
}'
<br/>
<br/>

<b>CRIANDO UM NOVO ENDEREÇO</b>
curl --location 'http://localhost:8080/address' \
--header 'Content-Type: application/json' \
--data ' {        
    "city": "Guarulhos",
    "street": "Rua Martins Fontes",
    "zipCode": "07194120",
    "customer": {
        "id": 2
    }
}'
<br/>
<br/>

<b>VERIFICA UM CEP</b>
curl --location 'viacep.com.br/ws/13382532/json/' \
--data ''
<br/>
<br/>

<b>CRIA UM ENDEREÇO BASEADO EM UM CEP</b>
curl --location 'http://localhost:8080/address/cep' \
--header 'Content-Type: application/json' \
--data ' {        
    "cep": "01312-000",
    "customer": 3
}'
<br/>
<br/>

<b>PESQUISA CLIENTE ATRA´VES DO NOME DA CIDADE</b>
curl --location 'http://localhost:8080/customers/search?city=S%C3%A3o%20Paulo'
<br/>
<br/>

<b>PESQUISA CLIENTE POR NOME / EMAIL / GENERO</b>
curl --location 'http://localhost:8080/customers/search?name=gamora'
curl --location 'http://localhost:8080/customers/search?email=aranha@vingadores.com'
curl --location 'http://localhost:8080/customers/search?gender=F'
<br/>
<br/>
