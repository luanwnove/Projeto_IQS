# Planejamento Ágil e User Stories — Aula 14

## Backlog priorizado (MoSCoW)

| ID | User Story | Prioridade | Situação no pacote |
|---|---|---|---|
| US-001 | Como cliente, quero buscar produtos por nome para encontrar itens rapidamente. | Must Have | Implementada no código, testes preparados |
| US-002 | Como gestor, quero cadastrar produtos com nome e preço para manter um catálogo. | Must Have | Implementada no código, testes preparados |
| US-003 | Como gestor, quero alterar e excluir produtos para manter o catálogo atualizado. | Should Have | Implementada no código, testes preparados |
| US-004 | Como cliente, quero consultar os detalhes de um produto para verificar seu preço. | Should Have | Implementada no código, testes preparados |

**Atenção:** os estados acima descrevem o código do ZIP, não a existência de Issues, quadros ou Pull Requests no GitHub. Eles precisam ser criados/publicados na conta do aluno.

## US-001 — Busca de produtos por nome

> Como cliente, quero buscar produtos por nome para encontrar itens rapidamente.

### Critérios de aceitação

- `GET /produtos/buscar?nome=Xiaomi` responde HTTP 200.
- O corpo é um array JSON, com itens cujo nome contém o termo pesquisado.
- A busca ignora diferenças entre letras maiúsculas e minúsculas.
- Quando não há correspondências, a resposta é um array vazio.
- Quando o parâmetro `nome` está ausente ou vazio, o resultado é HTTP 400.
- Existem testes unitários com Mockito e testes de integração com MockMvc/H2.

### Tarefas

- [x] Implementar query method no `ProdutoRepository`.
- [x] Implementar regra no `ProdutoService`.
- [x] Adicionar rota de busca no `ProdutoController`.
- [x] Adicionar testes unitários e de integração.
- [x] Documentar a API no Swagger/OpenAPI.
- [ ] Criar Issue real e associar PR no GitHub.

## US-002 — Cadastro de produtos

> Como gestor, quero cadastrar produtos com nome e preço para manter um catálogo.

Critérios: `POST /produtos` retorna HTTP 201, `Location` e produto criado; nome obrigatório; preço positivo e com até duas casas decimais; cenários inválidos retornam HTTP 400.

## US-003 — Manutenção do catálogo

> Como gestor, quero atualizar e remover produtos para manter as informações corretas.

Critérios: `PUT /produtos/{id}` atualiza nome e preço; `DELETE /produtos/{id}` retorna HTTP 204; produtos inexistentes retornam HTTP 404.

## US-004 — Consulta de produto

> Como cliente, quero consultar os detalhes de um produto para verificar o preço.

Critérios: `GET /produtos/{id}` retorna produto existente; ausente retorna HTTP 404.

## Definition of Done

- [ ] Alteração revisada por pelo menos um colega, quando houver equipe.
- [ ] Testes unitários e de integração executados com sucesso.
- [ ] Cobertura JaCoCo inspecionada.
- [ ] Build e verificações de estilo aprovados no GitHub Actions.
- [ ] SonarQube Cloud conferido e sem problemas críticos no escopo do projeto, quando configurado.
- [ ] Swagger e documentação Markdown atualizados.
- [ ] Issue associada ao PR e label `sprint-1` aplicada.

O status dos itens de execução e revisão só pode ser marcado após a verificação real no GitHub e nas ferramentas de qualidade.
