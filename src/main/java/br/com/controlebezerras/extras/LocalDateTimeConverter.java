package br.com.controlebezerras.extras;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	public Timestamp convertToDatabaseColumn(LocalDateTime locDate) {
		return locDate == null ? null : Timestamp.valueOf(locDate);
	}

	public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
		return sqlDate == null ? null : sqlDate.toLocalDateTime();
	}

}
