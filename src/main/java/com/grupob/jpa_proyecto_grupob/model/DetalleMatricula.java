package com.grupob.jpa_proyecto_grupob.model;

import com.grupob.jpa_proyecto_grupob.dto.DetalleMatriculaId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_det_matricula")
@Getter
@Setter
@IdClass(DetalleMatriculaId.class)
public class DetalleMatricula {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "num_boleta")
	private Matricula matricula;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Curso curso;
}
