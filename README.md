# IQS — Aula 11: Análise Estática e Qualidade de Código

Projeto Maven em Java 17 com refatoração por Strategy, testes JUnit 5 e configuração para análise SonarQube.

## Executar os testes

```powershell
mvn.cmd clean verify
```

## Analisar no SonarQube local

Inicie o SonarQube com Docker e aguarde a inicialização:

```powershell
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```

Acesse http://localhost:9000, configure o projeto e gere um token de análise. No PowerShell:

```powershell
$env:SONAR_TOKEN = "SEU_TOKEN"
mvn.cmd clean verify sonar:sonar "-Dsonar.projectKey=iqs-aula-11" "-Dsonar.host.url=http://localhost:9000" "-Dsonar.token=$env:SONAR_TOKEN"
```

Não registre tokens no Git nem os inclua em capturas de tela.

## Evidências da atividade

1. Print do terminal com testes e análise concluídos.
2. Print do painel SonarQube com as métricas e o Quality Gate.
3. Registre em relatório próprio quais classes foram analisadas, as métricas observadas e as mudanças feitas.

O material da Aula 11 propõe refatorar duas classes e configurar um Quality Gate mínimo. As métricas só podem ser confirmadas após a análise real no SonarQube.
