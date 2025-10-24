# Pasos para probar


1. cambiar datos de contraseña, url de la base de datos y usuario en el archivo "DatabaseConnection.java"
2. crear tablla en mysql si no la tene con estos datos:
CREATE TABLE actor (
  actor_id INT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50)
);

3. Ejecutar en la Raiz del proyecto: javac -cp "lib/mysql-connector-j-9.0.0.jar;src" -d target/classes src/main/java/com/ucc/Main.java
4. comando para probar: java -cp "lib/mysql-connector-j-9.0.0.jar;target/classes" com.ucc.Main

## INDIVIDUAL: Luis Fernando Cajigas
