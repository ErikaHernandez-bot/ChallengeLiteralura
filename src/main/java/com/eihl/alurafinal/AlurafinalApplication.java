//paquete en el que se encuentra esta clase
package com.eihl.alurafinal;

//Lanzar la aplicación Spring Boot
import org.springframework.boot.SpringApplication;
//configurar la aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Ejecutar código al inicio de la aplicación
import org.springframework.boot.CommandLineRunner;
//Inyectar dependencias
import org.springframework.beans.factory.annotation.Autowired;

//Clases necesarias para que funcione esta clase
import com.eihl.alurafinal.repositoriojpa.IAutor;
import com.eihl.alurafinal.repositoriojpa.ILibro;
import com.eihl.alurafinal.principal.Principal;

@SpringBootApplication
public class AlurafinalApplication implements CommandLineRunner{
	//Inyectar las dependencias, Spring Boot crea las instancias de estas clases
	//y las asigna a las variables correspondientes
	@Autowired
	private IAutor iAutor;
	@Autowired
	private ILibro iLibro;

//main punto de entrada de la aplicacion
	public static void main(String[] args) {
		//Inicia la aplicacion Spring Boot
		SpringApplication.run(AlurafinalApplication.class, args);
	}

	@Override
	// método run de la interfaz CommandLineRunner
	public void run(String... args) throws Exception {
		//Se instancia la clase Principal pasando las dependencias
		Principal principal = new Principal(iAutor, iLibro);
		//Metodo menu de la instancia Principal que inicia la app
		principal.menuUsuario();

	}
}
