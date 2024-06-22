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

import com.cine.model.Rol;
import com.cine.model.Usuario;
import com.cine.repository.IRolRepository;
import com.cine.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioRepository repousu;
	
	@Autowired
	private IRolRepository reporol;
	
	@GetMapping("/MenuPrincipal")
	public String menuPrincipal(Model model) {
		return "MenuPrincipal";
	}
	
	@GetMapping("/")
	public String cargarLoguin(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "login";
	}
	
	@PostMapping("/validar")
	public String validarLoguin(@ModelAttribute Usuario usuario, RedirectAttributes attribute) {
		Usuario validar = repousu.findByDniUsuarioAndPassword(usuario.getDniUsuario(), usuario.getPassword());
		if(validar!=null) {
			attribute.addFlashAttribute("mensaje", "Bienvenido "+validar.getNomUsuario()+" "+validar.getApeUsuario());
			return "redirect:/MenuPrincipal";
		}else {
			attribute.addFlashAttribute("mensaje", "Error iniciando sesión.");
		}
		return "redirect:/";
	}
	
	@GetMapping("/listarUsuario")
	public String listarUsuarios(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("lstUsuarios", repousu.findAll());
		model.addAttribute("lstRoles", reporol.findAll());
	    List<Usuario> lstUsuarios = null;

	    if (search != null && !search.isEmpty()) {
	        lstUsuarios = repousu.findByDniUsuarioContainingOrNomUsuarioContainingOrApeUsuarioContaining(search, search, search);
	    } else {
	        lstUsuarios = repousu.findAll();
	    }

	    model.addAttribute("lstUsuarios", lstUsuarios);

	    return "listaUsuario";
	}
	
	@GetMapping("/cargarUsuario")
	public String cargarUsu(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("lstRoles", reporol.findAll());
		return "crudUsuario";
	}
	
	@GetMapping("/corregirUsuario")
	public String corregirUsuario(@ModelAttribute("usuario") Usuario usuario,
		    				 @ModelAttribute("lstRoles") List<Rol> lstRoles,
		    				 @ModelAttribute("errorField") String errorField,
		    				 Model model) {
	
	    model.addAttribute("usuario", usuario);
	    model.addAttribute("lstRoles", lstRoles);
	    model.addAttribute("errorField", errorField);
		return "crudUsuario";
	}
	
	@PostMapping("/grabarUsuario")
	public String grabarUsu(@ModelAttribute Usuario usuario, RedirectAttributes attribute) {
		Usuario usudni = repousu.findByDniUsuario(usuario.getDniUsuario());
		if(usudni==null) {
			Usuario usutel = repousu.findByTelefono(usuario.getTelefono());
			if(usutel==null) {
				Usuario usucorreo = repousu.findByEmail(usuario.getEmail());
				if(usucorreo==null) {
					repousu.save(usuario);
					attribute.addFlashAttribute("successful", "Usuario Registrado Exitosamente!!!");
					return "redirect:/listarUsuario";
				}else {
					attribute.addFlashAttribute("mensaje", "El Usuario con el Correo: "+usuario.getEmail()+" ya existe");
		            attribute.addFlashAttribute("usuario", usuario);
		            attribute.addFlashAttribute("lstRoles", reporol.findAll());
		            attribute.addFlashAttribute("errorField", "email");
				}
			}else{
				attribute.addFlashAttribute("mensaje", "El Usuario con el Teléfono: "+usuario.getTelefono()+" ya existe");
	            attribute.addFlashAttribute("usuario", usuario);
	            attribute.addFlashAttribute("lstRoles", reporol.findAll());
	            attribute.addFlashAttribute("errorField", "telefono");
			}
		}else {
			attribute.addFlashAttribute("mensaje", "El Usuario con el DNI: "+usuario.getDniUsuario()+" ya existe");
            attribute.addFlashAttribute("usuario", usuario);
            attribute.addFlashAttribute("lstRoles", reporol.findAll());
            attribute.addFlashAttribute("errorField", "dniUsuario");
		}
		return "redirect:/corregirUsuario";
	}
		
	@PostMapping("/actualizarUsuario")
	public String actualizarUsu(@ModelAttribute Usuario usuario, RedirectAttributes attribute) {
		repousu.save(usuario);
		attribute.addFlashAttribute("successful", "Usuario Actualizado Exitosamente!!!");
		return "redirect:/listarUsuario";
	}
	
	@PostMapping("/eliminarUsuario")
	public String eliminarUsu(@ModelAttribute Usuario usuario, Model model, RedirectAttributes attribute) {
		Usuario usu = repousu.findByIdUsuario(usuario.getIdUsuario());
		repousu.delete(usu);
		attribute.addFlashAttribute("mensaje", "Usuario Eliminado Correctamente");
		return "redirect:/listarUsuario";
	}
}
