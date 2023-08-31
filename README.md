# Empresa API Controller

Projeto API Empresa feito para realizar controle de funcionarios, departamento e reuniões.

> Em desenvolvimento

API produzida com **Spring-Boot** com **Spring-web, bean validation e spring-data JPA** até o momento

Para conseguir utilizar acesse o arquivo `application.properties` e defina a URL do banco de dados desejado, juntamente
com sua senha e usuario, como o exemplo a baixo:

```
spring.datasource.url=URL_EXEMPLO
spring.datasource.username=USUARIO_EXEMPLO
spring.datasource.password=SENHA_EXEMPLO
```

Deixo minha recomendação para utilizar o banco de dados em memoria do h2 para testes. Para acessá lo faça a seguinte
configuração no arquivo `application.properties`:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

Para documentação foi disponibilizado o swagger-ui para facilitar leitura e tornar a pratica mais dinâmica. Para acessar
rode a aplicação e execute a seguinte URL no navegador de preferência:

`http://localhost:8080/swagger-ui.html`

