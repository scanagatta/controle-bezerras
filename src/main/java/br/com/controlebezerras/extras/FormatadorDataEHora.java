package br.com.controlebezerras.extras;

import java.time.LocalDate;
import java.time.LocalTime;

import org.joda.time.DateTime;

//uso da API Joda-Time
public class FormatadorDataEHora {

	public static String dataAtual() {
		DateTime dd = new DateTime();
		String diaAtual = dd.toString("dd/MM/yyyy");
		return diaAtual;
	}

	public static String horaAtual() {
		DateTime dh = new DateTime();
		String horaAtual = dh.toString("HH:mm:ss");
		return horaAtual;
	}

	public static String data(LocalDate data) {
		DateTime dd = new DateTime();
		String diaAtual = dd.toString("dd/MM/yyyy");
		return diaAtual;
	}

	public static String hora(LocalTime hora) {
		DateTime dh = new DateTime();
		String horaAtual = dh.toString("HH:mm:ss");
		return horaAtual;
	}

}
