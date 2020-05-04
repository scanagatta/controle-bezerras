package br.com.controlebezerras.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.controlebezerras.extras.LabelValue;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Vaca;
import br.com.controlebezerras.repository.BezerroRepository;
import br.com.controlebezerras.repository.VacaRepository;

@Service
public class VacaService {

	@Autowired
	private VacaRepository repository;

	@Autowired
	private BezerroRepository bezerroRepository;

	public Iterable<Vaca> obterTodos() {

		Iterable<Vaca> vacas = repository.findAll();

		return vacas;

	}

	public void salvar(Vaca vaca) {
		repository.save(vaca);
	}

	public Long contaVacas() {
		long result = 0;
		try {

			result = repository.count();
			return result;

		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer contaFilhos(Long id) {
		Integer result = 0;
		try {

			result = repository.contaFilhos(id);
			return result;

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<LabelValue> buscaPorNome(String nome, Pageable pageable) {

		try {

			return repository.buscaPorNome(nome, pageable);

		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}

	public void delete(Long id) {
		deletarFilhos(id);
		repository.deleteById(id);

	}

	public void deletarFilhos(Long codigo) {

		Iterable<Bezerro> bezerros = repository.buscarFilhos(codigo);

		for (Bezerro bezerro : bezerros) {
			bezerroRepository.delete(bezerro);
		}

	}

	public Integer qtdVacasAtivas() {
		Integer result = 0;
		try {
			result = repository.qtdVacasAtivas();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdVacasVendidas() {
		Integer result = 0;
		try {
			result = repository.qtdVacasVendidas();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdVacasInativas() {
		Integer result = 0;
		try {
			result = repository.qtdVacasInativas();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdVacasMortas() {
		Integer result = 0;
		try {
			result = repository.qtdVacasMortas();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}
}
