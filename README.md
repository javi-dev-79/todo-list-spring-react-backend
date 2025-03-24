# 📌 todo-list-spring-react-backend

🚀 **Backend API for a To-Do List Application with Spring Boot**

This project is the **backend** for a To-Do List application that allows you to manage users, task lists, and individual
tasks. The REST API is built with **Spring Boot** and connects to a frontend developed with **React**.

## 🛠️ Technologies Used

- ☕ **Spring Boot 3.x**
- 📦 **Spring Data JPA** (for database interaction)
- 🔑 **Spring Security** (for authentication and authorization management)
- 📊 **PostgreSQL** (as a relational database)
- 🔐 **JWT (JSON Web Token)** for user authentication
- 🛠️ **Flyway** for database migration management
- 🧪 **JUnit** (for unit testing)

## 📌 Key Features

✅ **User Management**  
✔️ New user registration  
✔️ User login  
✔️ User retrieval  
✔️ Updating and deleting users

✅ **Task List Management**  
✔️ Create and list a user's task lists  
✔️ Modify/delete task lists

✅ **Task Management**  
✔️ Create new tasks  
✔️ Edit task titles, descriptions, due dates, and statuses  
✔️ Mark tasks as complete  
✔️ Delete tasks

✅ **Authentication**  
✔️ JWT security  
✔️ Private route protection  
✔️ Registration and login with credential validation

## 🚀 Installation and Configuration

### 1️⃣ Clone the Repository

```
git clone https://github.com/your-user/todo-list-spring-react-backend.git
cd todo-list-spring-react-backend
```

### 2️⃣ Create the database locally or in Docker with the following command:

```
docker run --name postgres-todo-db -e POSTGRES_USER=postgres -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=todo-db -p 5432:5432 -d postgres:latest
```

### 3️⃣ Configure the Database

Make sure you have a **PostgreSQL** database running. The database can be configured in the `application.yaml` file.

```yaml
spring:
datasource:
url: jdbc:postgresql://localhost:5432/todo_db
username: postgres
password: postgres
flyway:
enabled: true
locations: classpath:db/migration
```

### 4️⃣ Configure Environment Variables

```
JWT_SECRET=your-secret-key
```

The secret key must be at least 32 characters long.

### 5️⃣ Install Dependencies

```
./mvnw clean install
```

### 6️⃣ Start the Application

```
./mvnw spring-boot:run
```

## 📡 Connecting to the Frontend

This backend is designed to work with a frontend developed in React. Make sure the frontend is running
on [http://localhost:5173](http://localhost:5173) (or the configured port)
and that it is configured to make requests to [http://localhost:8080/api](http://localhost:8080/api).

## 🛠️ Useful Commands

| Command                  | Description                                          |
|--------------------------|------------------------------------------------------|
| `./mvnw clean install`   | Installs dependencies and cleans the project         |
| `./mvnw spring-boot:run` | Runs the Spring Boot application in development mode |
| `./mvnw test`            | Runs project tests                                   |
| `./mvnw flyway:migrate`  | Runs database migrations                             |
| `./mvnw flyway:repair`   | Repair database migrations                           |
| `./mvnw clean`           | Cleans up the project's build files                  |
| `./mvnw package`         | Creates an executable JAR package of the project     |

## 📌 Contribute

If you'd like to contribute to this project:

1. Fork the repository.
2. Create a new branch:

```
git checkout -b feature-new-feature
```

3. Make your changes and commit:

```
git commit -m "Add new feature"
```

4. Push your changes

```
git push origin feature-new-feature
```

5. Create a pull request on GitHub.

## 📄 License

This project is licensed under the **MIT License.**

---

# 📌 todo-list-spring-react-backend

🚀 **Backend API para la Aplicación de Lista de Tareas con Spring Boot**

Este proyecto es el **backend** de una aplicación de lista de tareas (**To-Do List**) que permite gestionar usuarios, listas de tareas y tareas individuales. La API REST está construida con **Spring Boot** y se conecta a un frontend desarrollado con **React**.

## 🛠️ Tecnologías Utilizadas

- ☕ **Spring Boot 3.x**
- 📦 **Spring Data JPA** (para la interacción con la base de datos)
- 🔑 **Spring Security** (para la gestión de autenticación y autorización)
- 📊 **PostgreSQL** (como base de datos relacional)
- 🔐 **JWT (JSON Web Token)** para la autenticación de usuarios
- 🛠️ **Flyway** para la gestión de migraciones de la base de datos
- 🧪 **JUnit** (para pruebas unitarias)

## 📌 Características Principales

✅ **Gestión de Usuarios**  
✔️ Registro de nuevos usuarios  
✔️ Inicio de sesión de usuarios  
✔️ Recuperación de usuarios  
✔️ Actualización y eliminación de usuarios

✅ **Gestión de Listas de Tareas**  
✔️ Crear y listar listas de tareas de un usuario  
✔️ Modificar/eliminar listas de tareas

✅ **Gestión de Tareas**  
✔️ Crear nuevas tareas  
✔️ Editar título, descripción, fecha de vencimiento y estado de las tareas  
✔️ Marcar tareas como completadas  
✔️ Eliminar tareas

✅ **Autenticación**  
✔️ Seguridad con JWT  
✔️ Protección de rutas privadas  
✔️ Registro e inicio de sesión con validación de credenciales

## 🚀 Instalación y Configuración

### 1️⃣ Clonar el Repositorio

   ```
        git clone https://github.com/your-user/todo-list-spring-react-backend.git
        cd todo-list-spring-react-backend
  ```

### 2️⃣ Crear la base de datos en local o en docker con el siguiente comando:

```
docker run --name postgres-todo-db -e POSTGRES_USER=postgres -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=todo-db -p 5432:5432 -d postgres:latest
```

### 3️⃣ Configurar la Base de Datos

Asegúrate de tener una base de datos **PostgreSQL** en ejecución. La base de datos se puede configurar en el archivo
`application.yaml`.

```yaml
    spring:
       datasource:
          url: jdbc:postgresql://localhost:5432/todo_db
          username: postgres
          password: postgres
       flyway:
          enabled: true
          locations: classpath:db/migration
```

### 4️⃣ Configurar las Variables de Entorno

   ```
      JWT_SECRET=your-secret-key
```

La clave secreta debe tener mínimo 32 caracteres.

### 5️⃣ Instalar Dependencias

   ```
./mvnw clean install
```

### 6️⃣ Iniciar la Aplicación

   ```
./mvnw spring-boot:run
```

## 📡 Conexión con el Frontend

Este backend está diseñado para trabajar con un frontend desarrollado en React.  
Asegúrate de que el frontend esté corriendo en [http://localhost:5173](http://localhost:5173) (o el puerto
configurado)  
y que esté configurado para hacer peticiones a [http://localhost:8080/api](http://localhost:8080/api).

## 🛠️ Comandos Útiles

| Comando                  | Descripción                                             |
|--------------------------|---------------------------------------------------------|
| `./mvnw clean install`   | Instala las dependencias y limpia el proyecto           |
| `./mvnw spring-boot:run` | Ejecuta la aplicación Spring Boot en modo de desarrollo |
| `./mvnw test`            | Ejecuta las pruebas del proyecto                        |
| `./mvnw flyway:migrate`  | Ejecuta las migraciones de la base de datos             |
| `./mvnw flyway:repair`   | Repara las migraciones de la base de datos              |
| `./mvnw clean`           | Limpia los archivos de compilación del proyecto         |
| `./mvnw package`         | Crea un paquete ejecutable JAR del proyecto             |

## 📌 Contribuir

Si deseas contribuir a este proyecto:

1. Haz un fork del repositorio.
2. Crea una nueva rama:

   ```
   git checkout -b feature-nueva-funcionalidad
   ```
3. Realiza los cambios y haz un commit:
   ```
      git commit -m "Añadir nueva funcionalidad"
   ```
4. Sube los cambios
   ```
      git push origin feature-nueva-funcionalidad
   ```

5. Crea un Pull Request en GitHub.

## 📄 Licencia

Este proyecto está bajo licencia **MIT.**

---



