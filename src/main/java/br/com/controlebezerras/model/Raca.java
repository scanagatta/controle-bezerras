package br.com.controlebezerras.model;

public enum Raca {

	SEMRACA(""), ANGUS("Angus"), BRAHMAN("Brahman"), CARACU("Caracu"), CHAROLES("Charolês"), DEVON("Devon"), GIR("Gir"),
	GIROLANDO("Girolando"), GUZERA("Guzerá"), HEREFORD("Hereford"), HOLANDES("Holandês"), INDUBRASIL("InduBrasil"),
	JERSEY("Jersey"), LIMOUSIN("Limousin"), NELORE("Nelore"), SENEPOL("Senepol"), SIMENTAL("Simental"), SINDI("Sindi"),
	TABAPUA("Tabapuã");

	public String getRaca() {
		return getDescricao();
	}

	private String descricao;

	Raca(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}