package mx.com.personal.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import mx.com.personal.springboot.app.models.entity.Cliente;
import mx.com.personal.springboot.app.models.service.IClientService;

@Controller
@SessionAttributes("cliente")
public class ClientController {
	private static final String LISTAR = "client/listar";//Template
	private static final String FORM = "client/form";//Template
	
	//Redirect al metodo de listar
	private static final String FORM_SAVED = "redirect:list";
	
	private static final String URL_LISTAR = "/client/list";//Metodo de listar
	private static final String URL_FORM = "/client/form";//Metodo de form
	private static final String URL_ELIMINAR = "/client/eliminar/{id}";//Metodo eliminar
	
	private static final String FORM_TITLE = "Formulario de Cliente";
	
	private static final String LABEL_TITLE = "titulo";
	
	@Autowired
	private IClientService clientService;
	
	@RequestMapping(value=URL_LISTAR, method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute(LABEL_TITLE, "Listado de clientes");
		model.addAttribute("clientes", clientService.findAll());
		return LISTAR;
	}
	
	@RequestMapping(value=URL_FORM)
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put(LABEL_TITLE, FORM_TITLE);
		model.put("cliente", cliente);
		
		return FORM;
	}
	
	@RequestMapping(value=URL_FORM, method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute(LABEL_TITLE, FORM_TITLE);
			return FORM;
		}
		clientService.save(cliente);
		status.setComplete();
		
		return FORM_SAVED;
	}
	
	@RequestMapping(value=URL_FORM+"/{id}", method=RequestMethod.GET)
	public String editar(@PathVariable(value="id") Long id, Model model) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clientService.findOne(id);
		} else {
			return FORM_SAVED;
		}
		
		model.addAttribute(LABEL_TITLE, FORM_TITLE);
		model.addAttribute("cliente", cliente);
		
		return FORM;
	}
	
	@RequestMapping(value=URL_ELIMINAR)
	public String eliminar(@PathVariable(value="id") Long id, Model model) {
		if(id>0) {
			clientService.delete(id);	
		}

		return "redirect:../list";
	}
}
