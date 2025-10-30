package com.grupob.jpa_proyecto_grupob.model;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_docente")
@DynamicInsert
@Getter @Setter
public class Docente {
	@Id
	@Column(name = "id_docente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDocente;
	
	@Column(name = "nombre_docente")
	private String nombreDocente;

	@Column(name = "apellido_docente")
	private String apellidoDocente;
	
	@Column(name = "correo_profe")
	private String correoProfe;
	
	@Column(name = "clave_profe")
	private String claveProfe;
	
	@Column(name = "celular_profe")
	private String CelularProfe;
	
	@Column(name = "fecha_ingreso")
	private String fechaIngreso;
	
	@Column(name = "estado")
	private Boolean estado;
}
