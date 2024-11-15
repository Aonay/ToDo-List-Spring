# TODOList - Projeto Spring Boot

Aplicação de gerenciamento de tarefas (TODOList) desenvolvida em Java usando Spring Boot, com funcionalidades de CRUD (Create, Read, Update, Delete) e opções de filtro para organização de tarefas.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java standalone.
- **Spring Data JPA**: Abstração para interação com banco de dados.
- **Hibernate**: Implementação de JPA para mapeamento objeto-relacional.
- **Console**: Interface simples via terminal para interações do usuário.

## Estrutura do Projeto

- **Classe Principal**: Inicializa o aplicativo e exibe um menu interativo no console para gerenciar tarefas.
- **Modelo de Dados (`Tarefa`)**: Representa uma tarefa com atributos como título, descrição, prioridade, status e responsável.
- **Repositório (`TarefasRepository`)**: Interface que lida com persistência de dados, oferecendo métodos de CRUD e filtros específicos.
- **Serviço (`ListaTarefas`)**: Gerencia a lógica de negócios, conectando o repositório e a interface do usuário para operações como adicionar, atualizar e listar tarefas.

## Princípios e Paradigmas Utilizados

- **POO (Programação Orientada a Objeto)**: Estrutura baseada em classes que encapsulam dados e funcionalidades.
- **Injeção de Dependência**: Reduz acoplamento ao injetar serviços automaticamente com `@Autowired`.
- **Repository Pattern**: Camada de repositório para abstração e separação entre lógica de negócios e persistência de dados.

## Execução do Projeto

1. **Pré-requisitos**: Java 17+ e Maven instalados.
2. **Compilação e Execução**:
   ```bash
   mvn clean install
   mvn spring-boot:run
