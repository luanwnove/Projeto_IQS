# Guia de Entrega Final — Aulas 13, 14 e 15

## 1. Extrair e validar no VS Code

1. Extraia `Projeto_IQS_Final_Aulas_01_a_15.zip`.
2. Abra a pasta `Projeto_IQS` no VS Code.
3. Abra um terminal PowerShell nessa pasta.
4. Verifique `java -version` e `mvn.cmd -version`.
5. Execute os comandos em sequência:

```powershell
mvn.cmd clean verify
mvn.cmd checkstyle:check
mvn.cmd site
mvn.cmd spring-boot:run
```

Acesse `http://localhost:8080/swagger-ui.html`, cadastre dois produtos com `POST /produtos` e pesquise por `GET /produtos/buscar?nome=Xiaomi`. Interrompa o servidor com `Ctrl + C`.

## 2. Capturar evidências reais

Use `Win + Shift + S` e salve:

1. `01-maven-build.png`: `BUILD SUCCESS` após `clean verify`.
2. `02-jacoco.png`: `target/site/jacoco/index.html` aberto no navegador.
3. `03-swagger.png`: Swagger com rotas de produtos e pagamentos.
4. `04-busca-produtos.png`: retorno da busca por nome no Swagger/terminal.
5. `05-maven-site.png`: `target/site/index.html`.
6. `06-github-actions.png`: execução verde após publicação.
7. `07-sonarqube.png`: Quality Gate e métricas **reais** após análise.
8. `08-pull-request.png`: PR da US-001 com label `sprint-1` e CI.

Não crie prints fictícios ou valores estimados para a entrega.

## 3. Publicar preservando o histórico do GitHub

Repositório existente: `https://github.com/luanwnove/Projeto_IQS`.

Na pasta nova `Projeto_IQS`, sem mover arquivos manualmente:

```powershell
git init
git branch -M main
git remote add origin https://github.com/luanwnove/Projeto_IQS.git
git fetch origin
git reset --mixed origin/main
git status
```

Caso `git remote add` mostre `remote origin already exists`, use:

```powershell
git remote set-url origin https://github.com/luanwnove/Projeto_IQS.git
```

Revise se há arquivos deletados ou alterados inesperadamente. Não execute `git push --force`. Para registrar o trabalho por Pull Request (requisito das Aulas 14 e 15):

```powershell
git checkout -b feature/us-001-busca-produtos
git add -A
git commit -m "Aulas 13, 14 e 15 - MVP, busca e qualidade"
git push -u origin feature/us-001-busca-produtos
```

## 4. Abrir Issue, GitHub Projects e Pull Request

1. No GitHub, abra `Projeto_IQS` → **Issues** → **New issue** → User Story.
2. Crie a Issue com título `US-001: Busca de produtos por nome` e copie os critérios de `docs/USER_STORIES.md`.
3. Em **Projects**, crie um quadro com as colunas `Backlog`, `To Do`, `In Progress` e `Done`; inclua a Issue US-001.
4. Abra **Pull requests** → **New pull request**, selecione `base: main` e `compare: feature/us-001-busca-produtos`.
5. Título sugerido: `Aulas 13, 14 e 15 — Projeto final de IQS`.
6. No corpo do PR, substitua `Closes #` pelo número real da Issue (por exemplo, `Closes #8`).
7. Crie a label `sprint-1` se não existir e aplique ao PR.
8. Aguarde a execução real do CI, confira os testes e faça o merge do PR.

## 5. Configurar SonarQube Cloud

O Maven build roda sem segredos. Para análise no CI, abra **Settings → Secrets and variables → Actions** no repositório e cadastre:

| Tipo | Nome | Valor |
|---|---|---|
| Secret | `SONAR_TOKEN` | Token real do SonarQube Cloud |
| Variable | `SONAR_PROJECT_KEY` | Project Key do projeto `Projeto_IQS` no SonarQube Cloud |
| Variable | `SONAR_ORGANIZATION` | Organization Key do SonarQube Cloud |

Reexecute o workflow após definir os valores. Não cole credenciais nos arquivos nem nos prints. Se o projeto já utiliza análise automática, avalie manter apenas um método de análise para evitar duplicidade.

## 6. Atualizar a branch main e criar a tag v0.1

Depois do merge:

```powershell
git checkout main
git pull origin main
git tag -a v0.1 -m "MVP inicial de IQS"
git push origin v0.1
```

Confirme na aba **Tags**. Se `v0.1` já existir, não recrie nem sobrescreva: consulte o histórico e use outra tag sem duplicar nomes.

## 7. Entregar à professora ou professor

Envie o link `https://github.com/luanwnove/Projeto_IQS`, o PR associado à US-001, a tag e os prints exigidos no Teams ou na plataforma indicada. Caso o professor peça um arquivo compactado, entregue o ZIP da versão final **sem `target/`, segredos ou pasta `.git`**.

A publicação, a aprovação no CI e o merge não acontecem automaticamente só porque o ZIP foi gerado; são ações a realizar no GitHub.
