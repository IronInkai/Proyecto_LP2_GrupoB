package com.grupob.jpa_proyecto_grupob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_docente")
@Getter @Setter
public class Docente {
	@Id
	@Column(name = "id_docente")
	private Integer idDocente;
	
	@Column(name = "nombre_docente")
	private String nombreDocente;

	@Column(name = "apellido_docente")
	private String apellidoDocente;
	
	
}
