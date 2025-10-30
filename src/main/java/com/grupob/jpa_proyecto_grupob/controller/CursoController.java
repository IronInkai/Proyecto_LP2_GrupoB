package com.grupob.jpa_proyecto_grupob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupob.jpa_proyecto_grupob.dto.CursoFilter;
import com.grupob.jpa_proyecto_grupob.model.Curso;
import com.grupob.jpa_proyecto_grupob.service.AulaService;
import com.grupob.jpa_proyecto_grupob.service.CursoService;
import com.grupob.jpa_proyecto_grupob.service.DocenteService;
import com.grupob.jpa_proyecto_grupob.service.HorarioService;
import com.grupob.jpa_proyecto_grupob.service.SedeService;
import com.grupob.jpa_proyecto_grupob.util.Alert;

@Controller
@RequestMapping("curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private SedeService sedeService;
	
	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private HorarioService horarioService;
	
	@Autowired
	private DocenteService docenteService;
	
	
	@GetMapping("listadocurso")
	public String listado(Model model) {
		model.addAttribute("lstCurso", cursoService.getAll());
		model.addAttribute("lstCursoaux", cursoService.getAll());
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("filter", new CursoFilter());
		return "curso/listadocurso";
	}
	
	@GetMapping("filtrocurso")
	public String listado(@ModelAttribute CursoFilter filter, Model model) {
		model.addAttribute("lstCurso", cursoService.searchAll(filter));
		model.addAttribute("lstCursoaux", cursoService.getAll());
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("filter", filter);
		return "curso/listadocurso";
	}
	
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("lstAula", aulaService.getAll());
		model.addAttribute("lstHorario", horarioService.getAll());
		model.addAttribute("lstDocente", docenteService.getAll());
		model.addAttribute("curso", new Curso());
		return "curso/nuevo";
	}
	
	@PostMapping("registrar")
	public String registrar(@ModelAttribute Curso curso, Model model, RedirectAttributes flash) {
		
		var response = cursoService.create(curso);
		
		if (!response.success) {
			model.addAttribute("lstSede", sedeService.getAll());
			model.addAttribute("lstAula", aulaService.getAll());
			model.addAttribute("lstHorario", horarioService.getAll());
			model.addAttribute("lstDocente", docenteService.getAll());
			return "curso/nuevo";
		}
	
		flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/curso/listadocurso";
	}
	
	@GetMapping("edicion/{id}")
	public String edicion(@PathVariable Integer id, Model model) {
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("lstAula", aulaService.getAll());
		model.addAttribute("lstHorario", horarioService.getAll());
		model.addAttribute("lstDocente", docenteService.getAll());
		model.addAttribute("curso", cursoService.getOne(id));
		return "curso/edicion";
	}
	
	@PostMapping("guardar")
	public String guardar(@ModelAttribute Curso curso, Model model, RedirectAttributes flash) {
		
		var response = cursoService.update(curso);
		
		if (!response.success) {
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			model.addAttribute("lstSede", sedeService.getAll());
			model.addAttribute("lstAula", aulaService.getAll());
			model.addAttribute("lstHorario", horarioService.getAll());
			model.addAttribute("lstDocente", docenteService.getAll());
			return "curso/edicion";
		}
	
		flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/curso/listadocurso";
	}
	
	
}
