package com.eihl.alurafinal.principal;

//Clases y librerias necesarias

import com.eihl.alurafinal.modelos.Autor;
import com.eihl.alurafinal.modelos.DatosLibro;
import com.eihl.alurafinal.modelos.Libro;

import com.eihl.alurafinal.modelos.Registro;
import com.eihl.alurafinal.repositoriojpa.IAutor;
import com.eihl.alurafinal.repositoriojpa.ILibro;

import com.eihl.alurafinal.consumoapi.ConsumirAPI;
import com.eihl.alurafinal.consumoapi.ConvertirDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    //Para leer la entrada del usuario desde consola
    private Scanner scanner = new Scanner(System.in);

    //Declara e inicializa instancias
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();

    //Declara variables
    private IAutor iAutor;
    private ILibro iLibro;

    //Declara listas para almacenar libros y autores
    List<Libro> libros;
    List<Autor> autores;

    //Constructor de Principal, que inicializa las variables con los
    // valores pasados como parametros

    public Principal(IAutor iAutor, ILibro iLibro) {
        this.iAutor = iAutor;
        this.iLibro = iLibro;
    }
    //Metodo menu de cadena multilinea

    public void menuUsuario() {
        final var menu = """
                 \n\t**** Bienvenid@, ingresa una opcion  ****
                \t1 - Buscar libro por titulo
                \t2 - Listar libros registrados
                \t3 - Listar autores registrados
                \t4 - Listar autores vivos en un determinado año
                \t5 - Listar libros por idioma
                \n\t0 - Salir
                """;
        //Bucle para mostrar el menu y procesar la opcion seleccionada con un switch
        var opcion = 8;
        System.out.println("****************************************");
        while (opcion != 0) {
            System.out.println(menu);
            System.out.print("Su opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    libroPorTitulo();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivosPorAnio();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Proceso terminado");
                    break;
                default:
                    System.out.println("Intente de nuevo por favor");
                    break;
            }
        }
    }

    /*Usuario ingresa titulo a buscar, obtiene los datos de la API,
    los convierte a objetos (Registros), y si los encuentra, guarda los datos.
     */
    private void libroPorTitulo() {
        System.out.print("Ingresa el titulo del libro a buscar: ");


        String tituloBuscado = scanner.nextLine();

        //El método puede devolver cualquier tipo de objeto
        //los espacios en blanco no son permitidos en las URLs y se deben codificar como %20
        var json = consumirAPI.getDatos(tituloBuscado.replace(" ", "%20"));
        //System.out.println("json: " + json);

        //con tituloBuscado se trae el JSON completo y se deserializa
        //los parametros: la cadena que representa un JSON y el tipo de objeto en que debe convertirse el json
        var data = convertirDatos.getDatos(json, Registro.class);

        //System.out.println("data: " + data);
        if (data.registros().isEmpty()) {
            System.out.println("No se ha encontrado ningún libro con ese título");
        } else {
            DatosLibro datosLibro = data.registros().get(0);
            //System.out.println("bookData: " + bookData);
            Libro libro = new Libro(datosLibro);
            //System.out.println("book: " + book);
            Autor autor = new Autor().getPrimerAutor(datosLibro);
            //System.out.println("author: " + author);
            datosGuardados(libro, autor);
        }
    }

    //Verifica si el libro o autor están registrados en los repositorios y los guarda si no están.

    private void datosGuardados(Libro libro, Autor autor) {
        Optional<Libro> libroEncontrado = iLibro.findByTituloContains(libro.getTitulo());
        //System.out.println("bookFound: " + bookFound);
        if (libroEncontrado.isPresent()) {
            System.out.println("Este libro ya ha sido registrado");
        } else {
            try {
                iLibro.save(libro);
                System.out.println("Libro registrado con éxito");
            } catch (Exception e) {
                System.out.println("Error message: " + e.getMessage());
            }
        }

        Optional<Autor> autorEncontrado = iAutor.findByNombreContains(autor.getNombre());
        //System.out.println("authorFound: " + authorFound);
        if (autorEncontrado.isPresent()) {
            System.out.println("Este autor ya ha sido registrado");
        } else {
            try {
                iAutor.save(autor);
                System.out.println("Autor registrado con éxito");
            } catch (Exception e) {
                System.out.println("Error message: " + e.getMessage());
            }
        }
    }

    //Recupera los libros del repositorio, los ordena por título y los imprime.
    private void librosRegistrados() {
        System.out.println("Lista de libros registrados\n---------------------");
        libros = iLibro.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    //Recupera los autores del repositorio, los ordena por nombre y los imprime.

    private void autoresRegistrados() {
        System.out.println("Lista de autores registrados\n-----------------------");
        autores = iAutor.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }
    //Usuario ingresa un año y recupera los autores que estaban vivos en ese año, los ordena por nombre y los imprime.
    private void autoresVivosPorAnio() {
        System.out.print("Por favor ingresa un año: ");
        Integer anio = Integer.valueOf(scanner.nextLine());
        autores = iAutor
                .findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(anio, anio);
        if (autores.isEmpty()) {
            System.out.println("No han sido encontrados autores con vida para el año ingresado");
        } else {
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(System.out::println);
        }
    }
    //Usuario ingresa un idioma, recupera los libros en ese idioma, los ordena por título y los imprime.
    private void librosPorIdioma() {
        System.out.println("Lista de libros por idioma\n----------------------");
        System.out.println("""
                \n\t---- Selecciona un idioma por favor ----
                \ten - Inglés
                \tes - Español
                \tfr - Francés
                \tpt - Portugués 
                """);
        String idioma = scanner.nextLine();
        libros = iLibro.findByIdiomaContains(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se han encontrado libros por el idioma seleccionado");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
    }


}
