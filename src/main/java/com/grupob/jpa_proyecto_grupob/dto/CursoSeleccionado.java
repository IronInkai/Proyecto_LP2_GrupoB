package com.grupob.jpa_proyecto_grupob.dto;

import java.time.LocalDate;

import com.grupob.jpa_proyecto_grupob.model.Docente;
import com.grupob.jpa_proyecto_grupob.model.Horario;

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
