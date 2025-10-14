# Práctica Maven MVC con Base de Datos

Este proyecto es una práctica de arquitectura MVC utilizando Java, Maven y conexión a base de datos MySQL.

## Estructura del Proyecto

- `src/main/java/com/uniajc/mvn/` — Código fuente Java organizado en los paquetes `modelo`, `vista` y `controlador`.
- `src/main/resources/` — Recursos adicionales (vacío por defecto).
- `config.properties` — Archivo de configuración para la conexión a la base de datos.
- `pom.xml` — Archivo de configuración de Maven.

## Configuración de la Base de Datos

El proyecto utiliza un archivo `config.properties` para definir los parámetros de conexión a la base de datos MySQL. Este archivo debe estar en la raíz del proyecto y tener el siguiente formato:

```
URL=jdbc:mysql://localhost:3306/practica-mvc
USERNAME=root
PASSWORD=Pancha2025
# Práctica Maven MVC con Base de Datos

Este proyecto es una práctica de arquitectura MVC en Java usando Maven y MySQL. Aquí se documenta cómo usar el archivo `config.properties` que contiene la configuración de la conexión a la base de datos.

## Ubicación del archivo de configuración

El archivo `config.properties` se encuentra en la raíz del proyecto y se utiliza para leer la URL JDBC, usuario y contraseña que usa la clase `ConexionDatabase`.

Contenido actual (archivo incluido en este proyecto):

```
URL=jdbc:mysql://localhost:3306/practicamvc
USERNAME=root
PASSWORD=Pancha2025
# Práctica Maven MVC con Base de Datos

Este proyecto es una práctica de arquitectura MVC en Java usando Maven y MySQL. A continuación se documentan las clases principales, cómo se configura la conexión y cómo ejecutar la aplicación.

## Resumen de archivos importantes
- `config.properties` — Archivo en la raíz con la configuración JDBC (URL, usuario, contraseña).
- `src/main/resources/db.sql` — Script SQL para crear la base y la tabla `estudiante`.
- Código fuente: `src/main/java/com/uniajc/mvn/` con paquetes `modelo`, `vista`, `controlador`.

---

## `config.properties` (ejemplo)

```
URL=jdbc:mysql://localhost:3306/practicamvc
USERNAME=root
PASSWORD=Pancha2025
```

Coloca este archivo en la raíz del proyecto cuando ejecutes la aplicación desde la línea de comando. En producción usa variables de entorno o un gestor de secretos.

---

## Documentación de clases

### `Main` (ubicación: `src/main/java/com/uniajc/mvn/Main.java`)

Descripción:
- Programa principal. Pide al usuario el `nombre` y `edad` por consola, crea un objeto `Estudiante`, instancia `ControladorEstudiante` y agrega el estudiante a la base.

Flujo relevante:
- Abre conexión a la base con `ConexionDatabase.getConnection()` (se recomienda verificar la conexión antes de pedir entrada si es necesario).
- Usa `Scanner` para leer datos del usuario.
- Crea `Estudiante estudiante = new Estudiante(nombre, edad, 0);` y lo pasa al `ControladorEstudiante`.

Nota práctica:
- Evita declarar la misma variable dos veces en un mismo bloque (posible error de "duplicate local variable"). Reusar la variable o usar nombres diferentes para cada instancia.
- Cuando uses `scanner.nextInt()` seguido de `scanner.nextLine()`, consume el salto de línea pendiente con un `scanner.nextLine()` adicional si vas a leer más texto.

Ejemplo de uso (resumen):
- Ejecutar y seguir prompts en consola para insertar un registro.

---

### `ConexionDatabase` (ubicación: `src/main/java/com/uniajc/mvn/modelo/ConexionDatabase.java`)

Descripción:
- Clase utilitaria que crea y mantiene una única conexión JDBC (`Connection`) a MySQL.
- Lee `config.properties` (ruta relativa a la raíz del proyecto) y obtiene las propiedades `URL`, `USERNAME`, `PASSWORD`.

Comportamiento y métodos:
- `public static Connection getConnection()` — devuelve la conexión única (la crea si no existe). Carga el driver `com.mysql.cj.jdbc.Driver` y usa `DriverManager.getConnection(url, username, password)`.
# Práctica Maven MVC con Base de Datos (Java + MySQL)

Este proyecto es una práctica de arquitectura MVC (Modelo-Vista-Controlador) escrita en Java usando Maven y una base de datos MySQL.

## Estructura del proyecto

- `src/main/java/com/uniajc/mvn/` — Código fuente Java organizado en los paquetes `modelo`, `vista` y `controlador`.
	- `modelo` — clases que representan entidades y manejan operaciones con la base de datos (`Estudiante`, `Profesor`, `ConexionDatabase`).
	- `vista` — clases que muestran información por consola (`VistaEstudiante`, `VistaProfesor`).
	- `controlador` — controladores que coordinan modelo y vista (`ControladorEstudiante`, `ControladorProfesor`).
- `src/main/resources/db.sql` — script SQL para crear las tablas usadas en la práctica.
- `config.properties` — archivo de configuración con los parámetros JDBC (no incluir credenciales reales en el repositorio).
- `pom.xml` — definición del proyecto Maven y dependencias.

## Resumen de clases clave

- `Main` (`src/main/java/com/uniajc/mvn/Main.java`)
	- Punto de entrada. Inicializa la conexión (llamando a `ConexionDatabase.getConnection()`), y contiene ejemplos de cómo insertar, actualizar y eliminar `Estudiante` y `Profesor` usando `Scanner` para entrada por consola.

- `ConexionDatabase` (`src/main/java/com/uniajc/mvn/modelo/ConexionDatabase.java`)
	- Clase utilitaria que crea y devuelve una conexión JDBC a MySQL leyendo `config.properties`.

- `Estudiante` y `Profesor` (`src/main/java/com/uniajc/mvn/modelo/`)
	- Modelos con métodos estáticos para operaciones CRUD: `insertar...`, `obtenerTodos...`, `actualizar...`, `eliminar...`.

- `ControladorEstudiante` y `ControladorProfesor` (`src/main/java/com/uniajc/mvn/controlador/`)
	- Coordinan entre los modelos y las vistas. Por ejemplo, `ControladorProfesor.actualizarVistaP()` obtiene la lista de profesores y delega la impresión a `VistaProfesor`.

## Configuración de la base de datos

La aplicación usa un archivo `config.properties` en la raíz del proyecto con las siguientes claves:

```
URL=jdbc:mysql://localhost:3306/practicamvc
USERNAME=root
PASSWORD=tu_contraseña
```

Recomendaciones:
- No subir `config.properties` con credenciales reales: usa `config.properties.example` en su lugar y añade `config.properties` a `.gitignore`.
- Alternativamente, modifica `ConexionDatabase` para leer variables de entorno en lugar de un archivo.

## Crear la base de datos y tablas

Ejecuta el script SQL incluido para crear la base y las tablas (desde PowerShell):

```powershell
mysql -u root -p < "${PWD}\src\main\resources\db.sql"
```

Se te pedirá la contraseña del usuario MySQL.

Nota: verifica que la URL en `config.properties` apunte a la base correcta (nombre de la BD y puerto).

## Compilar y ejecutar (PowerShell)

Compilar con Maven:

```powershell
mvn clean package
```

Ejecutar directamente desde Maven:

```powershell
mvn exec:java -Dexec.mainClass="com.uniajc.mvn.Main"
```

O ejecutar el JAR generado:

```powershell
java -jar target\practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

## Uso desde consola (descripción breve)

El `Main` contiene código (algunas secciones comentadas) que pide entrada por consola para insertar/actualizar/eliminar estudiantes y profesores. Ejemplos:

- Insertar profesor:
	- Se solicita `nombre` y `materia` y se llama a `Profesor.insertarProfesor(...)`.
- Actualizar profesor:
	- Se solicita el `nombre` original y los nuevos datos, y se llama a `Profesor.actualizarProfesor(...)`.
- Eliminar profesor:
	- Se solicita el `nombre` y se llama a `Profesor.eliminarProfesor(...)`.

Ten en cuenta que el código actual usa búsquedas/actualizaciones por `nombre`, lo cual no es ideal en producción (mejor usar `id` como clave primaria estable).

## Notas de seguridad y buenas prácticas

- No incluyas credenciales en el repositorio. Usa `config.properties.example` o variables de entorno.
- Recomendado: cambiar las operaciones para utilizar `id` (clave primaria auto-increment) en lugar de búsquedas por `nombre`.
- Añadir manejo de excepciones más robusto y logging (`SLF4J` + `logback` / `log4j`) en lugar de `System.out.println`.
- Cerrar `Scanner` y conexiones adecuadamente; evitar declarar múltiples `Scanner` sobre `System.in`.

## Archivos sugeridos a agregar (próximos pasos)

1. `config.properties.example` — plantilla sin credenciales. Ejemplo:

```
URL=jdbc:mysql://localhost:3306/practicamvc
USERNAME=root
PASSWORD=
```

2. Actualizar `.gitignore` para excluir `config.properties`.
3. Refactor: implementar una capa DAO para `Estudiante` y `Profesor` que use `id` para operaciones.
4. Añadir pruebas unitarias para métodos del modelo usando una base de datos en memoria (H2) o pruebas de integración con Docker/MySQL.

## Cómo puedo ayudarte más

Puedo:

- Generar `config.properties.example` y actualizar `.gitignore`.
- Cambiar `ConexionDatabase` para leer desde `resources` o variables de entorno.
- Refactorizar `Profesor`/`Estudiante` para usar `id` en vez de nombre en actualizaciones/eliminaciones.
- Añadir pruebas unitarias o integración.



---

_Archivo regenerado y limpiado el contenido duplicado. Si quieres que adapte el README a otro idioma o estilo (más minimalista o con secciones de desarrollo), dime cuál._
