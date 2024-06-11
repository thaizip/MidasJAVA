package model;

public class Recompensa {
	private int codigo ;
	private String titulo;
	private String descricao;
	private String ponto;
	private String dtvencimento;
	public int getCodigo() {

		return codigo;
	}
	public void setCodigo(int codigo) {

		this.codigo = codigo;
	}
	public String getTitulo() {

		return titulo;
	}
	public void setTitulo(String titulo) {

		this.titulo = titulo;
	}
	public String getDescricao() {

		return descricao;
	}
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}
	public String getPonto() {

		return ponto;
	}
	public void setPonto(String ponto) {

		this.ponto = ponto;
	}
	public String getDtvencimento() {

		return dtvencimento;
	}
	public void setDtvencimento(String dtvencimento) {
		this.dtvencimento = dtvencimento;
	}

}
