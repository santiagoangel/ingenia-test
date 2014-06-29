ingenia-test
============

API basada en RESTful/json que permite la administración y autenticación de aplicaciones de  terceros.



Utiliza JPA 2 para el acceso y modelado de BD.

Utiliza Java WS.RS para crear servicios web REST con respuesta JSON.

Es un proyecto validado para JEE 6 y también se ejecuta en Tomcat ocupando los jars JEE del proyecto TomEE.


#
Requisitos:

(Fedora 20)
yum install mariadb-server
systemctl start mariadb.service

/usr/bin/mysql_secure_installation 

mysql -h localhost -u root -p


MariaDB [(none)]> CREATE DATABASE ingenia;
Query OK, 1 row affected (0.00 sec)

MariaDB [(none)]> CREATE USER 'ingenia_user'@'localhost' IDENTIFIED BY 'password';
Query OK, 0 rows affected (0.00 sec)

MariaDB [(none)]> GRANT ALL ON ingenia.* TO 'ingenia_user'@'localhost';
Query OK, 0 rows affected (0.00 sec)

MariaDB [(none)]> FLUSH PRIVILEGES;
Query OK, 0 rows affected (0.00 sec)

MariaDB [(none)]> source /archivos/ingeniadb.sql
Database changed
Query OK, 0 rows affected (0.07 sec)

Query OK, 0 rows affected (0.06 sec)

Query OK, 0 rows affected (0.05 sec)

Query OK, 1 row affected (0.01 sec)

Query OK, 0 rows affected (0.16 sec)               
Records: 0  Duplicates: 0  Warnings: 0

MariaDB [ingenia]> select *from apps;
Empty set (0.00 sec)

MariaDB [ingenia]> select *from users;
+----------+--------------+
| username | password     |
+----------+--------------+
| admin    | testpassword |
+----------+--------------+
1 row in set (0.00 sec)

MariaDB [ingenia]> 



###
Configuración:
Editar src/main/webapp/WEB-INF/resources.xml de acuerdo a nuestros datos de acceso a BD.

###
Ejecución:
cd ingenia
mvn clean install tomee:run


###
Pruebas:


#Autentificar aplicación:
curl -X POST -H 'Content-Type: application/json' -H 'Credentials: admin' -H 'APIKey: testpassword' -H 'ApplicationName: test' http://localhost:8080/ingenia/ws/auth

#usar el token de respuesta en el siguiente comando
#Agregar Aplicación
curl -v -X POST -d '{"app":{"appname":"test","company":"CO","description":"short description"}}' -H 'Content-Type: application/json' -H 'AUTH-TOKEN: 4861bdea-a2d3-4217-b8f1-694f91933eb0' http://localhost:8080/ingenia/ws/apps/

#usar el nuevo token
#Editar Aplicación
curl -v -X PUT -d '{"app":{"appname":"test","company":"test CO","description":"short description"}}' -H 'Content-Type: application/json' -H 'AUTH-TOKEN: 17ebe87e-d610-457a-9100-2f4fb7b32d4a' http://localhost:8080/ingenia/ws/apps/test

#usar un nuevo token
#Eliminar Aplicación
curl -v -X DELETE -H 'Content-Type: application/json' -H 'AUTH-TOKEN: 1063faf0-b970-473e-be3b-31344282828e' http://localhost:8080/ingenia/ws/apps/test











