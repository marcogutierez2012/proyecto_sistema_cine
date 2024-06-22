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

import com.cine.model.Cliente;
import com.cine.model.TipoCliente;
import com.cine.repository.IClienteRepository;
import com.cine.repository.ITipoClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private IClienteRepository repocli;
	
	@Autowired
	private ITipoClienteRepository repotip;
	
	@GetMapping("/listarCliente")
	public String listadoCliente(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("lstClientes", repocli.findAll());
		model.addAttribute("lstTipos", repotip.findAll());
	    List<Cliente> lstClientes = null;

	    if (search != null && !search.isEmpty()) {
	    	lstClientes = repocli.findByDniClienteContainingOrNomClienteContainingOrApeClienteContaining(search, search, search);
	    } else {
	        lstClientes = repocli.findAll();
	    }

	    model.addAttribute("lstClientes", lstClientes);
		return "listaCliente";
	}
	
	@GetMapping("/cargarCliente")
	public String cargarCli(Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("lstTipos", repotip.findAll());
		return "crudCliente";
	}
	
	@GetMapping("/corregirCliente")
	public String corregirCliente(@ModelAttribute("cliente") Cliente cliente,
		    				 @ModelAttribute("lstTipos") List<TipoCliente> lstTipos,
		    				 @ModelAttribute("errorField") String errorField,
		    				 Model model) {
	
	    model.addAttribute("cliente", cliente);
	    model.addAttribute("lstTipos", lstTipos);
	    model.addAttribute("errorField", errorField);
		return "crudCliente";
	}
	
	@PostMapping("/grabarCliente")
	public String grabarCli(@ModelAttribute Cliente cliente, RedirectAttributes attribute, Model model) {
		Cliente clidni = repocli.findByDniCliente(cliente.getDniCliente());
		if(clidni == null) {
			Cliente clitel = repocli.findByTelefono(cliente.getTelefono());
			if(clitel == null) {
				Cliente clicor = repocli.findByCorreo(cliente.getCorreo());
				if(clicor == null) {
					repocli.save(cliente);
					attribute.addFlashAttribute("successful", "Cliente Registrado Exitosamente!!!");
					return "redirect:/listarCliente";
				}else {
		            attribute.addFlashAttribute("mensaje", "El Cliente con el Correo: " + cliente.getCorreo() + " ya existe");
		            attribute.addFlashAttribute("cliente", cliente);
		            attribute.addFlashAttribute("lstTipos", repotip.findAll());
		            attribute.addFlashAttribute("errorField", "correo");
				}
			}else {
	            attribute.addFlashAttribute("mensaje", "El Cliente con el Télefono: " + cliente.getTelefono() + " ya existe");
	            attribute.addFlashAttribute("cliente", cliente);
	            attribute.addFlashAttribute("lstTipos", repotip.findAll());
	            attribute.addFlashAttribute("errorField", "telefono");
			}
		}else {
            attribute.addFlashAttribute("mensaje", "El Cliente con el Dni: " + cliente.getDniCliente() + " ya existe");
            attribute.addFlashAttribute("cliente", cliente);
            attribute.addFlashAttribute("lstTipos", repotip.findAll());
            attribute.addFlashAttribute("errorField", "dniCliente");
		}
		return "redirect:/corregirCliente";

	}
		
	@PostMapping("/actualizarCliente")
	public String actualizarCli(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {
		repocli.save(cliente);
		attribute.addFlashAttribute("successful", "Cliente Actualizado Exitosamente!!!");
		return "redirect:/listarCliente";
	}
	
	@PostMapping("/eliminarCliente")
	public String eliminarCli(@ModelAttribute Cliente cliente, Model model, RedirectAttributes attribute) {
		Cliente cli = repocli.findByIdCliente(cliente.getIdCliente());
		repocli.delete(cli);
		attribute.addFlashAttribute("mensaje", "Cliente Eliminado Correctamente!");
		return "redirect:/listarCliente";
	}
}
