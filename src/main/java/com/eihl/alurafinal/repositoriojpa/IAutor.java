package com.eihl.alurafinal.repositoriojpa;

import com.eihl.alurafinal.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAutor extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContains(String nombre);
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqual(Integer anioNacimiento, Integer anioMuerte);
}