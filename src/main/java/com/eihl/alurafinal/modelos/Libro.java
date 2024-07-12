package com.eihl.alurafinal.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idioma;
    private Double descargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = getPrimerAutor(datosLibro).getNombre();
        this.idioma = getPrimerIdioma(datosLibro);
        this.descargas = datosLibro.descargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public Autor getPrimerAutor(DatosLibro datosLibro) {
        DatosAutor datosAutor = datosLibro.autor().get(0);
        return new Autor(datosAutor);
    }

    public String getPrimerIdioma(DatosLibro datosLibro) {
        return datosLibro.idioma().get(0);
    }

    @Override
    public String toString() {
        return "**** Información del libro ****" +
                "\n\tTitulo: " + titulo +
                "\n\tAutor: " + autor +
                "\n\tIdioma: " + idioma +
                "\n\tDescargas: " + descargas;
    }
}
