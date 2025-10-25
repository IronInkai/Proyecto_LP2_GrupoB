package com.grupob.jpa_proyecto_grupob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.model.Sede;
import com.grupob.jpa_proyecto_grupob.repository.SedeRepository;

@Service
public class SedeService {
	
	@Autowired
	private SedeRepository sedeRepository;
	
	public List<Sede> getAll(){
		return sedeRepository.findAll();
	}
}
