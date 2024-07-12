<h1 align="center"> Challenge Foro Hub </h1>

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
Finalmente, nos enfocamos en el uso de la interfaz HttpResponse para gestionar las respuestas recibidas de la API. La interfaz 
HttpResponse en Java ofrece una estructura que permite analizar y acceder a los diferentes elementos de una respuesta HTTP. 
Al entender cómo trabajar con esta interfaz, podrás extraer información significativa de las respuestas, como códigos de estado, 
encabezados y el cuerpo de la respuesta, que normalmente se presenta en formato JSON.

Una vez que se creó la lógica para Crear, Consultar, Actualizar y Eliminar tópicos, esa información se actualizaba en la tabla del mismo nombre con la información id, título, mensaje, fecha de creación, status (estado del tópico), autor, curso.

<h3>Para el registro de un nuevo tópico</h3>

La API cuenta con un endpoint (punto final) para el registro de tópicos, y acepta solicitudes del tipo POST para la URI /tópicos.
Los datos del tópico (título, mensaje, autor y curso) son enviados en el cuerpo de la solicitud, en formato JSON.
Se usó la anotación @RequestBody para recibir correctamente los datos del cuerpo de la solicitud.
Se usó el método save del JpaRepository para realizar la persistencia de los datos del tópico creado.
Para la validación de los datos, se utilizó la anotación Java integrada en Spring @Valid.
La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).

<h3>Para el listado de tópicos</h3>

La API debe cuenta con un punto final para el listado de todos los tópicos, y debe aceptar solicitudes del tipo GET para la URI /tópicos.
Los datos de los tópicos (título, mensaje, fecha de creación, estado, autor y curso) son devueltos en el cuerpo de la respuesta, en formato JSON.
Al tratar con el CRUD se trabaja con JpaRepository asociado al tópico, especialmente en la lista de datos de la base de datos se utiliza el método findAll.
Se implementó el listado de resultados con paginación utilizando la anotación @PageableDefault

<h3>Para detallar los tópicos</h3>

La API cuenta con un endpoint (punto final) para el listado de todos los tópicos, y acepta solicitudes del tipo GET para la URI /tópicos/{id}.
Los datos de los tópicos (título, mensaje, fecha de creación, estado, autor y curso) son  devueltos en el cuerpo de la respuesta, en formato JSON.
Se usó la anotación @‌PathVariable para recibir el campo de ID de la solicitud GET.
También se verifica si el campo ID se ingresó correctamente, de lo contrario arroja una respuesta que inidica que el id no existe.

<h3>Para actualizar los tópicos</h3>

La API cuenta con un endpoint (punto final) para la actualización de los datos de un determinado tópico, y acepta solicitudes del tipo PUT para la URI /tópicos/{id}.
Para verificar si el tópico existe en la base de datos para realizar su actualización se usó el método isPresent() de la clase Java llamada Optional.

<h3>Para eliminar un tópico</h3>

La API cuenta con un endpoint para la eliminación de un tópico específico, el cual acepta solicitudes del tipo DELETE para la URI /tópicos/{id}.
Al tratarse de la eliminación de un elemento específico de la base de datos, se usó el método deleteById del JpaRepository.

<h3>Para la configuración de seguridad</h3>

Al robustecer el programa al brindar seguridad y un token, se genero una tabla de registro de usuarios para poder autenticar y autorizar 
sus peticiones, todas las fases fueron verificadas con Insomnia para facilitar la creación, el envío y la depuración de solicitudes HTTP y API.

Para configurar la autenticación en el proyecto, fue necesario definir la clase SecurityConfigurations con información para el acceso a través de solicitudes http, utilizando anotaciones como @Configuration y @EnableWebSecurity, así como la clase spring HttpSecurity.

<h3>Para Autenticación en el código Java</h3>

El proceso de autenticación en la API se realiza con la implementación de un controller responsable de recibir las solicitudes de inicio de sesión. Se utilizaron las anotaciones @RestController y @RequestMapping para definir la URL del controller.

Además, se utilizó una clase DTO (instancia Record en Java) para recibir los datos de inicio de sesión y contraseña, y luego autenticar al usuario en el método AuthenticationManager presente en la clase SecurityConfigurations.

Se usaron las anotaciones @PostMapping, @RequestBody y @Valid para recibir y validar los datos de la solicitud.

<h2> Acceso al proyecto </h2>: 
Una vez instaladas todas las herramientas de desarrollo, se deben configurar los accesos a la base de datos, un consejo al usar un sistema MAC es verificar el nombre del usuario en la base desde la instalacion, generalmente es el nombre del usuario de sesión que está abierto y no postgres.
Al crear las tablas verifica que se inicialicen todos los valores por default debido a que una de las reglas es que ningún campo puede estar nulo, algo que se puede implementar de no ser así, es que en una interfaz se coloquen las condiciones y aplicarlas en la clase que extiende a CommandLineRunner para que inicialice la aplicación con lo ya establecido.


<h2> Tecnologías utilizadas </h2>: 
Java JDK
Maven
Spring Boot
MySQL
IntelliJ IDEA

Dependencias para agregar al crear el proyecto con Spring Initializr:

Lombok
Spring Web
Spring Boot DevTools
Spring Data JPA
Flyway Migration
MySQL Driver
Validation
Spring Security
