package br.com.controlebezerras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Dia;
import br.com.controlebezerras.repository.BezerroRepository;

@Service
public class BezerroService {

	@Autowired
	private BezerroRepository repository;

	public Iterable<Bezerro> obterTodos() {
		return repository.findAll();
	}

	private Bezerro preSalvar(Bezerro bezerro) {
		if (bezerro.getId() == null) {
			bezerro.constroiDias(bezerro);
		} else {
			Dia diaPrimeiro = this.getDiaPrimeiro(bezerro.getId());
			if (diaPrimeiro != null) {
				if (!diaPrimeiro.getDataDoDia().equals(bezerro.getDataNascimento())) {
					bezerro.setDias(null);
					this.deleteDias(bezerro.getId());
					bezerro.constroiDias(bezerro);
				}
			} else {
				bezerro.constroiDias(bezerro);
			}
		}

		bezerro.setarValores();
		return bezerro;

	}

	public void salvar(Bezerro bezerro) {
		repository.save(this.preSalvar(bezerro));
	}

	public void delete(Long id) {
		deleteDias(id);
		repository.deleteById(id);
	}

	public void deleteDias(Long id) {
		repository.deleteDias(id);
	}

	public Long contaBezerros() {
		long result = 0;
		result = repository.count();
		return result;
	}

	public Integer qtdStatusDesmamado() {
		Integer result = 0;
		result = repository.qtdStatusDesmamado();
		return result;
	}

	public Integer qtdStatusAmamentado() {
		Integer result = 0;
		result = repository.qtdStatusAmamentado();
		return result;
	}

	public Integer qtdStatusDoado() {
		Integer result = 0;
		result = repository.qtdStatusDoado();
		return result;
	}

	public Integer qtdStatusVendido() {
		Integer result = 0;
		result = repository.qtdStatusVendido();
		return result;
	}

	public Integer qtdStatusMorto() {
		Integer result = 0;
		result = repository.qtdStatusMorto();
		return result;
	}

	public Integer qtdSexoMasculino() {
		Integer result = 0;
		result = repository.qtdSexoMasculino();
		return result;
	}

	public Integer qtdSexoFeminino() {
		Integer result = 0;
		result = repository.qtdSexoFeminino();
		return result;
	}

	public List<Bezerro> listaDesmamados() {
		return repository.listaDesmamados();
	}

	public List<Bezerro> listaAmamentados() {
		return repository.listaAmamentados();
	}

	public List<Dia> getDias(Long idBezerro) {
		return repository.findDias(idBezerro);
	}

	public Dia getDiaPrimeiro(Long idBezerro) {
		return repository.findDiaPrimeiro(idBezerro);
	}

	public void updatePeso(Bezerro bezerro) {
		repository.updatePeso(bezerro.getId(), bezerro.getUltimaPesagem(), bezerro.getDataUltimaPesagem());
	}

	public void updateAltura(Bezerro bezerro) {
		repository.updateAltura(bezerro.getId(), bezerro.getUltimaMedida(), bezerro.getDataUltimaMedida());
	}

	public Bezerro get(Long id) {
		return repository.get(id);
	}

}
