package com.eihl.alurafinal.repositoriojpa;

import com.eihl.alurafinal.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILibro extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContains(String titulo);
    List<Libro> findByIdiomaContains(String idioma);
}