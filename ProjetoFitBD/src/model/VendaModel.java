package model;

public class VendaModel {
	private int codVendas;
	private String descricao;
	private CaixaModel codCaixaVendas;

	public VendaModel() {
		super();
	}

	public VendaModel(int codVendas, String descricao, CaixaModel codCaixaVendas) {
		super();
		this.codVendas = codVendas;
		this.descricao = descricao;
		this.codCaixaVendas = codCaixaVendas;
	}

	public VendaModel(String descricao, CaixaModel codCaixaVendas) {
		super();
		this.descricao = descricao;
		this.codCaixaVendas = codCaixaVendas;
	}

	public int getCodVendas() {
		return codVendas;
	}

	public void setCodVendas(int codVendas) {
		this.codVendas = codVendas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CaixaModel getCodCaixaVendas() {
		return codCaixaVendas;
	}

	public void setCodCaixaVendas(CaixaModel codCaixaVendas) {
		this.codCaixaVendas = codCaixaVendas;
	}

	@Override
	public String toString() {
		return "VendaModel [codVendas=" + codVendas + ", descricao=" + descricao + ", codCaixaVendas=" + codCaixaVendas
				+ "]";
	}

}
