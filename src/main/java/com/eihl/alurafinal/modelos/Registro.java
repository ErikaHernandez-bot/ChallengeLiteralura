package com.eihl.alurafinal.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Registro(
        @JsonAlias("results") List<DatosLibro> registros
) {}
