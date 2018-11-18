package br.com.controlebezerras.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.controlebezerras.extras.FormatadorDataEHora;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Dia;
import br.com.controlebezerras.service.BezerroService;
import br.com.controlebezerras.service.DiaService;

@Controller
@RequestMapping("/manejo")
public class ManejoController {

//	@Autowired
//	private BezerroService bezerroService;

	@Autowired
	private DiaService diaService;

	@Autowired
	private BezerroService bezerroService;

	@RequestMapping("/listamanejo")
	public String listamanejo(Model model) {
		model.addAttribute("data", LocalDate.now());
		Iterable<Dia> dias = diaService.listaPorData(LocalDate.now());

		model.addAttribute("dias", dias);
		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		return "listamanejo";
	}

	@ResponseBody
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public Dia salvar(Dia dia, BindingResult result, Model model) {
		diaService.update(dia);

//		Bezerro bezerro = dia.getBezerro();
//		bezerro = bezerroService.findId(bezerro.getId());
//
//		if (dia.getPesoNoDia() != null) {
//			bezerro.setDataUltimaPesagem(dia.getDataDoDia());
//			bezerro.setUltimaPesagem(dia.getPesoNoDia());
//			bezerroService.updatePeso(bezerro);
//		}
//
//		if (dia.getAlturaNoDia() != null) {
//			bezerro.setDataUltimaMedida(dia.getDataDoDia());
//			bezerro.setUltimaMedida(dia.getAlturaNoDia());
//			bezerroService.updateAltura(bezerro);
//		}

		return dia;

	}

//	@RequestMapping("/pesquisar/data={data_do_dia}")
//	public String pesquisar(@PathVariable("data_do_dia") LocalDate data, Model model) {
//
//		model.addAttribute("data", data);
//
//		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
//		model.addAttribute("dataEHoraAtual", dataEHoraAtual);
//
//		
//		Iterable<Dia> dias = diaService.listaPorData(data.toString());
//
//		model.addAttribute("dias", dias);
//
//		return "listamenejo/"+data;
//	}

//	@ResponseBody
//	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
//	public String pesquisar(LocalDate data, BindingResult result, Model model) {
//		model.addAttribute("data", data);
//		Iterable<Bezerro> bezerros = bezerroService.listaPorData(data);
//		
//		model.addAttribute("bezerros", bezerros);
//		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
//		model.addAttribute("dataEHoraAtual", dataEHoraAtual);
//		return "listamanejo";
//
//	}

	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public String pesquisar(LocalDate data, BindingResult result, Model model) {

		model.addAttribute("data", data.toString());

		String dataEHoraAtual = FormatadorDataEHora.dataAtual() + " ás " + FormatadorDataEHora.horaAtual();
		model.addAttribute("dataEHoraAtual", dataEHoraAtual);

		Iterable<Dia> dias = diaService.listaPorData(data.toString());

		model.addAttribute("dias", dias);

		return "listamenejo";

	}

}
