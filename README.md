# Práctica Maven MVC con Base de Datos

Este proyecto Realizado por JUAN SEBASTIAN ALMENDRA Y SEBASTIAN ABADIA es una práctica de arquitectura MVC utilizando Java, Maven y conexión a base de datos MySQL.

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

# Práctica Maven MVC con Base de Datos

Proyecto de ejemplo que implementa la arquitectura MVC en Java usando Maven y una base de datos MySQL.

## Resumen

- Lenguaje: Java 11+ (compatible con Java 8 en la mayoría de los casos).
- Gestión de dependencias y build: Maven
- UI: Swing (interfaz gráfica simple en `VentanaPrincipal`)
- Estructura: `modelo`, `vista`, `controlador`

Esta práctica sirve como ejemplo para aprender cómo organizar una aplicación Java con patrones básicos y cómo conectar operaciones CRUD sencillas a una base de datos MySQL.

## Estructura del proyecto (ubicaciones clave)

- `src/main/java/com/uniajc/mvn/` — Código fuente Java.
  - `modelo` — Entidades y utilidades de acceso a datos (`ConexionDatabase`, `Estudiante`, `Profesor`, `Cursos`).
  - `vista` — Clases que implementan la interfaz gráfica (`VentanaPrincipal`, `VistaEstudiante`, `VistaProfesor`).
  - `controlador` — Lógica para coordinar `modelo` y `vista` (`ControladorEstudiante`, `ControladorProfesor`).
- `src/main/resources/db.sql` — Script SQL para crear la estructura de la base de datos usada en la práctica.
- `pom.xml` — Configuración de Maven.

## Requisitos previos

- Java JDK instalado (recomendado Java 11 o superior).
- Maven instalado (para compilar y empaquetar).
- MySQL o MariaDB disponible localmente o en red.

## Configuración de la base de datos

La aplicación espera leer las credenciales y la URL de conexión desde un archivo `config.properties` ubicado en la raíz del proyecto (junto a `pom.xml`) o desde variables de entorno si decides modificar `ConexionDatabase`.

Ejemplo de `config.properties` (NO incluya credenciales reales en el repositorio):

```
URL=jdbc:mysql://localhost:3306/practicamvc
USERNAME=root
PASSWORD=tu_contraseña_aqui
```

Recomendación: crea `config.properties` a partir del archivo `config.properties.example` y agrega `config.properties` a `.gitignore`.

## Crear la base de datos y tablas

Importa el script SQL incluido para crear la base de datos y tablas. En PowerShell (Windows):

```powershell
mysql -u root -p < "${PWD}\src\main\resources\db.sql"
```

Se te pedirá la contraseña del usuario MySQL.

Si prefieres ejecutar manualmente las sentencias, abre `src/main/resources/db.sql` y ejecútalas en tu cliente MySQL.

## Compilar y ejecutar

Desde PowerShell en la raíz del proyecto:

```powershell
mvn clean package
mvn -q -Dexec.mainClass="com.uniajc.mvn.Main" exec:java
```

O ejecuta el JAR generado (después de `mvn package`):

```powershell
java -jar target\practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

Nota: el proyecto actualmente abre una ventana Swing (`VentanaPrincipal`). Hay también un `Main` con la misma funcionalidad de arranque.

## Uso de la aplicación

- Interfaz gráfica: `VentanaPrincipal` contiene pestañas para Estudiantes, Profesores y Cursos.
  - Estudiantes: agrega/actualiza/elimina usando el `ControladorEstudiante`.
  - Profesores: operaciones directamente desde métodos estáticos de `Profesor`.
  - Cursos: operaciones directamente desde métodos estáticos de `Cursos`.

Observaciones:
- Algunas operaciones (por ejemplo, `Profesor.insertarProfesor(...)`) usan métodos estáticos en las clases del modelo. Esto funciona para la práctica, pero en aplicaciones reales es preferible usar una capa DAO o servicios con inyección de dependencias.
- Muchas operaciones usan `nombre` como clave para buscar/actualizar/eliminar. Es más robusto utilizar `id` (clave primaria) en las consultas.

## Seguridad y buenas prácticas

- No incluir `config.properties` con credenciales en el repositorio.
- Preferir variables de entorno o un gestor de secretos en entornos de producción.
- Validar y sanear cuidadosamente los datos de entrada si los recibes desde la UI o la red.
- Manejar recursos (conexiones, statements, result sets) correctamente y cerrarlos en bloques `finally` o usar `try-with-resources`.
- Añadir logging (SLF4J + Logback/Log4j2) en lugar de `System.out.println` o `JOptionPane` para errores de fondo.

## Pruebas y mejoras recomendadas (próximos pasos)

- Añadir un `config.properties.example` y actualizar `.gitignore` para excluir el archivo real.
- Refactorizar `Estudiante`, `Profesor` y `Cursos` para usar `id` en las operaciones CRUD y separar la lógica de acceso a datos en clases DAO.
- Añadir pruebas unitarias e integración (por ejemplo con H2 en memoria o un contenedor Docker para MySQL).
- Añadir manejo de transacciones cuando una operación afecte varias tablas.
- Mejorar manejo de errores y mostrar mensajes más informativos en la UI.

## Archivos sugeridos para añadir

- `config.properties.example` — Plantilla sin credenciales.
- `.gitignore` — Asegúrate de incluir `config.properties`.

## ¿Necesitas que haga esto por ti?

Puedo:
- Generar `config.properties.example` y actualizar `.gitignore`.
- Refactorizar los modelos para usar `id` y crear DAOs.
- Añadir pruebas automatizadas con JUnit y una base H2 para CI.

---

Para dudas o problemas, contacta al autor o revisa la documentación en el código fuente.



TALLER REALIZADO POR SEBASTIAN ABADIA Y SEBASTIAN ALMENDRA
