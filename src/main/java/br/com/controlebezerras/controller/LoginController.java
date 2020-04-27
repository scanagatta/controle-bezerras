package br.com.controlebezerras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controlebezerras.extras.FormatadorDataEHora;
import br.com.controlebezerras.service.BezerroService;
import br.com.controlebezerras.service.TouroService;
import br.com.controlebezerras.service.VacaService;

@Controller
public class LoginController {



	@RequestMapping("/login")
	public String index(Model model) {
		return "login";
	}


}
