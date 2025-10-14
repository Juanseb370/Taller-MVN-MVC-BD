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
```

### Claves disponibles
- `URL` — Cadena JDBC para conectarse a MySQL.
- `USERNAME` — Usuario de la base de datos.
- `PASSWORD` — Contraseña del usuario.

> Seguridad: no subas credenciales reales a repositorios públicos. Para entornos reales usar variables de entorno o gestores de secretos.

## Cómo la aplicación carga la configuración

La clase `ConexionDatabase` (en `src/main/java/com/uniajc/mvn/modelo/ConexionDatabase.java`) carga el archivo `config.properties` desde la raíz del proyecto con `new File("config.properties")` y lee las propiedades `URL`, `USERNAME` y `PASSWORD`. Luego crea la conexión JDBC usando `DriverManager.getConnection(URL, USERNAME, PASSWORD)`.

Si prefieres ubicar `config.properties` en otro sitio, actualiza la ruta en `ConexionDatabase` o modifica el código para leer la ruta desde una variable de entorno.

## Preparar la base de datos

El script SQL para crear la base y las tablas está en `src/main/resources/db.sql`.

Desde PowerShell (Windows) puedes ejecutar:

```powershell
# Ejecuta el script db.sql (te pedirá la contraseña de MySQL)
# Ajusta el usuario y la ruta si es necesario
mysql -u root -p < "${PWD}\src\main\resources\db.sql"
```

También puedes abrir el archivo `db.sql` en MySQL Workbench o phpMyAdmin y ejecutarlo manualmente.

## Compilar y ejecutar (PowerShell)

1. Compilar con Maven:

```powershell
mvn clean package
```

2. Ejecutar la aplicación (opción 1: usando el plugin exec en el pom, si está configurado):

```powershell
mvn exec:java -Dexec.mainClass="com.uniajc.mvn.Main"
```

O (opción 2) ejecutar el JAR generado:

```powershell
# Ajusta el nombre del jar según tu build
java -jar target\practica-mvn-mvc-bd-1.0-SNAPSHOT.jar
```

## Notas sobre errores comunes

- Archivo `config.properties` no encontrado: asegúrate de ejecutar la app desde la raíz del proyecto o proveer la ruta correcta.
- Credenciales incorrectas: verifica `USERNAME`/`PASSWORD` y que el usuario tenga permisos sobre la base `practicamvc`.
- Driver JDBC: el `pom.xml` debe incluir el conector MySQL (`mysql-connector-java`).

## Mejores prácticas y alternativas

- En lugar de almacenar credenciales en texto plano, usa variables de entorno o un gestor de secretos.
- Para múltiples entornos (dev/test/prod) mantén archivos de configuración separados y seguros.

---

Si quieres, puedo:

- Añadir lectura de `config.properties` desde la carpeta `resources`.
- Cambiar `ConexionDatabase` para aceptar la ruta por variable de entorno.
- Añadir instrucciones para crear un archivo `.env` y un pequeño script para cargarlo en Windows.

Indícame cuál prefieres y lo implemento.
    } catch (Exception e) {
