package br.com.controlebezerras.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "vaca")
public class Vaca implements Serializable {
	
	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private String numero;
	private String nome;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataNascimento;
	
	private Integer qtdFilhos;
	private StatusAdulto status;
	
	private Raca raca;
	
	@OneToMany(mappedBy = "vaca")
	private List<Bezerro> bezerros;
	
	public Vaca() {
		bezerros = new ArrayList<Bezerro>();
		qtdFilhos = 0;
		status = StatusAdulto.ATIVO;
		dataNascimento = LocalDate.now();
	}
	
	public void adicionarBezerro(Bezerro bezerro) {
		bezerros.add(bezerro);
	}
	
	public Vaca(Long id, String numero, String nome, LocalDate dataNascimento, Integer qtdFilhos, StatusAdulto status,
				Raca raca, List<Bezerro> bezerros) {
		super();
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.qtdFilhos = qtdFilhos;
		this.status = status;
		this.raca = raca;
		this.bezerros = bezerros;
	}
	
	public Vaca(String nome) {
		
		this.nome = nome;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumeroNome() {
		String texto = numero + " - " + nome;
		if (numero != null && nome != null) {
			return texto;
		} else if (numero == null) {
			return nome;
		} else {
			return numero;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getQtdFilhos() {
		return qtdFilhos;
	}
	
	public void setQtdFilhos(Integer qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}
	
	public List<Bezerro> getBezerros() {
		return bezerros;
	}
	
	public void setBezerros(List<Bezerro> bezerros) {
		this.bezerros = bezerros;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public String getDataNascimentoTexto() {
		
		return dataNascimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setData(String string) {
		
	}
	
	public StatusAdulto getStatus() {
		return status;
	}
	
	public void setStatus(StatusAdulto status) {
		this.status = status;
	}
	
	public Raca getRaca() {
		return raca;
	}
	
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	
}
