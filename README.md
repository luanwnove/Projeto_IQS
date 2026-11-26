# Projeto Final de IQS — Aulas 1 a 15

Projeto de Gestão da Qualidade de Software da Fatec, consolidado ao final da Aula 15. Esta versão preserva os exercícios de refatoração e a API de pagamentos das Aulas 11–12, acrescentando o MVP de produtos das Aulas 13–15.

## Tecnologias

| Camada | Tecnologias |
|---|---|
| Backend | Java 17, Spring Boot 3.3, Spring Web |
| Persistência | Spring Data JPA, H2 em memória e PostgreSQL opcional via Testcontainers |
| Documentação | Springdoc OpenAPI, Swagger UI, Javadoc, Maven Site, Mermaid |
| Qualidade | JUnit 5, testes parametrizados, Mockito, MockMvc, Cucumber, JaCoCo, Checkstyle, SonarQube Cloud |
| Integração contínua | GitHub Actions em pushes, Pull Requests e execução manual |

## Pré-requisitos

- JDK 17 ou superior
- Maven 3.9 ou superior
- VS Code com extensões Java, se desejar
- Docker **somente** para o teste opcional de PostgreSQL com Testcontainers

## Início rápido (Windows / PowerShell)

Na pasta raiz do projeto:

```powershell
java -version
mvn.cmd -version
mvn.cmd clean verify
mvn.cmd checkstyle:check
mvn.cmd site
mvn.cmd spring-boot:run
```

Acesse a documentação em http://localhost:8080/swagger-ui.html e o OpenAPI JSON em http://localhost:8080/v3/api-docs.

`mvn.cmd clean verify` executa testes JUnit, Mockito, cenários BDD/Cucumber, os testes de integração MockMvc + H2 e produz o relatório JaCoCo em `target/site/jacoco/index.html`.

`mvn.cmd site` gera o site em `target/site/index.html`. O Javadoc fica em `target/site/apidocs/index.html`.

## Endpoints de Produtos — Aulas 13–15

| Método | Rota | Resultado |
|---|---|---|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Detalhes ou HTTP 404 |
| `GET` | `/produtos/buscar?nome=Xiaomi` | US-001: pesquisa por nome, ignorando maiúsculas/minúsculas |
| `POST` | `/produtos` | Cadastra um produto, HTTP 201 e cabeçalho `Location` |
| `PUT` | `/produtos/{id}` | Atualiza nome e preço |
| `DELETE` | `/produtos/{id}` | Exclui produto, HTTP 204 |

### Criar um produto

```powershell
Invoke-RestMethod -Uri http://localhost:8080/produtos -Method Post -ContentType 'application/json' -Body '{"nome":"Smartphone Xiaomi","preco":1299.90}'
```

### Buscar por nome

```powershell
Invoke-RestMethod -Uri 'http://localhost:8080/produtos/buscar?nome=Xiaomi'
```

### Validar campos obrigatórios

O campo `nome` não pode estar em branco nem ultrapassar 120 caracteres. O preço deve ser positivo, com até oito dígitos inteiros e duas casas decimais. Dados inválidos retornam HTTP 400.

## Endpoints anteriores — Aula 12

| Método | Rota | Resultado |
|---|---|---|
| `POST` | `/pagamentos` | Registra pagamento em memória |
| `GET` | `/pagamentos/{id}` | Recupera um pagamento ou responde 404 |

A API de pagamentos continua disponível para demonstrar documentação REST e testes. Os dados de pagamentos são mantidos apenas em memória; os produtos são persistidos em H2 durante a execução.

Os cenários BDD estão em `src/test/resources/features/produtos.feature`; o Cucumber gera `target/cucumber-report.html`.

## Testes e qualidade

```powershell
mvn.cmd clean verify
mvn.cmd checkstyle:check
mvn.cmd javadoc:javadoc
mvn.cmd site
```

Para executar especificamente os testes de integração com PostgreSQL, **somente se tiver Docker instalado e ativo**:

```powershell
mvn.cmd -Ppostgres-it verify
```

A integração padrão não utiliza Docker: é executada com H2, `MockMvc` e `maven-failsafe-plugin`. O perfil PostgreSQL é opcional e separado para não impedir o trabalho em computadores sem Docker.

## Integração com SonarQube Cloud

O workflow `.github/workflows/ci.yml` executa build, testes, JaCoCo, Checkstyle e Maven Site em pushes e Pull Requests. A etapa de análise SonarQube Cloud funciona após configurar no GitHub:

- Secret `SONAR_TOKEN`: token emitido no SonarQube Cloud.
- Variable `SONAR_PROJECT_KEY`: chave do projeto existente no SonarQube Cloud.
- Variable `SONAR_ORGANIZATION`: chave da organização SonarQube Cloud.

Sem essas três configurações, o CI continua executando build e testes, mas **não** realiza a análise SonarQube. O status real do Quality Gate deve ser conferido no portal; não é inferido do resultado do Maven.

## Planejamento, documentação e entrega

- [Arquitetura e decisões técnicas](ARCHITECTURE.md)
- [User Stories, MoSCoW e Definition of Done](docs/USER_STORIES.md)
- [Rastreabilidade das aulas e entregáveis](docs/AULAS_01_A_15.md)
- [Passo a passo de publicação e prints](docs/GUIA_ENTREGA.md)

## Restrições

Este projeto é um MVP educacional. O H2 é volátil e os dados não sobrevivem ao encerramento da aplicação. Não há autenticação, autorização, HTTPS configurado ou infraestrutura de produção. As verificações no SonarQube, os Pull Requests, as Issues e os resultados da CI dependem de execução real na conta GitHub do aluno.
