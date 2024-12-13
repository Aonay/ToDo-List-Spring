# **Sistema de Tarefas**
<img align="center" src="https://skillicons.dev/icons?i=java,spring,angular,postgres,aws">

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
  - Filtrar tarefas por prioridade (mais filtros aplicados no backend)

- **Notificações:**
  - Envio de e-mails após o registro do usuário.
 
## **Rotas da API**

### **Usuários**
| Método | Endpoint              | Descrição                    |
|--------|-----------------------|------------------------------|
| POST   | `/usuarios/registrar` | Registra um novo usuário     |
| POST   | `/usuarios/login`     | Realiza login do usuário     |
| POST   | `/usuarios/logout`    | Realiza logout do usuário    |

### **Tarefas**
| Método | Endpoint                | Descrição                     |
|--------|-------------------------|-------------------------------|
| GET    | `/tarefas/status`       | Retorna tarefas por status    |
| POST   | `/tarefas`              | Adiciona uma nova tarefa      |
| GET    | `/tarefas/{id}`         | Retorna uma tarefa específica |
| PUT    | `/tarefas/{id}`         | Atualiza uma tarefa existente |
| DELETE | `/tarefas/{id}`         | Remove uma tarefa             |

### **Filtros**
| Método | Endpoint                      | Descrição                          |
|--------|-------------------------------|------------------------------------|
| GET    | `/tarefas/status/{status}`    | Retorna tarefas filtradas por status |
| GET    | `/tarefas/prioridade/{nivel}` | Retorna tarefas filtradas por prioridade |

---

## **Tecnologias Utilizadas**

### **Backend:**
- **Java 17**
- **Spring Boot 3.4**
  - Spring Data JPA
  - Hibernate
- **PostgreSQL**
- **Maven**

### **Frontend:**
- **Angular 18**
- **TypeScript**
- **Material Design + Angular Material**
- **RxJS**

---

## **Requisitos**

### **Backend:**
- **Java 17+**
- **PostgreSQL**
- **Maven**

### **Frontend:**
- **Node.js 17+**
- **Angular CLI**
---

## **Como Rodar o Projeto**

### **1. Clonar o Repositório**
```bash
git clone https://github.com/seu-usuario/sistema-de-tarefas.git
cd sistema-de-tarefas
```

### **2. Configurar o Backend**
```bash
cd backend
```
##2.1. Configure o arquivo src/main/resources/application.properties com os detalhes do banco de dados:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
##2.2. Compile e rode o backend:
```bash
mvn spring-boot:run
```

### **3. Clonar o Repositório**
##3.1. Acesse a pasta do frontend:
```bash
cd frontend
```
##3.2. Instale as dependências:
```bash
cd frontend
```
##3.3. Inicie o servidor:
```bash
ng serve
```
##3.4. Acesse o frontend em: http://localhost:4200
