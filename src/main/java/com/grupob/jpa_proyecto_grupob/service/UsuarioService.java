package com.grupob.jpa_proyecto_grupob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.model.Usuario;
import com.grupob.jpa_proyecto_grupob.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario getOne(Integer id) {
		return usuarioRepository.findById(id).orElseThrow();
	}
}
