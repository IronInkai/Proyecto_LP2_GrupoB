package com.grupob.jpa_proyecto_grupob.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoSeleccionado {
	private Integer idCurso;
	private String nombreCurso;
	private LocalDate fechaInicio;
	private String horario;
	private String docente;

}
