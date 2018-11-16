package br.com.controlebezerras.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.model.Dia;

public interface DiaRepository extends CrudRepository<Dia, Long> {

	@Query("select d from dia d where data_do_dia = ?1")
	List<Dia> listaPorData(String data);

	@Transactional
	@Modifying
	@Query("update dia set peso_no_dia = ?2, altura_no_dia = ?3 where id = ?1")
	void update(Long id, Double pesoNoDia, Double alturaNoDia);

}
