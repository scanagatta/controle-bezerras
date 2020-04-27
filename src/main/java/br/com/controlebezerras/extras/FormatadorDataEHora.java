package br.com.controlebezerras.extras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

//uso da API Joda-Time
public class FormatadorDataEHora {

	public static String dataAtual() {
		DateTime dd = new DateTime();
		String diaAtual = dd.toString("dd/MM/yyyy");
		return diaAtual;
	}

	public static String horaAtual() {
		DateTime dh = new DateTime();
		DateTimeZone timeZone = DateTimeZone.forID("America/Sao_Paulo");
		DateTime dateTimeBrasil = new DateTime(dh, timeZone);
		String horaAtual = dateTimeBrasil.toString("HH:mm:ss");
		return horaAtual;
	}

	public static String formatarData(LocalDate data) {
		String vazio = "";
		if (data != null) {
			String dataTexto = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			return dataTexto.replaceAll("-", "/");
		}
		return vazio;
	}

	public static String diferencaEntreDatas(String maiorData, String menorData, LocalDate dataNascimento) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar maior = new GregorianCalendar();
			maior.setTime(dateFormat.parse(maiorData));
			GregorianCalendar menor = new GregorianCalendar();
			menor.setTime(dateFormat.parse(menorData));
			GregorianCalendar dif = new GregorianCalendar();
			dif.setTimeInMillis(maior.getTimeInMillis() - menor.getTimeInMillis());

			int anos = diferencaEmDias(dataNascimento) / 365;
			int meses = dif.get(GregorianCalendar.MONTH);
			int dias = dif.get(GregorianCalendar.DAY_OF_MONTH);

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

	public static int diferencaEmDias(LocalDate dataNascimento) {
		// faz conversoes do local date para date
		// e depois do date para date time
		Date date = Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
		DateTime dateTime = new DateTime(date);
		// metodo que calcula a quantidade de dias
		Days dias = Days.daysBetween(dateTime, DateTime.now());
		return dias.getDays();
	}
}
