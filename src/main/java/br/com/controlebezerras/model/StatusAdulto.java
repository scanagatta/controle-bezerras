package br.com.controlebezerras.model;

public enum StatusAdulto {
	ATIVO("Ativo(a)"), VENDIDO("Vendido(a)"), INATIVO("Inativo(a)"), MORTO("Morto(a)");

	public String getStatus() {
		return getDescricao();
	}

	private String descricao;

	StatusAdulto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
