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
- `public static void closeConnection()` — cierra la conexión si está abierta.

Puntos a revisar:
- Si quieres que el archivo `config.properties` esté en `resources`, cambia la carga para usar el classpath (`getResourceAsStream`) en lugar de `new File("config.properties")`.
- Maneja mejor las excepciones (re-throw o uso de loggers) en producción.

---

### `Estudiante` (ubicación: `src/main/java/com/uniajc/mvn/modelo/Estudiante.java`)

Descripción:
- Modelo que representa la entidad `estudiante` con campos `nombre` (String), `edad` (int) e `id_estudiante` (int).

Constructores y getters/setters:
- `public Estudiante()` — constructor vacío.
- `public Estudiante(String nombre, int edad, int id)` — constructor con valores.
- Métodos `getNombre()`, `setNombre(...)`, `getEdad()`, `setEdad(...)`, `getId()`, `setId(...)`.

Operaciones con BD (métodos estáticos):
- `insertarEstudiante(Estudiante estudiante)` — inserta en tabla `estudiante` usando `PreparedStatement`. La consulta en el código actual inserta `nombre` y `edad`.
- `obtenerTodosLosEstudiantes()` — devuelve `List<Estudiante>` leyendo `nombre` y `edad` desde la tabla.
- `actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado)` — actualiza registro buscando por `nombre`.
- `eliminarEstudiante(String nombre)` — elimina por `nombre`.

Notas:
- Actualmente el `INSERT` no establece `id_estudiante` (se asume que la tabla tiene un `AUTO_INCREMENT` para id). Si la tabla no tiene auto-increment, ajusta la lógica para insertar el id.
- Se usan `PreparedStatement` para evitar inyección SQL.

---

### `ControladorEstudiante` (ubicación: `src/main/java/com/uniajc/mvn/controlador/ControladorEstudiante.java`)

Descripción:
- Controlador que conecta el modelo `Estudiante` con la vista `VistaEstudiante`.

Estado y constructor:
- Mantiene una lista local `List<Estudiante> estudiantes` y referencia a la `VistaEstudiante`.
- Constructor actual: `public ControladorEstudiante(Estudiante modelo, VistaEstudiante vista)` — crea la lista interna y guarda la vista.

Principales métodos:
- `public void agregarEstudiante(Estudiante estudiante)` — llama a `Estudiante.insertarEstudiante(...)` y muestra un mensaje en consola.
- `public List<Estudiante> listarTodosLosEstudiantes()` — devuelve la lista consultando el modelo.
- `public void actualizarVista()` — obtiene la lista y delega a `vista.mostrarDetallesEstudiante(estudiantes)` para imprimirla.
- `public void actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado)` — delega al modelo para actualizar.
- `public void eliminarEstudiante(String nombre)` — delega al modelo para eliminar.

Extensiones sugeridas:
- Inyectar la dependencia de la fuente de datos (ej. pasar una interfaz DAO) para facilitar pruebas.
- Manejar y reportar errores de forma más explícita (logs/ excepciones) en lugar de solo imprimir en consola.

---

## Preparar la base de datos

El script `src/main/resources/db.sql` contiene la DDL necesaria. Desde PowerShell ejecuta:

```powershell
mysql -u root -p < "${PWD}\src\main\resources\db.sql"
```

Se te pedirá la contraseña del usuario MySQL.

## Compilar y ejecutar (PowerShell)

```powershell
mvn clean package
mvn exec:java -Dexec.mainClass="com.uniajc.mvn.Main"
```

o ejecutar el JAR generado:

```powershell
java -jar target\practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

---

## Notas de seguridad y buenas prácticas

- No subas el archivo `config.properties` con credenciales reales. Añade `config.properties` a `.gitignore` y mantén un `config.properties.example` sin credenciales.
- Para entornos reales, usa variables de entorno o un gestor de secretos.
- Mejora el manejo de errores y agrega logging en lugar de `System.out.println`.

---

Si quieres, puedo:

- Cambiar `ConexionDatabase` para leer `config.properties` desde `resources` o desde una variable de entorno.
- Añadir un `config.properties.example` y actualizar `.gitignore`.
- Implementar una capa DAO y tests unitarios básicos para `Estudiante`.

Indícame qué prefieres y lo implemento.
