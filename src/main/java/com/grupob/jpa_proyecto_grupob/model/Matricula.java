package com.grupob.jpa_proyecto_grupob.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_matricula")
public class Matricula {
	@Id
	@Column(name = "id_matricula")
	private Integer idMatricula;
	
	@Column(name = "costo")
	private Double costo;
	
	@Column(name = "fecha_matricula")
	private LocalDate fechaMatricula;
	
	@Column(name = "activo")
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;
}
