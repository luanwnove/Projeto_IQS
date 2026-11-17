# IQS — Aula 12: Documentação de Software

Projeto acadêmico de documentação Java, REST e arquitetura. Esta versão mantém os exercícios Java da Aula 11 (`FinanceUtils`, `ImpostoStrategy` e `CalculadoraDesconto`) e incorpora uma API de pagamentos documentada.

## Requisitos

- JDK 17 ou superior
- Apache Maven 3.9 ou superior
- VS Code com suporte a Java (opcional)

Não é necessário instalar Docker, SonarQube ou banco de dados para realizar a Aula 12.

## Instalação e teste

```powershell
mvn.cmd clean verify
```

## Executar a API

```powershell
mvn.cmd spring-boot:run
```

Acesse a documentação interativa: http://localhost:8080/swagger-ui.html

A especificação OpenAPI está disponível em http://localhost:8080/v3/api-docs

## Endpoints

| Método | Endpoint | Comportamento |
|---|---|---|
| POST | `/pagamentos` | Cria pagamento (HTTP 201); retorna JSON e `Location` |
| GET | `/pagamentos/{id}` | Consulta pagamento (HTTP 200 ou 404) |

### Exemplo POST

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/pagamentos -ContentType 'application/json' -Body '{"descricao":"Mensalidade","valor":150.00}'
```

### Exemplo GET

```powershell
Invoke-RestMethod -Uri http://localhost:8080/pagamentos/1
```

O identificador depende dos pagamentos criados desde o início do processo.

## Gerar documentação Java

```powershell
mvn.cmd javadoc:javadoc
```

Abra `target/site/apidocs/index.html` no navegador. A classe `Financeira` contém descrição, `@author`, `@version`, `@param`, `@return`, `@throws`, `@see` e exemplo com `{@code ...}`.

## Gerar site Maven

```powershell
mvn.cmd site
```

Abra `target/site/index.html`. A geração do site é independente da execução do servidor REST.

## Arquitetura

Consulte [ARCHITECTURE.md](ARCHITECTURE.md) para o diagrama Mermaid e as decisões técnicas.

## Evidências recomendadas

1. Terminal: `BUILD SUCCESS` em `mvn.cmd clean verify`.
2. Página de Javadoc: `target/site/apidocs/index.html`.
3. Swagger UI mostrando `POST /pagamentos` e `GET /pagamentos/{id}`.
4. Site Maven: `target/site/index.html` após `mvn.cmd site`.
5. Diagrama Mermaid de `ARCHITECTURE.md` no GitHub.

As capturas devem refletir execuções reais. Não inclua a pasta `target` no controle de versão.
