package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.dto.CursoFilter;
import com.grupob.jpa_proyecto_grupob.dto.ResultadoResponse;
import com.grupob.jpa_proyecto_grupob.model.Curso;
import com.grupob.jpa_proyecto_grupob.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	public List<Curso> getAll(){
		return cursoRepository.findAllByOrderByIdCursoDesc();
	}
	
	public List<Curso> searchAll(CursoFilter curFilter) {

		if (("-1").equals(curFilter.getNombreCurso()) && curFilter.getIdSede() == -1) {
			return cursoRepository.findAllByOrderByIdCursoDesc();
		}
		if (!("-1").equals(curFilter.getNombreCurso()) && curFilter.getIdSede() == -1) {
			return cursoRepository.findAllByNomCurso(curFilter.getNombreCurso());
		}
		if (("-1").equals(curFilter.getNombreCurso()) && curFilter.getIdSede() != -1) {
			return cursoRepository.findAllByIdSede(curFilter.getIdSede());
		} 
		if (!("-1").equals(curFilter.getNombreCurso()) && curFilter.getIdSede() != -1){
			return cursoRepository.findAllWithFilters(curFilter.getNombreCurso(),curFilter.getIdSede());
		}
		else {
			return cursoRepository.findAll();
		}
	}
	public ResultadoResponse create(Curso curso) {
		try {
			Curso cursoRegistrado = cursoRepository.save(curso);
			
			String mensaje = String.format("Curso registrado con Id %s", cursoRegistrado.getIdCurso());
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en registrar CursoService: "+ e.getMessage());
		}
	}
	
	
	public Curso getOne(Integer id) {
		return cursoRepository.findById(id).orElseThrow();
	}
	
	
	
	public ResultadoResponse update(Curso curso) {
		try {
			Curso cursoRegistrado = cursoRepository.save(curso);
			
			String mensaje = String.format("Curso actualizado con Id %s", cursoRegistrado.getIdCurso());
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en guardar CursoService: "+ e.getMessage());
		}
	}
	
}
