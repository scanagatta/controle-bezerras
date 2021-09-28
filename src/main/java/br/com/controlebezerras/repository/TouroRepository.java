package br.com.controlebezerras.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.extras.LabelValue;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Touro;

public interface TouroRepository extends CrudRepository<Touro, Long> {

	@Query("select new br.com.controlebezerras.extras.LabelValue (CONCAT(t.numero, ' - ', t.nome), t.id) from touro t where t.nome like %?1% or t.numero like %?1%")
	List<LabelValue> buscaPorNome(String nome, Pageable pageable);

	@Query("select b from bezerro b where touro_id = ?1")
	Iterable<Bezerro> buscarFilhos(Long codigo);

	@Query("select count(id) from bezerro where touro_id = ?1")
	Integer contaFilhos(Long codigo);

	@Query("SELECT COUNT(id) FROM touro where status = 0")
	Integer qtdTourosAtivos();

	@Query("SELECT COUNT(id) FROM touro where status = 1")
	Integer qtdTourosVendidos();

	@Query("SELECT COUNT(id) FROM touro where status = 2")
	Integer qtdTourosInativos();

	@Query("SELECT COUNT(id) FROM touro where status = 3")
	Integer qtdTourosMortos();

	@Query("select t from touro t order by nome")
	Iterable<Touro> findAll();
}
