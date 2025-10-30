package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.repository.MatriculaRepository;
import com.grupob.jpa_proyecto_grupob.dto.ResultadoResponse;
import com.grupob.jpa_proyecto_grupob.model.Matricula;

@Service
public class MatriculaService {

	@Autowired
	private MatriculaRepository matriculaRepository;
	
	public List<Matricula> getAll() {
		return matriculaRepository.findAll();
	}
	
	public ResultadoResponse create(Matricula matricula) {
		try {
			Matricula registrado = matriculaRepository.save(matricula);
			
			String mensaje = String.format("Matricula registrada con número %s", registrado.getIdMatricula());
			return new ResultadoResponse(true, mensaje);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResultadoResponse(false, "Error en MatriculaService: "+ e.getMessage());
		}
	}
}
