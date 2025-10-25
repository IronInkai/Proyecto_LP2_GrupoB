package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.model.Aula;
import com.grupob.jpa_proyecto_grupob.repository.AulaRepository;

@Service
public class AulaService {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	public List<Aula> getAll(){
		return aulaRepository.findAll();
	}
}
