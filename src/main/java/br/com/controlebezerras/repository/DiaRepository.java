package br.com.controlebezerras.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.model.Dia;

public interface DiaRepository extends CrudRepository<Dia, Long> {

	@Query("select d from dia d where data_do_dia = ?1")
	List<Dia> listaPorData(String data);
	
	@Query("select d from dia d where data_do_dia = ?1")
	List<Dia> listaPorData(LocalDate data);

	@Transactional
	@Modifying
	@Query("update dia set peso_no_dia = ?2 where id = ?1")
	void updatePeso(Long id, Double pesoNoDia);
	
	@Transactional
	@Modifying
	@Query("update dia set altura_no_dia = ?2 where id = ?1")
	void updateAltura(Long id, Double alturaNoDia);

}
