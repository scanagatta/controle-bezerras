package br.com.controlebezerras.extras;

public class LabelValue {

	String value;
	String data;

	public LabelValue(String name, Long codigo) {
		this.value = name;
		this.data = String.valueOf(codigo);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
