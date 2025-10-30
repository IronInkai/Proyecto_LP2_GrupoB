package com.grupob.jpa_proyecto_grupob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupob.jpa_proyecto_grupob.dto.DocenteFilter;
import com.grupob.jpa_proyecto_grupob.dto.ResultadoResponse;
import com.grupob.jpa_proyecto_grupob.model.Docente;
import com.grupob.jpa_proyecto_grupob.service.DocenteService;
import com.grupob.jpa_proyecto_grupob.util.Alert;

@Controller
@RequestMapping("docente")
public class DocenteController {


	@Autowired
	private DocenteService docenteService;
	

	@GetMapping("listadodocente")
	public String listado(Model model) {
		model.addAttribute("lstDocente", docenteService.getAll());
		model.addAttribute("lstDocenteaux", docenteService.getAll());
		model.addAttribute("filter", new DocenteFilter());
		return "docente/listadodocente";
	}
	
	
	
	@GetMapping("filtrodocente")
	public String listado(@ModelAttribute DocenteFilter filter, Model model) {
		model.addAttribute("lstDocente", docenteService.searchAll(filter));
		model.addAttribute("lstDocenteaux", docenteService.getAll());
		model.addAttribute("filter", filter);
		return "docente/listadodocente";
	}
	
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		model.addAttribute("docente", new Docente());
		return "docente/nuevo";
	}
	
	@PostMapping("registrar")
	public String registrar(@ModelAttribute Docente docente, Model model, RedirectAttributes flash) {
		
		ResultadoResponse response = docenteService.create(docente);
		
		if (!response.success) {
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "docente/nuevo";
		}
	
		flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/docente/listadodocente";
	}
	
	
	@GetMapping("edicion/{id}")
	public String edicion(@PathVariable Integer id, Model model) {
		model.addAttribute("docente", docenteService.getOne(id));
		return "docente/edicion";
	}
	

	@PostMapping("guardar")
	public String guardar(@ModelAttribute Docente docente, Model model, RedirectAttributes flash) {
		
		ResultadoResponse response = docenteService.update(docente);
		
		if (!response.success) {
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "docente/nuevo";
		}
	
		flash.addFlashAttribute("toast", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/docente/listadodocente";
	}
	
	@PostMapping("cambiar-estado")
	public String cambiarEstado(@RequestParam Integer id, RedirectAttributes flash) {

		ResultadoResponse response = docenteService.cambiarEstado(id);
		
		String toast = Alert.sweetToast(response.mensaje, "success", 5000);
		flash.addFlashAttribute("toast", toast);
		return "redirect:/docente/listadodocente";
	}
	
	
	
	
	
	
	
	
}
