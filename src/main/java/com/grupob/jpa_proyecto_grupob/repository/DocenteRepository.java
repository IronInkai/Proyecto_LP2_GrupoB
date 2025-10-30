package com.grupob.jpa_proyecto_grupob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupob.jpa_proyecto_grupob.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer>{

	List<Docente> findAllByOrderByIdDocenteDesc();
	
	@Query("""
			select d from Docente d
			where
				(:nombreDocente is null or d.nombreDocente = :nombreDocente)
			order by
				d.idDocente desc
			""")
	List<Docente> findAllByNombreDocente(
			@Param("nombreDocente") String nombreDocente
	);
	
	@Query("""
			select d from Docente d
			where
				(:apellidoDocente is null or d.apellidoDocente = :apellidoDocente)
			order by
				d.idDocente desc
			""")
	List<Docente> findAllByApellidoDocente(
			@Param("apellidoDocente") String apellidoDocente
	);
	
	
	@Query("""
			select d from Docente d
			where
				(:nombreDocente is null or d.nombreDocente = :nombreDocente)
				and
				(:apellidoDocente is null or d.apellidoDocente = :apellidoDocente)
			order by
				d.idDocente desc
			""")
	List<Docente> findAllWithFilters(
			@Param("nombreDocente") String nombreDocente,
			@Param("apellidoDocente") String apellidoDocente
	);
	
	
	
	
	
	
	
	
	
	
	
}
