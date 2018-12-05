package model;

public class ProdutoModel {
	private int cod_produto;
	private String descricao;
	private int quantidade;
	
	
	public ProdutoModel() {
		super();
	}

	public ProdutoModel(int cod_produto, String descricao, int quantidade) {
		super();
		this.cod_produto = cod_produto;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public ProdutoModel(String descricao, int quantidade) {
		super();
		this.cod_produto = cod_produto;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public int getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	@Override
	public String toString() {
		return "ProdutoModel [cod_produto=" + cod_produto + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ "]";
	}
	
	
}
