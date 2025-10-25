package com.grupob.jpa_proyecto_grupob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_tipo")
@Getter @Setter
public class Tipo {
	@Id
	@Column(name = "id_tipo")
	private Integer idTipo;
	
	@Column(name = "descripcion")
	private String descripcion;
}
