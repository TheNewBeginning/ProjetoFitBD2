package model;

public class ProdutoVendas {
	private ProdutoModel id;
	private VendaModel idVenda;

	public ProdutoVendas() {
		super();
	}

	public ProdutoVendas(ProdutoModel id, VendaModel idVenda) {
		super();
		this.id = id;
		this.idVenda = idVenda;
	}

	public ProdutoModel getId() {
		return id;
	}

	public void setId(ProdutoModel id) {
		this.id = id;
	}

	public VendaModel getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(VendaModel idVenda) {
		this.idVenda = idVenda;
	}

}
