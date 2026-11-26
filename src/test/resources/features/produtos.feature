Feature: Busca de produtos no catálogo

  Scenario: Encontrar um produto pelo nome
    Given existe um produto chamado "Smartphone Xiaomi"
    When busco pelo nome "Xiaomi"
    Then recebo 1 produto na lista

  Scenario: Não encontrar produto inexistente
    Given existe um produto chamado "Smartphone Xiaomi"
    When busco pelo nome "Samsung"
    Then recebo 0 produto na lista
