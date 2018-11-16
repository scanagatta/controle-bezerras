package br.com.controlebezerras.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.controlebezerras.extras.FormatadorDataEHora;
import br.com.controlebezerras.extras.LabelValue;
import br.com.controlebezerras.model.Raca;
import br.com.controlebezerras.model.StatusAdulto;
import br.com.controlebezerras.model.Touro;
import br.com.controlebezerras.service.TouroService;

@Controller
@RequestMapping("/touro")
public class TouroController {

	@Autowired
	private TouroService service;

	@RequestMapping("/listatouros")
	public String listaTouros(Model model) {

		Iterable<Touro> touros = service.obterTodos();

		for (Touro touro : touros) {
			Collections.sort(touro.getBezerros());
		}

		model.addAttribute("touros", touros);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		return "listatouros";
	}

	@RequestMapping("/adicionartouro")
	public String adicionarTouro(Model model) {
		model.addAttribute("touro", new Touro());
		model.addAttribute("status", StatusAdulto.values());
		model.addAttribute("raca", Raca.values());
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);
		return "adicionartouro";
	}

	@ResponseBody
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public Touro salvar(Touro touro, BindingResult result, Model model) {

		service.salvar(touro);

		return touro;

	}

	@RequestMapping(value = "/salvarTouro", method = RequestMethod.POST)
	public String salvarTouro(Touro touro, BindingResult result, Model model) {

		service.salvar(touro);

		return "redirect:listatouros";

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/touros", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValue> touros(@RequestParam("touros") String query) {

		return service.buscaPorNome(query, new PageRequest(0, 20));
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Touro touro) {
		ModelAndView mv = new ModelAndView("adicionartouro");
		mv.addObject("status", StatusAdulto.values());
		mv.addObject("raca", Raca.values());
		mv.addObject(touro);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		mv.addObject("dataEHoraAtual", dataEHoraAtual);
		return mv;
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public String excluir(Touro touro) {
		service.delete(touro.getId());
		return "redirect:/touro/listatouros";
	}

	// da pagina do touro
	@RequestMapping("/excluirTouro/{id}")
	public String excluirTouro(@PathVariable("id") Touro touro) {
		service.delete(touro.getId());
		return "redirect:/touro/listatouros";
	}

}
