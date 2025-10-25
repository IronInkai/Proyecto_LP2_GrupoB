package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.model.Docente;
import com.grupob.jpa_proyecto_grupob.repository.DocenteRepository;

@Service
public class DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	public List<Docente> getAll(){
		return docenteRepository.findAll();
	}
}
