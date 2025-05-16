Descripción
Este es un microservicio construido con Java 17, utilizando Spring Boot 3 como framework, y gestionado con Maven 3.9.9. 
El microservicio está diseñado para manejar la gestión de empleados y ofrece una API RESTful documentada mediante Swagger.

Requisitos:
    -JDK 17 o superior
    -Maven 3.9.9 o superior
    -Acceso a una base de datos MySQL 8

Instalación:
1.-Ingresa a Mysql y crea el siguiente esquema
        CREATE SCHEMA `employeedb`;
2.- Descarga, descomprime e importa en projecto en tu IDE de preferencia (IntelliJ, Eclipse, etc)
3.- En el proyecto ingresa a /src/main/resource/application.properties y agrea tus credenciales para establecer la conexion con Mysql en las siguientes propiedades:
        spring.datasource.username=<COLOCAL-TU_USUARIO-MYSQL>
        spring.datasource.password=<COLOCA-TU-PASSWORD-MYSQL>

4.- Compilar el proyecto:
    mvn clean install
5.- Ejecutar el microservicio desde la terminal 
    mvn spring-boot:run
6.- O ejecutar desde la clase principal
    EmployeeApplication.java

Uso:
El microservicio se ejecutará en http://localhost:8080 por defecto.
Para probar los endpoints, puedes usar herramientas como Postman o curl.

Endpoints:
DELETE /api/employee/{id}: Eliminar empleado por ID.
curl -X 'DELETE' \
'http://localhost:8080/api/employee/2' \
-H 'accept: application/json'

GET /api/employee: Obtener todos los empleados.
curl -X 'GET' \
'http://localhost:8080/api/employee' \
-H 'accept: application/json'

POST /api/employee: Guardar uno o varios empleados.
curl -X 'POST' \
'http://localhost:8080/api/employee' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"employeers": [
{
"name": "Luis",
"middleName": "Felipe",
"paternalSurname": "Perez",
"maternalSurname": "Solis",
"age": 23,
"sex": "MALE",
"dateOfBirth": "12-10-2001",
"jobPositionId": 3
}
]
}'

PUT /api/employee: Actualizar empleado.
curl -X 'PUT' \
'http://localhost:8080/api/employee' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": 5,
"name": "Juan",
"age": 29,
"sex": "MALE",
"dateOfBirth": "22-10-1997",
"jobPositionId": 5
}'


Documentación de la API con Swagger:
La documentación de la API está disponible en el siguiente enlace cuando el microservicio está corriendo:
Swagger UI
http://localhost:8080/swagger-ui/index.html#

Estructura del Proyecto:
employee/
│
├── src/main/java/com/babel/employee/       # Código fuente
│   ├── config/                             # Clases de configuracion
│   ├── controller/                         # Servicios REST
│   ├── dto/                                # Clases de tranferencia de datos
│   └── entity/                             # Clases de acceso a datos
│   └── model/                              # Modelos de datos
│   └── repository/                         # Clases de acceso a datos
│   └── service/                            # Clases donde se encuentra la logica de negocio
│   └── util/                               # Clases de Utilidades
│
├── src/main/resources/                         # Recursos (application.properties, etc.)
│
├── pom.xml                                     # Configuración de Maven
└── README.md                                   # Este archivo


Contacto
Para más información, puedes contactar a Eulices Martinez Santamaria/eulices.santamaria@gmail.com