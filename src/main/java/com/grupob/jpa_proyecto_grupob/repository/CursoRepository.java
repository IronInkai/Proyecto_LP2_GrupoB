package com.grupob.jpa_proyecto_grupob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupob.jpa_proyecto_grupob.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	List<Curso> findAllByOrderByIdCursoDesc();
	
	
	@Query("""
			select c from Curso c
			where
				(:nombreCurso is null or c.nombreCurso = :nombreCurso)
				and
				(:idSede is null or c.sede.idSede = :idSede)
			order by
				c.idCurso desc
			""")
	List<Curso> findAllWithFilters(
			@Param("nombreCurso") String nombreCurso,
			@Param("idSede") Integer idSede
	);
	
	
}
