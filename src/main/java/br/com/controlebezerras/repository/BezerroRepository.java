package br.com.controlebezerras.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Dia;

public interface BezerroRepository extends CrudRepository<Bezerro, Long> {

	@Query("SELECT COUNT(id) FROM bezerro where status = 0")
	Integer qtdStatusAmamentado();

	@Query("SELECT COUNT(id) FROM bezerro where status = 1")
	Integer qtdStatusDesmamado();

	@Query("SELECT COUNT(id) FROM bezerro where status = 2")
	Integer qtdStatusDoado();

	@Query("SELECT COUNT(id) FROM bezerro where status = 3")
	Integer qtdStatusVendido();

	@Query("SELECT COUNT(id) FROM bezerro where status = 4")
	Integer qtdStatusMorto();

	@Query("SELECT COUNT(id) FROM bezerro where sexo = 0")
	Integer qtdSexoMasculino();

	@Query("SELECT COUNT(id) FROM bezerro where sexo = 1")
	Integer qtdSexoFeminino();

	@Query("SELECT bez FROM bezerro bez where status = 0")
	List<Bezerro> listaAmamentados();

	@Query("SELECT bez FROM bezerro bez where status = 1")
	List<Bezerro> listaDesmamados();

	@Transactional
	@Modifying
	@Query("delete from dia where bezerro_id = ?1")
	void deleteDias(Long id);

	@Query("select d from dia d where bezerro_id = ?1")
	List<Dia> findDias(Long idBezerro);

	@Query("select d from dia d where bezerro_id = ?1 and numero_dia = 1")
	Dia findDiaPrimeiro(Long idBezerro);

	@Transactional
	@Modifying
	@Query("update bezerro set ultima_pesagem = ?2, data_ultima_pesagem = ?3 where id = ?1")
	void updatePeso(Long id, Double ultimaPesagem, LocalDate dataUltimaPesagem);

	@Transactional
	@Modifying
	@Query("update bezerro set ultima_medida = ?2, data_ultima_medida = ?3 where id = ?1")
	void updateAltura(Long id, Double ultimaMedida, LocalDate dataUltimaMedida);

	@Query("select b from bezerro b where id = ?1")
	Bezerro get(Long id);

	@Query("select b from bezerro b order by data_nascimento desc")
	Iterable<Bezerro> findTodos();
}
