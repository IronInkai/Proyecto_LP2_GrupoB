package com.grupob.jpa_proyecto_grupob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_aula")
@Getter @Setter
public class Aula {
	@Id
	@Column(name = "id_aula")
	private Integer idAula;
	
	@Column(name = "nombre_aula")
	private String nombreAula;
	
	public String toString() {
		return nombreAula;
	}
}
