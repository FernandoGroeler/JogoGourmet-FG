package br.com.jg.entities;

public class PratoBuilder {
	private String descricao;
	private String caracteristicas;
	
	public PratoBuilder descricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public PratoBuilder caracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
		return this;
	}
	
	public Prato build() {
		return new Prato(this.descricao, this.caracteristicas);
	}
}