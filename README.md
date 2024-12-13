# **Sistema de Tarefas**

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-15-red)](https://angular.io/)

## **Descrição**
Este é um sistema de gerenciamento de tarefas onde os usuários podem:
- Criar contas e fazer login.
- Criar, editar e deletar tarefas.
- Visualizar tarefas organizadas por status (Pendentes, Em andamento, Concluídas).
- Filtrar tarefas por prioridade.
- Utilizar funcionalidades de **drag and drop** para alterar o status das tarefas.

O sistema é composto por um backend desenvolvido em **Java com Spring Boot** e um frontend construído em **Angular**.

---

## **Funcionalidades**
- **Autenticação:**
  - Registro de usuários.
  - Login e gerenciamento de sessão com cookies.
  
- **Gerenciamento de Tarefas:**
  - Adicionar, editar e deletar tarefas.
  - Organização por status e prioridade.
  - Alteração do status por drag and drop.

- **Filtros:**
  - Filtrar tarefas por prioridade.

- **Notificações:**
  - Envio de e-mails após o registro do usuário.

---

## **Tecnologias Utilizadas**

### **Backend:**
- **Java 17**
- **Spring Boot 2.7**
  - Spring Data JPA
  - Hibernate
  - Spring Security (opcional)
- **MySQL** (banco de dados)
- **Maven** (gerenciamento de dependências)

### **Frontend:**
- **Angular 15**
- **TypeScript**
- **Material Design**
- **RxJS** (para manipulação de dados assíncronos)

---

## **Requisitos**

### **Backend:**
- **Java 17+**
- **MySQL** (ou outro banco de dados relacional configurado no `application.properties`)
- **Maven**

### **Frontend:**
- **Node.js 16+**
- **Angular CLI**

---

## **Como Rodar o Projeto**

### **1. Clonar o Repositório**
```bash
git clone https://github.com/seu-usuario/sistema-de-tarefas.git
cd sistema-de-tarefas
