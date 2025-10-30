package com.grupob.jpa_proyecto_grupob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupob.jpa_proyecto_grupob.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{

	List<Matricula> findByUsuarioIdUsuario(Integer idUsuario);
}
