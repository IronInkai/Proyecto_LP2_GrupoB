package com.grupob.jpa_proyecto_grupob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_horario")
@Getter @Setter
public class Horario {
	@Id
	@Column(name = "id_horario")
	private Integer idHorario;
	
	@Column(name = "descripcion_horario")
	private String descripcionHorario;
	
	public String toString() {
		return descripcionHorario;
	}
}
