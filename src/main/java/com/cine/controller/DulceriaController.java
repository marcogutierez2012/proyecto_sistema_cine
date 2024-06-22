package com.cine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.model.Dulceria;
import com.cine.repository.IDulceriaRepository;
import com.cine.repository.ITipoDulceriaRepository;

@Controller
public class DulceriaController {

	@Autowired
	private IDulceriaRepository repodul;
	
	@Autowired
	private ITipoDulceriaRepository repotidul;
	
	@GetMapping("/listarDulceria")
	public String listadoDulceria(Model model) {
		model.addAttribute("dulceria", new Dulceria());
		model.addAttribute("lstDulceria", repodul.findAll());
		model.addAttribute("lstTiposDulceria", repotidul.findAll());
		return "listaDulceria";
	}
	
	@GetMapping("/cargarDulceria")
	public String cargarDul(Model model) {
		model.addAttribute("dulceria", new Dulceria());
		model.addAttribute("lstTiposDulceria", repotidul.findAll());
		return "crudDulceria";
	}
	
	@PostMapping("/grabarDulceria")
	public String grabarDul(@ModelAttribute Dulceria dulceria, RedirectAttributes attribute) {
		repodul.save(dulceria);
		attribute.addFlashAttribute("successful", "Snack Ingresado Correctamente!!!");
		return "redirect:/listarDulceria";
	}
		
	@PostMapping("/actualizarDulceria")
	public String actualizarDul(@ModelAttribute Dulceria dulceria, RedirectAttributes attribute) {
		repodul.save(dulceria);
		attribute.addFlashAttribute("successful", "Snack Actualizado Exitosamente!!!");
		return "redirect:/listarDulceria";
	}
	
	@PostMapping("/eliminarDulceria")
	public String eliminarDul(@ModelAttribute Dulceria dulceria, Model model, RedirectAttributes attribute) {
		Dulceria dul = repodul.findByIdDulceria(dulceria.getIdDulceria());
		repodul.delete(dul);
		attribute.addFlashAttribute("mensaje", "Snack Eliminado Correctamente!!!");
		return "redirect:/listarDulceria";
	}
}
