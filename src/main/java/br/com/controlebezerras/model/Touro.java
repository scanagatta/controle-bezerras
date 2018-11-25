package br.com.controlebezerras.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "touro")
public class Touro {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id = null;

	private String numero;
	private String nome;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataNascimento;

	private StatusAdulto status;

	private Raca raca;

	@OneToMany(mappedBy = "touro")
	private List<Bezerro> bezerros;

	public Touro() {
		bezerros = new ArrayList<Bezerro>();
		status = StatusAdulto.ATIVO;
	}

	public Touro(Long id, String numero, String nome, LocalDate dataNascimento, StatusAdulto status, Raca raca,
			List<Bezerro> bezerros) {
		super();
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.status = status;
		this.raca = raca;
		this.bezerros = bezerros;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getNome() {
		return nome;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Bezerro> getBezerros() {
		return bezerros;
	}

	public void setBezerros(List<Bezerro> bezerros) {
		this.bezerros = bezerros;
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