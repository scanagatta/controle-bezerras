package br.com.controlebezerras.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.repository.BezerroRepository;

@Service
public class BezerroService {

	@Autowired
	private BezerroRepository repository;

	public Iterable<Bezerro> obterTodos() {

		Iterable<Bezerro> bezerros = repository.findAll();

		return bezerros;

	}

	public void salvar(Bezerro bezerro) {
		repository.save(bezerro);
	}

	public Bezerro findId(Long id) {
		repository.findById(id);
		return new Bezerro();
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
		try {
			result = repository.count();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdStatusDesmamado() {
		Integer result = 0;
		try {
			result = repository.qtdStatusDesmamado();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdStatusAmamentado() {
		Integer result = 0;
		try {
			result = repository.qtdStatusAmamentado();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdStatusDoado() {
		Integer result = 0;
		try {
			result = repository.qtdStatusDoado();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdStatusVendido() {
		Integer result = 0;
		try {
			result = repository.qtdStatusVendido();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdStatusMorto() {
		Integer result = 0;
		try {
			result = repository.qtdStatusMorto();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdSexoMasculino() {
		Integer result = 0;
		try {
			result = repository.qtdSexoMasculino();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdSexoFeminino() {
		Integer result = 0;
		try {
			result = repository.qtdSexoFeminino();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Bezerro> listaDesmamados() {
		List<Bezerro> bezerros;
		try {
			bezerros = repository.listaDesmamados();
			return bezerros;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Bezerro> listaAmamentados() {
		List<Bezerro> bezerros;
		try {
			bezerros = repository.listaAmamentados();
			return bezerros;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Bezerro> listaPorMae(Long codigo) {

		try {

			return repository.listaPorMae(codigo);

		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	public List<Bezerro> listaPorData(LocalDate data) {

		try {

			return repository.listaPorData(data);

		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	public void updatePeso(Bezerro bezerro) {
		repository.updatePeso(bezerro.getId(), bezerro.getUltimaPesagem(), bezerro.getDataUltimaPesagem());
	}

	public void updateAltura(Bezerro bezerro) {
		repository.updateAltura(bezerro.getId(), bezerro.getUltimaMedida(), bezerro.getDataUltimaMedida());
	}

}
