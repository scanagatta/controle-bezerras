package br.com.controlebezerras.model;

public enum Status {

	AMAMENTADO("Em amamentação"), DESMAMADO("Desmamado(a)"), DOADO("Doado(a)"), VENDIDO("Vendido(a)"),
	MORTO("Morto(a)");

	public String getStatus() {
		return getDescricao();
	}

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
