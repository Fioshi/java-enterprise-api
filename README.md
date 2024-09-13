# 🌟 Empresa API Controller 🌟

🎉 Bem-vindo ao projeto **Empresa API Controller**! 🎉a
Este projeto foi desenvolvido para gerenciar funcionários, departamentos, reuniões, tarefas e projetos de maneira eficiente e organizada. 📊

## 🚧 Em Desenvolvimento 🚧

A API foi construída utilizando **Spring Boot** com os seguintes módulos:

- Spring Web 🌐
- Bean Validation ✅
- Spring Data JPA 🗃️

## 🔧 Configuração Inicial 🔧

Para começar a utilizar a API, siga estes passos simples:

1. Acesse o arquivo `application.properties`.
2. Defina a URL do banco de dados desejado, juntamente com seu usuário e senha, como no exemplo abaixo:

    ```properties
    spring.datasource.url=URL_EXEMPLO
    spring.datasource.username=USUARIO_EXEMPLO
    spring.datasource.password=SENHA_EXEMPLO
    ```

### 💡 Dica: Banco de Dados em Memória H2 💡

Para testes, recomendamos o uso do banco de dados em memória H2. Configure da seguinte forma:

    ```properties
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    ```

## 📜 Documentação com Swagger 📜

A documentação da API está disponível via **Swagger-UI**. Isso facilita a leitura e torna a prática mais dinâmica! Para acessá-la, rode a aplicação e acesse a seguinte URL no seu navegador favorito:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 🚀

## ✉️ Configuração do Sistema de Envio de Emails ✉️

Para usar o sistema de envio de emails, configure o `application.properties` com as seguintes informações:

    ```properties
    spring.mail.username=********@gmail.com
    spring.mail.password=**************
    support.mail=*****@gmail.com
    ```

Substitua `username` e `support.mail` pelo email que deseja utilizar e insira sua senha usual de login. Caso tenha verificação em duas etapas, será necessário utilizar uma **senha de app**. Para obter sua senha de app, siga estes passos do próprio Google: [Suporte Google](https://support.google.com/accounts/answer/185833?hl=pt-BR) 🔐

---

