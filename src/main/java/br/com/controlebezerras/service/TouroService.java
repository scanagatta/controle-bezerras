package br.com.controlebezerras.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.controlebezerras.extras.LabelValue;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Touro;
import br.com.controlebezerras.repository.BezerroRepository;
import br.com.controlebezerras.repository.TouroRepository;

@Service
public class TouroService {

	@Autowired
	private TouroRepository repository;
	
	@Autowired
	private BezerroRepository bezerroRepository;

	public Iterable<Touro> obterTodos() {

		Iterable<Touro> touros = repository.findAll();

		return touros;

	}

	public void salvar(Touro touro) {
		repository.save(touro);
	}

	public Long contaTouros() {
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

	public Integer qtdTourosAtivos() {
		Integer result = 0;
		try {
			result = repository.qtdTourosAtivos();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdTourosVendidos() {
		Integer result = 0;
		try {
			result = repository.qtdTourosVendidos();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdTourosInativos() {
		Integer result = 0;
		try {
			result = repository.qtdTourosInativos();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer qtdTourosMortos() {
		Integer result = 0;
		try {
			result = repository.qtdTourosMortos();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

}
