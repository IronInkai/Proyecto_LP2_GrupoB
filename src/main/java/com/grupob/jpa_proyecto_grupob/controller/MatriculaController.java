package com.grupob.jpa_proyecto_grupob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupob.jpa_proyecto_grupob.service.CursoService;
import com.grupob.jpa_proyecto_grupob.service.MatriculaService;
import com.grupob.jpa_proyecto_grupob.service.UsuarioService;
import com.grupob.jpa_proyecto_grupob.util.Alert;

import jakarta.servlet.http.HttpSession;

import com.grupob.jpa_proyecto_grupob.dto.CursoSeleccionado;
import com.grupob.jpa_proyecto_grupob.dto.ResultadoResponse;
import com.grupob.jpa_proyecto_grupob.model.Curso;
import com.grupob.jpa_proyecto_grupob.model.DetalleMatricula;
import com.grupob.jpa_proyecto_grupob.model.Matricula;
import com.grupob.jpa_proyecto_grupob.model.Usuario;

@Controller
@RequestMapping("matricula")
@SessionAttributes("lstSeleccionado")
public class MatriculaController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@ModelAttribute("lstSeleccionado")
	public List<CursoSeleccionado> inicializarSeleccionados() {
		return new ArrayList<>();
	}
	
	@GetMapping("listado")
	public String listado(Model model, HttpSession session) {
		
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    Integer idTipo = (Integer) session.getAttribute("idTipo"); 

	    if (idUsuario == null) {
	        model.addAttribute("alert", Alert.sweetAlertInfo("Sesión expirada, vuelva a iniciar sesión."));
	        return "redirect:/login";
	    }

	    if (idTipo != 2) {
	        model.addAttribute("lstMatricula", matriculaService.getAll());
	    } else {
	        List<Matricula> matriculas = matriculaService.getByUsuarioSesion(session);
	        model.addAttribute("lstMatricula", matriculas);
	    }

	    return "matricula/listado";
	}


	@GetMapping("filtrar")
	public String filtrar(Model model) {
		return "matricula/listado";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("cursoSeleccionado", new CursoSeleccionado());
		model.addAttribute("cursos", cursoService.getAll());
		return "matricula/nuevo";
	}

	@PostMapping("agregar")
	public String agregar(@ModelAttribute CursoSeleccionado seleccionado,
			@ModelAttribute("lstSeleccionado") List<CursoSeleccionado> lstSeleccionado, Model model) {
		model.addAttribute("cursos", cursoService.getAll());
		// validamos si el producto ya está seleccionado
		boolean existeProducto = lstSeleccionado.stream()
				.anyMatch(item -> item.getIdCurso() == seleccionado.getIdCurso());

		if (existeProducto) {
			model.addAttribute("alert", Alert.sweetAlertInfo("El producto ya fue seleccionado"));
			return "matricula/nuevo";
		}

		lstSeleccionado.add(seleccionado);
		model.addAttribute("cursoSeleccionado", new CursoSeleccionado());
		return "matricula/nuevo";
	}

	@PostMapping("quitar")
	public String quitar(@RequestParam Integer idCurso,
			@ModelAttribute("lstSeleccionado") List<CursoSeleccionado> lstSeleccionado, Model model) {

		lstSeleccionado.removeIf(item -> item.getIdCurso() == idCurso);

		model.addAttribute("cursos", cursoService.getAll());
		model.addAttribute("cursoSeleccionado", new CursoSeleccionado());
		return "matricula/nuevo";
	}

	@PostMapping("registrar")
	public String registrar(@ModelAttribute("lstSeleccionado") List<CursoSeleccionado> lstSeleccionado, Model model,
			RedirectAttributes flash, HttpSession session) {
		// Instanciamos la boleta
		Matricula matricula = new Matricula();

		// Obtenemos el id usuario de la sesión
		Integer idUsuario = (Integer) session.getAttribute("idUsuario");

		if (idUsuario == null) {
			flash.addFlashAttribute("alert", Alert.sweetAlertInfo("Sesión expirada"));
			return "redirect:/login";
		}

		// Obtenemos al usuario
		Usuario usuario = usuarioService.getOne(idUsuario);

		// Seteamos la usuario a la boleta
		matricula.setUsuario(usuario);

		// Validamos seleccionados
		if (lstSeleccionado.stream().count() == 0) {
			model.addAttribute("alert", Alert.sweetAlertInfo("Agregue por lo menos 1 curso"));
			model.addAttribute("cursos", cursoService.getAll());
			model.addAttribute("cursoSeleccionado", new CursoSeleccionado());
			return "matricula/nuevo";
		}

		// Convertimos los productos seleccionados a Detalles de boleta
		List<DetalleMatricula> lstDetalleMatricula = lstSeleccionado.stream().map(item -> {
			DetalleMatricula detalle = new DetalleMatricula();
			detalle.setMatricula(matricula);

			Curso curso = cursoService.getOne(item.getIdCurso());
			detalle.setCurso(curso);

			return detalle;
		}).toList();

		// Seteamos el detalle convertido a la boleta
		matricula.setLstDetalleMatricula(lstDetalleMatricula);

		// Registramos la boleta
		ResultadoResponse response = matriculaService.create(matricula);

		if (!response.success) {
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			model.addAttribute("cursos", cursoService.getAll());
			model.addAttribute("cursoSeleccionado", new CursoSeleccionado());
			return "matricula/nuevo";
		}

		flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje, "success", 5000));
		session.removeAttribute("lstSeleccionado");
		return "redirect:/matricula/listado";
	}
	
}
