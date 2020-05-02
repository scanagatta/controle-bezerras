package br.com.controlebezerras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.controlebezerras.extras.FormatadorDataEHora;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Raca;
import br.com.controlebezerras.model.Status;
import br.com.controlebezerras.service.BezerroService;

@Controller
@RequestMapping("/bezerro")
public class BezerroController {

	@Autowired
	private BezerroService service;

	@RequestMapping("/listabezerros")
	public String listaBezerros(Model model) {

		Iterable<Bezerro> bezerros = service.obterTodos();

		model.addAttribute("bezerros", bezerros);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		return "listabezerros";
	}

	@RequestMapping("/adicionarbezerro")
	public String adicionarBezerro(Model model) {
		model.addAttribute("bezerro", new Bezerro());
		model.addAttribute("status", Status.values());
		model.addAttribute("raca", Raca.values());
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);
		return "adicionarbezerro";
	}

	@RequestMapping("/perfil/{id}")
	public ModelAndView perfil(@PathVariable("id") Bezerro bezerro) {
		ModelAndView mv = new ModelAndView("perfilbezerro");
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		mv.addObject("dataEHoraAtual", dataEHoraAtual);
		mv.addObject(bezerro);
		return mv;
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Bezerro bezerro) {
		ModelAndView mv = new ModelAndView("adicionarbezerro");
		mv.addObject("status", Status.values());
		mv.addObject("raca", Raca.values());
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		mv.addObject("dataEHoraAtual", dataEHoraAtual);
		mv.addObject(bezerro);
		return mv;
	}

	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Bezerro bezerro) {
		service.delete(bezerro.getId());
		return "redirect:/bezerro/listabezerros";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(Bezerro bezerro, BindingResult result, Model model) {

		boolean editandoBezerro = bezerro.getId() != null;

		if (editandoBezerro == false && bezerro.getDias().size() == 0) {
			bezerro.constroiDias(bezerro);
		} else {
			service.deleteDias(bezerro.getId());
			bezerro.constroiDias(bezerro);
		}

		bezerro.setarValores();
		service.salvar(bezerro);

		return "redirect:perfil/" + bezerro.getId();

		// nada
	}

}
