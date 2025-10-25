package com.grupob.jpa_proyecto_grupob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupob.jpa_proyecto_grupob.dto.CursoFilter;
import com.grupob.jpa_proyecto_grupob.service.AulaService;
import com.grupob.jpa_proyecto_grupob.service.CursoService;
import com.grupob.jpa_proyecto_grupob.service.DocenteService;
import com.grupob.jpa_proyecto_grupob.service.HorarioService;
import com.grupob.jpa_proyecto_grupob.service.SedeService;

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
		model.addAttribute("lstCurso2", cursoService.getAll());
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("lstAula", aulaService.getAll());
		model.addAttribute("lstHorario", horarioService.getAll());
		model.addAttribute("lstDocente", docenteService.getAll());
		model.addAttribute("filter", new CursoFilter());
		return "curso/listadocurso";
	}
	
	@GetMapping("filtrocurso")
	public String listado(@ModelAttribute CursoFilter filter, Model model) {
		model.addAttribute("lstCurso", cursoService.search(filter));
		model.addAttribute("lstCurso2", cursoService.getAll());
		model.addAttribute("lstSede", sedeService.getAll());
		model.addAttribute("lstAula", aulaService.getAll());
		model.addAttribute("lstHorario", horarioService.getAll());
		model.addAttribute("lstDocente", docenteService.getAll());
		model.addAttribute("filter", filter);
		return "curso/listadocurso";
	}
	
	
	
	
	
}
