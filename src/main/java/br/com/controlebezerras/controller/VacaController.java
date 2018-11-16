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
import br.com.controlebezerras.model.Vaca;
import br.com.controlebezerras.service.VacaService;

@Controller
@RequestMapping("/vaca")
public class VacaController {

	@Autowired
	private VacaService service;

	@RequestMapping("/listavacas")
	public String listavacas(Model model) {

		Iterable<Vaca> vacas = service.obterTodos();

		for (Vaca vaca : vacas) {
			Collections.sort(vaca.getBezerros());
		}
		model.addAttribute("vacas", vacas);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		return "listavacas";
	}

	@RequestMapping("/adicionarvaca")
	public String adicionarVaca(Model model) {
		model.addAttribute("vaca", new Vaca());
		model.addAttribute("status", StatusAdulto.values());
		model.addAttribute("raca", Raca.values());
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);
		return "adicionarvaca";
	}

	@ResponseBody
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public Vaca salvar(Vaca vaca, BindingResult result, Model model) {
		service.salvar(vaca);

		return vaca;

	}

	@RequestMapping(value = "/salvarVaca", method = RequestMethod.POST)
	public String salvarVaca(Vaca vaca, BindingResult result, Model model) {

		service.salvar(vaca);

		return "redirect:listavacas";

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/vacas", method = RequestMethod.GET)
	@ResponseBody
	public List<LabelValue> vacas(@RequestParam("vacas") String query) {

		// pageable serve para escolher quantos elementos vai retornar a consulta do
		// banco
		return service.buscaPorNome(query, new PageRequest(0, 20));
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Vaca vaca) {
		ModelAndView mv = new ModelAndView("adicionarvaca");
		mv.addObject("status", StatusAdulto.values());
		mv.addObject("raca", Raca.values());
		mv.addObject(vaca);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		mv.addObject("dataEHoraAtual", dataEHoraAtual);
		return mv;

	}

	//do modal
	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public String excluir(Vaca vaca) {
		service.delete(vaca.getId());
		return "redirect:/vaca/listavacas";
	}

	// da pagina da vaca
	@RequestMapping("/excluirVaca/{id}")
	public String excluirVaca(@PathVariable("id") Vaca vaca) {
		service.delete(vaca.getId());
		return "redirect:/vaca/listavacas";
	}

}
