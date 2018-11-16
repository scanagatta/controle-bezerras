package br.com.controlebezerras.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "dia")
public class Dia {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private int numeroDia;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDoDia;
	private Double pesoNoDia;
	private Double alturaNoDia;

	@ManyToOne
	private Bezerro bezerro;

	private int colostro;
	private int suplementoColostro;
	private int leite;
	private int racao;
	private boolean feno;

	private boolean vermifugacao;

	private boolean descorna;

	public Dia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dia(int numeroDia, LocalDate dataDoDia, Double pesoNoDia, Double alturaNoDia, int colostro,
			int suplementoColostro, int leite, int racao, boolean vermifugacao, boolean feno, boolean descorna,
			Bezerro bezerro) {
		super();
		this.dataDoDia = dataDoDia;
		this.numeroDia = numeroDia;
		this.pesoNoDia = pesoNoDia;
		this.alturaNoDia = alturaNoDia;
		this.colostro = colostro;
		this.suplementoColostro = suplementoColostro;
		this.leite = leite;
		this.racao = racao;
		this.vermifugacao = vermifugacao;
		this.feno = feno;
		this.descorna = descorna;
		this.bezerro = bezerro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroDia() {
		return numeroDia;
	}

	public void setNumeroDia(int numeroDia) {
		this.numeroDia = numeroDia;
	}

	public Double getPesoNoDia() {
		return pesoNoDia;
	}

	public void setPesoNoDia(Double pesoNoDia) {
		this.pesoNoDia = pesoNoDia;
	}

	public Double getAlturaNoDia() {
		return alturaNoDia;
	}

	public void setAlturaNoDia(Double alturaNoDia) {
		this.alturaNoDia = alturaNoDia;
	}

	public int getColostro() {
		return colostro;
	}

	public void setColostro(int colostro) {
		this.colostro = colostro;
	}

	public int getSuplementoColostro() {
		return suplementoColostro;
	}

	public void setSuplementoColostro(int suplementoColostro) {
		this.suplementoColostro = suplementoColostro;
	}

	public int getLeite() {
		return leite;
	}

	public void setLeite(int leite) {
		this.leite = leite;
	}

	public int getRacao() {
		return racao;
	}

	public void setRacao(int racao) {
		this.racao = racao;
	}

	public boolean isVermifugacao() {
		return vermifugacao;
	}

	public void setVermifugacao(boolean vermifugacao) {
		this.vermifugacao = vermifugacao;
	}

	public boolean isFeno() {
		return feno;
	}

	public void setFeno(boolean feno) {
		this.feno = feno;
	}

	public boolean isDescorna() {
		return descorna;
	}

	public void setDescorna(boolean descorna) {
		this.descorna = descorna;
	}

	public LocalDate getDataDoDia() {
		return dataDoDia;
	}

	public void setDataDoDia(LocalDate dataDoDia) {
		this.dataDoDia = dataDoDia;
	}

	public Bezerro getBezerro() {
		return bezerro;
	}

	public void setBezerro(Bezerro bezerro) {
		this.bezerro = bezerro;
	}

	

}
