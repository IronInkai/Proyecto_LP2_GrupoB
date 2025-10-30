package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.dto.DocenteFilter;
import com.grupob.jpa_proyecto_grupob.dto.ResultadoResponse;
import com.grupob.jpa_proyecto_grupob.model.Docente;
import com.grupob.jpa_proyecto_grupob.repository.DocenteRepository;

@Service
public class DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	public List<Docente> getAll(){
		return docenteRepository.findAllByOrderByIdDocenteDesc();
	}
	
	public List<Docente> searchAll(DocenteFilter docFilter) {

		if (("-1").equals(docFilter.getNombreDocente()) && ("-1").equals(docFilter.getApellidoDocente())) {
			return docenteRepository.findAllByOrderByIdDocenteDesc();
		}
		if (!("-1").equals(docFilter.getNombreDocente()) && ("-1").equals(docFilter.getApellidoDocente())) {
			return docenteRepository.findAllByNombreDocente(docFilter.getNombreDocente());
		}
		if (("-1").equals(docFilter.getNombreDocente()) && !("-1").equals(docFilter.getApellidoDocente())) {
			return docenteRepository.findAllByApellidoDocente(docFilter.getApellidoDocente());
		} 
		if (!("-1").equals(docFilter.getNombreDocente()) && !("-1").equals(docFilter.getApellidoDocente())){
			return docenteRepository.findAllWithFilters(docFilter.getNombreDocente(),docFilter.getApellidoDocente());
		}
		else {
			return docenteRepository.findAll();
		}
	}
	
	
	public ResultadoResponse create(Docente docente) {
		try {
			Docente docenteRegistrado = docenteRepository.save(docente);
			
			String mensaje = String.format("Docente registrado con Id %s", docenteRegistrado.getIdDocente());
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en registrar DocenteService: "+ e.getMessage());
		}
	}
	
	
	public Docente getOne(Integer id) {
		return docenteRepository.findById(id).orElseThrow();
	}
	
	
	public ResultadoResponse update(Docente docente) {
		try {
			Docente docenteRegistrado = docenteRepository.save(docente);
			
			String mensaje = String.format("Curso actualizado con Id %s", docenteRegistrado.getIdDocente());
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en guardar DocenteService: "+ e.getMessage());
		}
	}
	
	
	public ResultadoResponse cambiarEstado(Integer id) {

		Docente docente = this.getOne(id);
		String accion = docente.getActivo() ? "desactivado" : "activado";

		docente.setActivo(!docente.getActivo());

		try {
			Docente registrado = docenteRepository.save(docente);

			String mensaje = String.format("Producto con Id %s %s", registrado.getIdDocente(), accion);
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}
	
	
	
	
	
	
}
