
# 📚 desafiobusquedalibros

¡Bienvenido a **desafiobusquedalibros**!  
Una app Java Spring Boot para buscar, registrar y explorar libros usando la API de [Gutendex](https://gutendex.com/books/) y PostgreSQL.

---

## 🚀 Características principales

- 🔎 **Buscar libros por título** y guardar resultados únicos en tu base de datos.
- 📋 **Listar libros y autores** registrados.
- 🧑‍💼 **Filtrar autores vivos** en un rango de años.
- 🌍 **Buscar libros por idioma**.
- 🏆 **Top 10 libros más descargados**.
- 👤 **Buscar autor por nombre** y más.

---

## 🛠️ Tecnologías

- Java 24+
- Spring Boot 3.5.4
- Maven
- Spring Data JPA
- PostgreSQL (pgAdmin 4)
- API Gutendex

---

## ⚡ Instalación rápida

1. **Clona el repositorio**
   ```bash
   [//github.com/YairZ21/Challenge-Literalura](https://github.com/YairZ21/Challenge-Literalura)
   ```

2. **Configura la base de datos**  
   Crea una base en pgAdmin 4 y actualiza las variables en `src/main/resources/application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/tu_basededatos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   ```

3. **Ejecuta la app**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🖥️ Menú interactivo

Al iniciar, verás un menú como este:

```
====
desafiobusquedalibros
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos después de un año
5. Listar libros por idioma
6. Top 10 libros más descargados
7. Buscar autor por nombre
8. Listar autores y número de libros
9. Salir
Elige opción:
```

---

## 💡 Ejemplo de uso

- Ingresa `1` para buscar un libro por título.
- Ingresa `5` para ver libros por idioma.
- ¡Explora todas las opciones!

---

## 📝 Notas

- Las búsquedas se guardan automáticamente en la base de datos.
- No se registran libros duplicados.
- Puedes consultar y administrar los datos desde pgAdmin 4.

---

## 🤝 Contribuciones

¡Pull requests y sugerencias son bienvenidas!

---



¡Disfruta explorando el mundo de los libros! 📖✨
```
