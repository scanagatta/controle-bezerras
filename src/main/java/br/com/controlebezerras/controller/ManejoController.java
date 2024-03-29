package br.com.controlebezerras.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.controlebezerras.extras.FormatadorDataEHora;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Dia;
import br.com.controlebezerras.model.Status;
import br.com.controlebezerras.service.BezerroService;
import br.com.controlebezerras.service.DiaService;

@Controller
@RequestMapping("/manejo")
public class ManejoController {

	@Autowired
	private BezerroService bezerroService;

	@Autowired
	private DiaService diaService;

	@RequestMapping("/listamanejo")
	public String listamanejo(Model model) {
		model.addAttribute("data", LocalDate.now());
		Iterable<Dia> dias = diaService.listaPorData(LocalDate.now());
		model.addAttribute("dias", dias);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		int qtdLitros = 0;
		int qtdLitrosManha = 0;
		int qtdLitrosTarde = 0;

		for (Dia dia : dias) {
			qtdLitros += dia.getLeite();
			if (dia.getLeite() > 4) {
				qtdLitrosTarde += 4;
				qtdLitrosManha += dia.getLeite() - 4;
			} else {
				qtdLitrosTarde += dia.getLeite();
			}
		}

		model.addAttribute("qtdLitros", qtdLitros);
		model.addAttribute("qtdLitrosManha", qtdLitrosManha);
		model.addAttribute("qtdLitrosTarde", qtdLitrosTarde);
		return "listamanejo";
	}

	@ResponseBody
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public Dia salvar(Dia dia, BindingResult result, Model model) {
		Bezerro bezerro = bezerroService.get(dia.getBezerro().getId());
		if (dia.getPesoNoDia() != null) {
			bezerro.setDataUltimaPesagem(dia.getDataDoDia());
			bezerro.setUltimaPesagem(dia.getPesoNoDia());
			diaService.updatePeso(dia);
		}
		if (dia.getAlturaNoDia() != null) {
			bezerro.setDataUltimaMedida(dia.getDataDoDia());
			bezerro.setUltimaMedida(dia.getAlturaNoDia());
			diaService.updateAltura(dia);
		}
		if (dia.getNumeroDia() == 60) {
			bezerro.setStatus(Status.DESMAMADO);
		}
		int numeroDia = dia.getNumeroDia() - 1;
		if (numeroDia == 0) {
			bezerro.setGanhoPesoDia(0d);
		} else {
			if (bezerro.getPesoInicial() != null) {
				bezerro.setGanhoPesoDia((bezerro.getUltimaPesagem() - bezerro.getPesoInicial()) / numeroDia);
			}
		}
		bezerroService.salvar(bezerro);
		return dia;
	}

	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String pesquisar(String data) {
		return "redirect:listamanejo/" + data;

	}

	@RequestMapping("/listamanejo/{data}")
	public ModelAndView result(@PathVariable("data") String data) {
		ModelAndView mv = new ModelAndView("listamanejo");
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		mv.addObject("dataEHoraAtual", dataEHoraAtual);
		mv.addObject("data", data);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(data, formatter);
		Iterable<Dia> dias = diaService.listaPorData(date);
		mv.addObject("dias", dias);

		int qtdLitros = 0;
		int qtdLitrosManha = 0;
		int qtdLitrosTarde = 0;

		for (Dia dia : dias) {
			qtdLitros += dia.getLeite();
			if (dia.getLeite() > 4) {
				qtdLitrosTarde += 4;
				qtdLitrosManha += dia.getLeite() - 4;
			} else {
				qtdLitrosTarde += dia.getLeite();
			}
		}

		mv.addObject("qtdLitros", qtdLitros);
		mv.addObject("qtdLitrosManha", qtdLitrosManha);
		mv.addObject("qtdLitrosTarde", qtdLitrosTarde);

		return mv;
	}
}
