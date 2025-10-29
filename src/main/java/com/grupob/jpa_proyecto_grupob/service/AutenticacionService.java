package com.grupob.jpa_proyecto_grupob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupob.jpa_proyecto_grupob.dto.AutenticacionFilter;
import com.grupob.jpa_proyecto_grupob.model.Usuario;
import com.grupob.jpa_proyecto_grupob.repository.UsuarioRepository;

@Service
public class AutenticacionService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario autenticar(AutenticacionFilter filter) {
		return usuarioRepository.findByCuentaAndClave(filter.getCuenta(), filter.getClave());
	}

}


