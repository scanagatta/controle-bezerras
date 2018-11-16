package br.com.controlebezerras.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlebezerras.model.Dia;
import br.com.controlebezerras.model.Vaca;
import br.com.controlebezerras.repository.DiaRepository;

@Service
public class DiaService {

	@Autowired
	private DiaRepository repository;

	public List<Dia> listaPorData(String string) {
		try {
			return repository.listaPorData(string);
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}
	
	public void update(Dia dia) {
		repository.update(dia.getId(), dia.getPesoNoDia(), dia.getAlturaNoDia());
	}

}
