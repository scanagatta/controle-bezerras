package br.com.controlebezerras.extras;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//uso da API Joda-Time
public class FormatadorDataEHora {

	public static String formatarData(LocalDate data) {
		String vazio = "";
		if (data != null) {
			String dataTexto = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return dataTexto;
		}
		return vazio;
	}

}
