<h1 align="center"> Challenge Literalura </h1>

<h2> Descripcion del proyecto </h2>
Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de
interacción. Los libros se buscarán a través de la API Gutendex. 

<h2> Estado del proyecto </h2>
Finalizado.

<h2> Funcionalidad </h2>: 
Construyendo el Cliente para Solicitudes (HttpClient)

Se empleó la clase HttpClient para realizar solicitudes a la API de libros y obtener datos y optimizar el proceso de la solicitud.

Construyendo la Solicitud (HttpRequest)

Se usó la clase HttpRequest para configurar y personalizar las solicitudes a la API de libros. 

Construyendo la Respuesta (HttpResponse)
Se usó la interfaz HttpResponse para gestionar las respuestas recibidas de la API. Se puede trabajar con códigos de estado, 
encabezados y el cuerpo de la respuesta, que normalmente se presenta en formato JSON.

<h3>Analizando la respuesta en formato JSON y convirtiendo datos</h3>

Con la biblioteca Jackson, se realizó el mapeo eficiente de los datos JSON a objetos Java, facilitando así la extracción y manipulación de la información con clases como ObjectMapper, para acceder a las distintas propiedades de la respuesta JSON.
Fue fundamental crear métodos específicos para manejar estos datos (getters, setters and toString())
Se usaron las anotaciones @JsonIgnoreProperties y @JsonAlias para obtener los atributos deseados del cuerpo de respuesta json.

<h2> Acceso al proyecto </h2>: 
Una vez instaladas todas las herramientas de desarrollo, se deben configurar los accesos a la base de datos, un consejo al usar un sistema MAC es verificar el nombre del usuario en la base desde la instalacion, generalmente es el nombre del usuario de sesión que está abierto y no postgres.


<h2> Tecnologías utilizadas </h2>: 
Java JDK
Maven
Spring 
Postgres
IntelliJ IDEA

Dependencias para agregar al crear el proyecto con Spring Initializr:

Spring Data JPA
Postgres Driver
