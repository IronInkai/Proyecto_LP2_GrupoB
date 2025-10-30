package com.grupob.jpa_proyecto_grupob.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_matricula")
@DynamicInsert
@Getter
@Setter
public class Matricula {
	@Id
	@Column(name = "id_matricula")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatricula;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@Column(name = "costo")
	private Double costo;

	@Column(name = "fecha_matricula")
	private LocalDate fechaMatricula;

	@Column(name = "activo")
	private Boolean activo;

	@OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
	private List<DetalleMatricula> lstDetalleMatricula;
}
