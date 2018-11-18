package br.com.controlebezerras.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "bezerro")
public class Bezerro implements Comparable<Bezerro> {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	private String numero;
	private String nome;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataNascimento;
	private String sexo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vaca vaca;

	@ManyToOne(fetch = FetchType.EAGER)
	private Touro touro;
	private Status status;
	private Raca raca;

	private Double pesoInicial;
	private Double alturaInicial;
	private String observacao;

	@OneToMany(mappedBy = "bezerro", cascade = CascadeType.ALL)
	private List<Dia> dias;

	private LocalDate dataPrevistaDesmame;
	private Double pesoPrevistoFinal;
	private Double ganhoPesoDia;

	private LocalDate dataUltimaPesagem;
	private Double ultimaPesagem;

	private LocalDate dataUltimaMedida;
	private Double ultimaMedida;

	private Double pesoFinal;
	private Double alturaFinal;

	public Bezerro() {
		dias = new ArrayList<Dia>();
		status = Status.AMAMENTADO;
		dataNascimento = LocalDate.now();
	}

	public Bezerro(Long id, String numero, String nome, LocalDate dataNascimento, String sexo, Vaca vaca, Touro touro,
			Status status, Raca raca, Double pesoInicial, Double alturaInicial, String observacao, List<Dia> dias,
			LocalDate dataPrevistaDesmame, Double pesoPrevistoFinal, Double ganhoPesoDia, LocalDate dataUltimaPesagem,
			Double ultimaPesagem, LocalDate dataUltimaMedida, Double ultimaMedida, Double pesoFinal,
			Double alturaFinal) {
		super();
		this.id = id;
		this.numero = numero;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.vaca = vaca;
		this.touro = touro;
		this.status = status;
		this.raca = raca;
		this.pesoInicial = pesoInicial;
		this.alturaInicial = alturaInicial;
		this.observacao = observacao;
		this.dias = dias;
		this.dataPrevistaDesmame = dataPrevistaDesmame;
		this.pesoPrevistoFinal = pesoPrevistoFinal;
		this.ganhoPesoDia = ganhoPesoDia;
		this.dataUltimaPesagem = dataUltimaPesagem;
		this.ultimaPesagem = ultimaPesagem;
		this.dataUltimaMedida = dataUltimaMedida;
		this.ultimaMedida = ultimaMedida;
		this.pesoFinal = pesoFinal;
		this.alturaFinal = alturaFinal;
	}

	public void setarValores() {
		if (pesoInicial != null) {
			pesoPrevistoFinal = pesoInicial + pesoInicial;
		}
		dataPrevistaDesmame = dataNascimento.plusDays(60);
	}

	public void constroiDias(Bezerro id) {

		dias.add(new Dia(1, dataNascimento, 0.0d, 0.0d, 6, 0, 0, 0, false, false, false, id));
		dias.add(new Dia(2, dataNascimento.plusDays(1), 0.0d, 0.0d, 0, 4, 0, 0, false, false, false, id));
		dias.add(new Dia(3, dataNascimento.plusDays(2), 0.0d, 0.0d, 0, 4, 0, 0, false, false, false, id));
		int racao = 500;
		for (int i = 4; i <= 60; i++) {
			int d = i - 1;
			if (i < 10) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 100, false, false, false, id));
			} else if (i < 16) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 100, false, true, false, id));
			} else if (i < 22) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 150, false, true, false, id));
			} else if (i < 29) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 200, false, true, false, id));
			} else if (i == 29) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 300, false, true, false, id));
			} else if (i == 30) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 300, true, true, false, id));
			} else if (i > 30 && i < 35) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 300, false, true, false, id));
			} else if (i == 35) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 8, 450, false, true, false, id));
			} else if (i > 35 && i < 41) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 7, racao, false, true, false, id));
				racao = racao + 100;
			} else if (i > 40 && i < 45) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 6, racao, false, true, false, id));
				racao = racao + 100;
			} else if (i == 45) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 6, racao + 100, false, true, false,
						id));
				racao = racao + 100;
			} else if (i > 45 && i < 51) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 5, racao, false, true, false, id));
				racao = racao + 100;
			} else if (i > 50 && i < 54) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 4, racao, false, true, false, id));
				racao = racao + 100;
			} else if (i > 53 && i < 57) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 3, racao, false, true, false, id));
				racao = racao + 100;
			} else if (i == 57 || i == 58) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 2, racao - 100, false, true, false,
						id));
			} else if (i == 59) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 1, racao - 100, false, true, false,
						id));
			} else if (i == 60) {
				dias.add(new Dia(i, dataNascimento.plusDays(d), 0.0d, 0.0d, 0, 0, 1, racao - 100, true, true, false,
						id));
			}

		}
	}

	public void adicionarDia(Dia dia) {
		dias.add(dia);
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimentoTexto() {

		return dataNascimento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	public String getIdadeTexto() {

		return diferencaEntreDatas(LocalDate.now().toString(), dataNascimento.toString());

	}

	public String diferencaEntreDatas(String maiorData, String menorData) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar maior = new GregorianCalendar();
			maior.setTime(dateFormat.parse(maiorData));
			GregorianCalendar menor = new GregorianCalendar();
			menor.setTime(dateFormat.parse(menorData));
			GregorianCalendar dif = new GregorianCalendar();
			dif.setTimeInMillis(maior.getTimeInMillis() - menor.getTimeInMillis());

			int anos = (maior.get(GregorianCalendar.YEAR) - menor.get(GregorianCalendar.YEAR));
			int meses = (dif.get(GregorianCalendar.MONTH));
			int dias = dif.get(GregorianCalendar.DAY_OF_MONTH) - 1;

			if (dataNascimento.equals(LocalDate.now())) {
				return "nasceu hoje";
			}

			if (dias > 31) {
				dias = 0;
				meses = meses + 1;
			}

			String textoDia = " dia";
			if (dias > 1) {
				textoDia = " dias";
			}

			String textoMes = " mes";
			if (meses > 1) {
				textoMes = " meses";
			}

			String textoAno = " ano";
			if (anos > 1) {
				textoMes = " anos";
			}

			String resposta = anos + textoAno + meses + textoMes + " e " + dias + textoDia;

			if (anos == 0) {
				resposta = meses + textoMes + " e " + dias + textoDia;
				if (meses == 0) {
					resposta = dias + textoDia;

				}
				if (dias == 0) {
					resposta = meses + textoMes;
				}
			}

			return resposta + " de vida";
		} catch (ParseException ex) { // Lança exceção caso a data informada não esteja no formato "dd/MM/yyyy"
			ex.printStackTrace(System.err);
			String resposta = "";
			return resposta;
		}

	}

	public LocalDate getDataNascimento() {

		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Vaca getVaca() {
		return vaca;
	}

	public void setVaca(Vaca vaca) {
		this.vaca = vaca;
	}

	public Touro getTouro() {
		return touro;
	}

	public void setTouro(Touro touro) {
		this.touro = touro;
	}

	public Double getPesoInicial() {
		return pesoInicial;
	}

	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}

	public Double getAlturaInicial() {
		return alturaInicial;
	}

	public void setAlturaInicial(Double alturaInicial) {
		this.alturaInicial = alturaInicial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Dia> getDias() {
		return dias;
	}

	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}

	public LocalDate getDataPrevistaDesmame() {
		return dataPrevistaDesmame;
	}

	public String getTextoDataPrevistaDesmame() {
		String resposta = "";
		if (dataPrevistaDesmame != null) {
			resposta = dataPrevistaDesmame.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return resposta;
	}

	public String getTextoDataUltimaPesagem() {
		String resposta = "";
		if (dataUltimaPesagem != null) {
			resposta = dataUltimaPesagem.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return resposta;
	}

	public String getTextoDataUltimaMedida() {
		String resposta = "";
		if (dataUltimaMedida != null) {
			return dataUltimaMedida.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return resposta;
	}

	public int getDiasTexto() {
		// faz conversoes do local date para date
		// e depois do date para date time
		Date date = Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
		DateTime dateTime = new DateTime(date);
		// metodo que calcula a quantidade de dias
		Days dias = Days.daysBetween(dateTime, DateTime.now());
		return dias.getDays();
	}

	public void setDataPrevistaDesmame(LocalDate dataPrevistaDesmame) {
		this.dataPrevistaDesmame = dataPrevistaDesmame;
	}

	public Double getGanhoPesoDia() {
		return ganhoPesoDia;
	}

	public void setGanhoPesoDia(Double ganhoPesoDia) {
		this.ganhoPesoDia = ganhoPesoDia;
	}

	public Double getPesoFinal() {
		return pesoFinal;
	}

	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

	public Double getAlturaFinal() {
		return alturaFinal;
	}

	public void setAlturaFinal(Double alturaFinal) {
		this.alturaFinal = alturaFinal;
	}

	public Double getPesoPrevistoFinal() {
		return pesoPrevistoFinal;
	}

	public void setPesoPrevistoFinal(Double pesoPrevistoFinal) {
		this.pesoPrevistoFinal = pesoPrevistoFinal;
	}

	@Override
	public int compareTo(Bezerro arg0) {
		return arg0.getId().compareTo(this.getId());
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNumeroNome() {
		String texto = numero + " " + nome;
		if (numero != null && nome != null) {
			return texto;
		} else if (numero == null) {
			return nome;
		} else {
			return numero;
		}
	}

	public LocalDate getDataUltimaPesagem() {
		return dataUltimaPesagem;
	}

	public void setDataUltimaPesagem(LocalDate dataUltimaPesagem) {
		this.dataUltimaPesagem = dataUltimaPesagem;
	}

	public Double getUltimaPesagem() {
		return ultimaPesagem;
	}

	public void setUltimaPesagem(Double ultimaPesagem) {
		this.ultimaPesagem = ultimaPesagem;
	}

	public LocalDate getDataUltimaMedida() {
		return dataUltimaMedida;
	}

	public void setDataUltimaMedida(LocalDate dataUltimaMedida) {
		this.dataUltimaMedida = dataUltimaMedida;
	}

	public Double getUltimaMedida() {
		return ultimaMedida;
	}

	public void setUltimaMedida(Double ultimaMedida) {
		this.ultimaMedida = ultimaMedida;
	}

}