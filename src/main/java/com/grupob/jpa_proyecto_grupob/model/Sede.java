package com.grupob.jpa_proyecto_grupob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_sede")
@Getter @Setter
public class Sede {
	@Id
	@Column(name = "id_sede")
	private Integer idSede;
	
	@Column(name = "nombre_sede")
	private String nombreSede;
	
	public String toString() {
		return nombreSede;
	}
	
}
