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
public class IndexController {

	@Autowired
	private BezerroService bezerroService;

	@Autowired
	private VacaService vacaService;

	@Autowired
	private TouroService touroService;

	@RequestMapping("/index")
	public String index(Model model) {
		preencheDados(model);
		return "/index";
	}

	@RequestMapping("/")
	public String branca(Model model) {
		preencheDados(model);
		return "index";
	}
	
	@RequestMapping("/offline")
	public String offline(Model model) {
		preencheDados(model);
		return "/offline";
	}

	public String preencheDados(Model model) {
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		model.addAttribute("qtdBezerros", bezerroService.contaBezerros());
		model.addAttribute("qtdMachos", bezerroService.qtdSexoMasculino());
		model.addAttribute("qtdFemeas", bezerroService.qtdSexoFeminino());
		model.addAttribute("qtdDesmamados", bezerroService.qtdStatusDesmamado());
		model.addAttribute("qtdAmamentados", bezerroService.qtdStatusAmamentado());
		model.addAttribute("qtdDoados", bezerroService.qtdStatusDoado());
		model.addAttribute("qtdVendidos", bezerroService.qtdStatusVendido());
		model.addAttribute("qtdMortos", bezerroService.qtdStatusMorto());
		model.addAttribute("listaDesmamados", bezerroService.listaDesmamados());
		model.addAttribute("listaAmamentados", bezerroService.listaAmamentados());

		model.addAttribute("qtdVacas", vacaService.contaVacas());
		model.addAttribute("qtdVacasAtivas", vacaService.qtdVacasAtivas());
		model.addAttribute("qtdVacasVendidas", vacaService.qtdVacasVendidas());
		model.addAttribute("qtdVacasInativas", vacaService.qtdVacasInativas());
		model.addAttribute("qtdVacasMortas", vacaService.qtdVacasMortas());

		model.addAttribute("qtdTouros", touroService.contaTouros());
		model.addAttribute("qtdTourosAtivos", touroService.qtdTourosAtivos());
		model.addAttribute("qtdTourosVendidos", touroService.qtdTourosVendidos());
		model.addAttribute("qtdTourosInativos", touroService.qtdTourosInativos());
		model.addAttribute("qtdTourosMortos", touroService.qtdTourosMortos());

		return "index";
	}

}
