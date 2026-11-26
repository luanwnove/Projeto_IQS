# Rastreabilidade da Disciplina — Aulas 1 a 15

Este documento separa os conteúdos estudados dos artefatos presentes no pacote final. Os roteiros `.pdf` e `.java` das 15 aulas foram recebidos como material didático; o projeto acadêmico efetivamente publicado até a Aula 12 foi usado como base do código.

| Aula | Tema principal identificado nos materiais | Evidência no projeto final |
|---|---|---|
| 01 | Qualidade e legibilidade / Clean Code | Separação por camadas, nomes descritivos, validações |
| 02 | JUnit e integração contínua | Testes JUnit e `.github/workflows/ci.yml` |
| 03 | Análise de estilo e refatoração | `checkstyle.xml` e código organizado |
| 04 | SOLID e padrões de projeto | `ImpostoStrategy`, `FinanceUtils`, separação Controller/Service |
| 05 | Tratamento de erros e observabilidade | Status HTTP 400/404 e validação de entrada; instrumentação de produção não incluída |
| 06 | Testes JUnit 5 e parametrização | JUnit 5 e testes parametrizados em `ProdutoValidacaoParametrizadaTest` |
| 07 | Mocks e integração com banco | Mockito, H2 por padrão e perfil Testcontainers opcional |
| 08 | BDD e Cucumber | Cenários executáveis em `src/test/resources/features/produtos.feature` com Cucumber e JUnit Platform |
| 09 | Git Flow / PR e code review | Template de PR e instruções de branch/Issue |
| 10 | Pipeline CI e Quality Gate | Workflow de build e configuração opcional SonarQube Cloud |
| 11 | Análise estática e Strategy | `FinanceUtils`, `ImpostoStrategy` e testes existentes |
| 12 | Documentação: Javadoc, OpenAPI, Markdown | `Financeira`, Swagger UI, `ARCHITECTURE.md`, Maven Site |
| 13 | MVP JPA, H2, CRUD, testes, JaCoCo e tag | Entidade Produto, Repository, Service, Controller, testes, cobertura e guia de tag |
| 14 | US-001, busca, Github Projects e PR | `/produtos/buscar`, testes, `USER_STORIES.md` e instruções GitHub |
| 15 | CI/CD, testes, Testcontainers, Sonar e melhoria de qualidade | `ci.yml`, testes unitários/integração, perfil PostgreSQL opcional, método defensivo de preço |

## Itens que dependem de ação fora do ZIP

- Rodar Maven/JaCoCo e capturar resultados reais, pois não há execução Maven registrada neste pacote.
- Criar uma Issue US-001, configurar um GitHub Projects com colunas e abrir/mesclar um PR com label `sprint-1`.
- Confirmar o GitHub Actions e a análise SonarQube Cloud na conta do aluno.
- Executar o perfil Testcontainers com Docker se a demonstração PostgreSQL for exigida; o padrão H2 não necessita Docker.
- Deploy externo é opcional no material pós-aula e depende de uma conta e plataforma de hospedagem.

As aulas anteriores não são reapresentadas como 15 projetos separados: o objetivo desta entrega é um **projeto final integrado** sobre o histórico do repositório da disciplina.
