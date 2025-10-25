package com.grupob.jpa_proyecto_grupob.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_curso")
public class Curso {
	@Id
	@Column(name = "id_curso")
	private Integer idCurso;
	
	@Column(name = "nombre_curso")
	private String nombreCurso;
	
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	
	@ManyToOne
	@JoinColumn(name = "id_sede")
	private Sede sede;
	
	@ManyToOne
	@JoinColumn(name = "id_aula")
	private Aula aula;
	
	@ManyToOne
	@JoinColumn(name = "id_horario")
	private Horario horario;
	
	@ManyToOne
	@JoinColumn(name = "id_docente")
	private Docente docente;
}
