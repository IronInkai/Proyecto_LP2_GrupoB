package com.grupob.jpa_proyecto_grupob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupob.jpa_proyecto_grupob.dto.AutenticacionFilter;
import com.grupob.jpa_proyecto_grupob.model.Usuario;
import com.grupob.jpa_proyecto_grupob.service.AutenticacionService;
import com.grupob.jpa_proyecto_grupob.util.Alert;

import jakarta.servlet.http.HttpSession;
@Controller
public class LoginController {
	
	@Autowired
	private AutenticacionService autenticacionService;

	@GetMapping({ "/", "login" })
	public String login(Model model) {
		model.addAttribute("filter", new AutenticacionFilter());
		return "login";
	}

	@PostMapping("iniciar-sesion")
	public String iniciarSesion(@ModelAttribute AutenticacionFilter filter, Model model, RedirectAttributes flash,
			HttpSession session) {
		
		Usuario usuario = autenticacionService.autenticar(filter);
		
		if (usuario == null) {
			model.addAttribute("alert", Alert.sweetAlertError("Credenciales incorrectas"));
			model.addAttribute("filter", new AutenticacionFilter());
			return "login";
		}
		
		if (!usuario.getActivo()) {
			model.addAttribute("alert", Alert.sweetAlertInfo("Usuario inactivo"));
			model.addAttribute("filter", new AutenticacionFilter());
			return "login";
		}
		
		session.setAttribute("idUsuario", usuario.getIdUsuario());
		session.setAttribute("nombreCompleto", usuario.getNombres()+ " "+ usuario.getApellidos());
		session.setAttribute("idTipo", usuario.getTipo().getIdTipo());
		
		flash.addFlashAttribute("alert", Alert.sweetImageUrl(
				"Bienvenido a Sistema de Matrículas", 
				usuario.getNombres()+ " "+ usuario.getApellidos(), 
				"/assets/img/mapache_pedro.gif"));
		
		return "redirect:/curso/listadocurso";
	}

	@GetMapping("listadocurso")
	public String home() {
		return "curso/listadocurso";
	}
	
	@GetMapping("cerrar-sesion")
	public String cerrarSesion(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}



