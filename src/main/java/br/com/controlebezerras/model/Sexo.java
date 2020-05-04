package br.com.controlebezerras.model;

public enum Sexo {

	MACHO("Macho"), FEMEA("Fêmea");

	public String getSexo() {
		return getDescricao();
	}

	private String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
