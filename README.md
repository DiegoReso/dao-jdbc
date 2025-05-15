# Explorando a Essência do JDBC no Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este repositório foi criado com o objetivo de consolidar meus estudos sobre os fundamentos do Java Database Connectivity (JDBC), focando nos conceitos essenciais para quem está começando a interagir com bancos de dados em Java.

## Tópicos Abordados

Durante o desenvolvimento e estudo presente neste repositório, explorei os seguintes temas cruciais para iniciantes em JDBC:

* **Configuração do Ambiente JDBC:** Demonstração dos passos necessários para configurar um ambiente de desenvolvimento JDBC, incluindo a adição do driver JDBC específico para o banco de dados desejado.
* **Estabelecimento de Conexões com o Banco de Dados:** Exploração de como estabelecer uma conexão com um banco de dados utilizando a classe `DriverManager` e a interface `Connection`. Entendimento dos parâmetros necessários para a conexão (URL, usuário, senha).
* **Execução de Queries SQL:** Demonstração de como executar comandos SQL (SELECT, INSERT, UPDATE, DELETE) utilizando as interfaces `Statement` e `PreparedStatement`. Diferenças entre elas e as vantagens de usar `PreparedStatement` para prevenir SQL Injection.
* **Manipulação de Resultados:** Exploração de como processar os resultados de uma consulta SQL utilizando a interface `ResultSet`. Navegação pelos resultados, acesso aos dados das colunas por nome e índice, e tratamento de diferentes tipos de dados.
* **Trabalho com Transações:** Introdução ao conceito de transações e como controlar a atomicidade das operações no banco de dados utilizando os métodos `commit()` e `rollback()` da interface `Connection`.
* **Tratamento de Exceções JDBC:** Entendimento das exceções específicas que podem ocorrer durante a interação com o banco de dados (`SQLException`) e como tratá-las adequadamente para garantir a robustez da aplicação.
* **Encerrando Recursos JDBC:** Importância de fechar corretamente as conexões (`Connection`), statements (`Statement`, `PreparedStatement`) e result sets (`ResultSet`) para liberar recursos do banco de dados e evitar vazamentos de conexão.
