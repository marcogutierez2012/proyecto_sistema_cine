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

import com.cine.model.Boleta;
import com.cine.model.BoletaDetalle;
import com.cine.model.DetalleBoleta;
import com.cine.model.Dulceria;
import com.cine.model.Funcion;
import com.cine.repository.IBoletaRepository;
import com.cine.repository.IClienteRepository;
import com.cine.repository.IDetalleBoletaRepository;
import com.cine.repository.IDulceriaRepository;
import com.cine.repository.IFuncionRepository;
import com.cine.repository.IUsuarioRepository;

@Controller
public class BoletaController {

	@Autowired
	private IUsuarioRepository repousu;
	
	@Autowired
	private IClienteRepository repocli;
	
	@Autowired
	private IFuncionRepository repofun;
	
	@Autowired
	private IBoletaRepository repobol;
	
	@Autowired
	private IDulceriaRepository repodul;
	
	@Autowired
	private IDetalleBoletaRepository repodet;
	
	@GetMapping("/listarBoleta")
	public String listadoBoleta(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("boleta", new Boleta());
		model.addAttribute("lstBoletas", repobol.findAll());
		model.addAttribute("lstUsuarios", repousu.findAll());
		model.addAttribute("lstClientes", repocli.findAll());
		model.addAttribute("lstFunciones", repofun.findAll());
		List<Boleta> lstBoletas = null;

	    if (search != null && !search.isEmpty()) {
	    	lstBoletas = repobol.findByUsuarioApeUsuarioContainingOrUsuarioNomUsuarioContainingOrClienteApeClienteContainingOrClienteNomClienteContaining(search, search, search, search);
	    } else {
	    	lstBoletas = repobol.findAll();
	    }

	    model.addAttribute("lstBoletas", lstBoletas);
		return "listaBoleta";
	}
	
	@GetMapping("/cargarBoleta")
	public String cargarBoleta(Model model) {
	    Boleta boleta = new Boleta();
	    
	    BoletaDetalle boletaDetalle = new BoletaDetalle();
	    boletaDetalle.setBoleta(boleta);

	    model.addAttribute("boletaDetalle", boletaDetalle);
		//BOLETA
	    model.addAttribute("lstUsuarios", repousu.findAll());
		model.addAttribute("lstClientes", repocli.findAll());
		model.addAttribute("lstFunciones", repofun.findAll());
	
	    return "generarboleta";
	}
		
	@PostMapping("/generarBoleta")
	public String generarBoleta(@ModelAttribute BoletaDetalle boletaDetalle, RedirectAttributes attribute) {
		//BOLETA
		Funcion fun = repofun.findByIdFuncion(boletaDetalle.getBoleta().getFuncion().getIdFuncion());
		fun.setNroAsientos(fun.getNroAsientos()-boletaDetalle.getBoleta().getCantAsientos());
		try {		
			if(fun.getNroAsientos()>=0) {
			repofun.save(fun);
			Boleta boleta = boletaDetalle.getBoleta();
			repobol.save(boleta);
			attribute.addFlashAttribute("successful", "Boleta Generada Exitosamente!!!");
			return "redirect:/listarBoleta";
			}
			else {
				attribute.addFlashAttribute("mensaje", "La cantidad de asientos seleccionados supera la cantidad de asientos disponibles.");
				return "redirect:/cargarBoleta";
			}
		} catch (Exception e) {
			attribute.addFlashAttribute("mensaje", "La cantidad de asientos seleccionados supera la cantidad de asientos disponibles.");
			return "redirect:/cargarBoleta";
		}
	
	}
	
	@GetMapping("/cargarDetalle")
	public String cargarDetalle(Model model) {
	    DetalleBoleta detalle = new DetalleBoleta();
	    
	    BoletaDetalle boletaDetalle = new BoletaDetalle();
	    boletaDetalle.setDetalle(detalle);

	    model.addAttribute("boletaDetalle", boletaDetalle);
		//DETALLE
		model.addAttribute("lstDulceria", repodul.findAll());
		
	    return "generarDetalle";
	}
	
	@PostMapping("/generarDetalle")
	public String generarDetalle(@ModelAttribute BoletaDetalle boletaDetalle, RedirectAttributes attribute) {
	    int idBoleta = boletaDetalle.getBoleta().getIdBoleta();
		//DETALLE
		Dulceria dul = repodul.findByIdDulceria(boletaDetalle.getDetalle().getDulceria().getIdDulceria());
		dul.setCantDulceria(dul.getCantDulceria()-boletaDetalle.getDetalle().getCant());
		//BOLETA
			try {
				repodul.save(dul);
				DetalleBoleta detalleBoleta = boletaDetalle.getDetalle();
				detalleBoleta.getBoleta().setIdBoleta(idBoleta);
				repodet.save(detalleBoleta);
				attribute.addFlashAttribute("successful", "Detalle Generado Exitosamente!!!");
				return "redirect:/listarBoleta";
			} catch (Exception e) {
				attribute.addFlashAttribute("mensaje", "La cantidad de snacks seleccionados supera la cantidad de snacks disponibles.");
				return "redirect:/cargarDetalle";
			}	
	}
	
	/*@PostMapping("/generarBoleta")
	public String generarBoleta(@ModelAttribute Boleta boleta,@ModelAttribute DetalleBoleta detalle, RedirectAttributes attribute) {
		//BOLETA
		Funcion fun = repofun.findByIdFuncion(boleta.getFuncion().getIdFuncion());
		fun.setNroAsientos(fun.getNroAsientos()-boleta.getCantAsientos());
		//DETALLE
		Dulceria dul = repodul.findByIdDulceria(detalle.getDulceria().getIdDulceria());
		dul.setCantDulceria(dul.getCantDulceria()-detalle.getCant());
		if(fun.getNroAsientos()>=0) {
			//BOLETA
			repofun.save(fun);
			repobol.save(boleta);
			//DETALLE
			
			attribute.addFlashAttribute("successful", "Boleta Generada Exitosamente!!!");
			return "redirect:/listarBoleta";
		}
		else {
			attribute.addFlashAttribute("mensaje", "La cantidad de asientos seleccionados supera la cantidad de asientos disponibles.");
			return "redirect:/cargarBoleta";
		}
		
		//Genera la boleta
	}*/
		
	
	
}
