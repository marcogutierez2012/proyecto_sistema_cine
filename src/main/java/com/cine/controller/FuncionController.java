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

import com.cine.model.Funcion;
import com.cine.repository.IFuncionRepository;
import com.cine.repository.IPeliculaRepository;
import com.cine.repository.ISalaRepository;
import com.cine.repository.ITipoFuncionRepository;

@Controller
public class FuncionController {

	@Autowired
	private IFuncionRepository repofun;
	
	@Autowired
	private IPeliculaRepository repopeli;
	
	@Autowired
	private ISalaRepository reposala;
	
	@Autowired
	private ITipoFuncionRepository repotifun;
	
	@GetMapping("/listarFuncion")
	public String listadoFuncion(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("funcion", new Funcion());
		model.addAttribute("lstFunciones", repofun.findAll());
		model.addAttribute("lstPeliculas", repopeli.findAll());
		model.addAttribute("lstSalas", reposala.findAll());
		model.addAttribute("lstTiposFunciones", repotifun.findAll());
		List<Funcion> lstFunciones = null;

	    if (search != null && !search.isEmpty()) {
	    	lstFunciones = repofun.findByPeliculaTitPeliculaContainingOrSalaDesSalaContainingOrTipoFuncionDesTipoFuncionContaining(search, search, search);
	    } else {
	    	lstFunciones = repofun.findAll();
	    }

	    model.addAttribute("lstFunciones", lstFunciones);
		return "listaFuncion";
	}
	
	@GetMapping("/cargarFuncion")
	public String cargarFun(Model model) {
		model.addAttribute("funcion", new Funcion());
		model.addAttribute("lstPeliculas", repopeli.findAll());
		model.addAttribute("lstSalas", reposala.findAll());
		model.addAttribute("lstTiposFunciones", repotifun.findAll());
		return "crudFuncion";
	}
	
	@PostMapping("/grabarFuncion")
	public String grabarFun(@ModelAttribute Funcion funcion, RedirectAttributes attribute) {
		repofun.save(funcion);
		attribute.addFlashAttribute("successful", "Funcion Actualizada Exitosamente!!!");
		return "redirect:/listarFuncion";
	}
		
	@PostMapping("/actualizarFuncion")
	public String actualizarFun(@ModelAttribute Funcion funcion, RedirectAttributes attribute) {
		repofun.save(funcion);
		attribute.addFlashAttribute("successful", "Funcion Actualizada Exitosamente!!!");
		return "redirect:/listarFuncion";
	}
	
	@PostMapping("/eliminarFuncion")
	public String eliminarFuncion(@ModelAttribute Funcion funcion, Model model, RedirectAttributes attribute) {
		Funcion fun = repofun.findByIdFuncion(funcion.getIdFuncion());
		repofun.delete(fun);
		attribute.addFlashAttribute("mensaje", "Funcion Eliminada Correctamente!!!");
		return "redirect:/listarFuncion";
	}
}
