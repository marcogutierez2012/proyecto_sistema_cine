package com.cine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.model.Genero;
import com.cine.model.Pelicula;
import com.cine.repository.IGeneroRepository;
import com.cine.repository.IPeliculaRepository;


@Controller
public class PeliculaController {

	@Autowired
	private IPeliculaRepository repopeli;
	
	@Autowired
	private IGeneroRepository repogen;
	
	@GetMapping("/listarPelicula")
	public String listadoPelicula(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("pelicula", new Pelicula());
		model.addAttribute("lstPeliculas", repopeli.findAll());
		model.addAttribute("lstGeneros", repogen.findAll());
		List<Pelicula> lstPeliculas = null;

	    if (search != null && !search.isEmpty()) {
	    	lstPeliculas = repopeli.findByTitPeliculaContainingOrDirPeliculaContainingOrGeneroDesGeneroContaining(search, search, search);
	    } else {
	    	lstPeliculas = repopeli.findAll();
	    }

	    model.addAttribute("lstPeliculas", lstPeliculas);
		return "listaPelicula";
	}
	
	@GetMapping("/cargarPelicula")
	public String cargarPeli(Model model) {
		model.addAttribute("pelicula", new Pelicula());
		model.addAttribute("lstGeneros", repogen.findAll());
		return "crudPelicula";
	}
	
	@GetMapping("/corregirPelicula")
	public String corregirCliente(@ModelAttribute("pelicula") Pelicula pelicula,
		    				 @ModelAttribute("lstGeneros") List<Genero> lstGeneros,
		    				 @ModelAttribute("errorField") String errorField,
		    				 Model model) {
	
	    model.addAttribute("pelicula", pelicula);
	    model.addAttribute("lstGeneros", lstGeneros);
	    model.addAttribute("errorField", errorField);
		return "crudPelicula";
	}
		
	@PostMapping("/grabarPelicula")
	public String grabarPeli(@ModelAttribute Pelicula pelicula, RedirectAttributes attribute, Model model) {
		Pelicula peltit = repopeli.findByTitPelicula(pelicula.getTitPelicula());
		if(peltit == null) {
			repopeli.save(pelicula);
			attribute.addFlashAttribute("successful", "Cliente Registrado Exitosamente!!!");
			return "redirect:/listarPelicula";
		}else {
            attribute.addFlashAttribute("mensaje", "La película con el título: " + pelicula.getTitPelicula() + " ya existe");
            attribute.addFlashAttribute("pelicula", pelicula);
            attribute.addFlashAttribute("lstGeneros", repogen.findAll());
            attribute.addFlashAttribute("errorField", "titPelicula");
		}
		return "redirect:/corregirPelicula";
	}
		
	@PostMapping("/actualizarPelicula")
	public String actualizarPeli(@ModelAttribute Pelicula pelicula, RedirectAttributes attribute) {
		repopeli.save(pelicula);
		attribute.addFlashAttribute("successful", "Pelicula Actualizado Exitosamente!!!");
		return "redirect:/listarPelicula";
	}
	
	@PostMapping("/eliminarPelicula")
	public String eliminarPeli(@ModelAttribute Pelicula pelicula, Model model) {
		Pelicula peli = repopeli.findByIdPelicula(pelicula.getIdPelicula());
		repopeli.delete(peli);
		return "redirect:/listarPelicula";
	}
}
