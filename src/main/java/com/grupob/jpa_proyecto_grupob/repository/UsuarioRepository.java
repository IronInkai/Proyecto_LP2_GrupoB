package com.grupob.jpa_proyecto_grupob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupob.jpa_proyecto_grupob.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByCuentaAndClave(String cuenta, String clave);
}
