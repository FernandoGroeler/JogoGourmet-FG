package br.com.jg.entities;

public class Prato {
	private String descricao;
	private String caracteristicas;
	
	public Prato(String descricao, String caracteristicas) {
		super();
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}