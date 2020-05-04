package br.com.controlebezerras.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.extras.LabelValue;
import br.com.controlebezerras.model.Bezerro;
import br.com.controlebezerras.model.Vaca;

public interface VacaRepository extends CrudRepository<Vaca, Long> {

	@Query("select new br.com.controlebezerras.extras.LabelValue (CONCAT(v.numero, ' - ', v.nome), v.id) from vaca v where v.nome like %?1% or v.numero like %?1%")
	List<LabelValue> buscaPorNome(String nome, Pageable pageable);

	@Query("select b from bezerro b where vaca_id = ?1")
	Iterable<Bezerro> buscarFilhos(Long codigo);

	@Query("select count(id) from bezerro where vaca_id = ?1")
	Integer contaFilhos(Long codigo);

	@Query("SELECT COUNT(id) FROM vaca where status = 0")
	Integer qtdVacasAtivas();

	@Query("SELECT COUNT(id) FROM vaca where status = 1")
	Integer qtdVacasVendidas();

	@Query("SELECT COUNT(id) FROM vaca where status = 2")
	Integer qtdVacasInativas();

	@Query("SELECT COUNT(id) FROM vaca where status = 3")
	Integer qtdVacasMortas();

}
