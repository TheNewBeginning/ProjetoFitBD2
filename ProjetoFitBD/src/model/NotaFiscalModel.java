package model;

import model.VendaModel;

public class NotaFiscalModel {
	private int codNf;
	private VendaModel codVendaModel;

	public NotaFiscalModel() {
		super();
	}
	public NotaFiscalModel(int codNf, VendaModel codVendaModel) {
		super();
		this.codNf = codNf;
		this.codVendaModel = codVendaModel;
	}

	public int getCodNf() {
		return codNf;
	}

	public void setCodNf(int codNf) {
		this.codNf = codNf;
	}

	public VendaModel getCodVendaModel() {
		return codVendaModel;
	}

	public void setCodVendaModel(VendaModel codVendaModel) {
		this.codVendaModel = codVendaModel;
	}

	@Override
	public String toString() {
		return "NotaFiscal [codNf=" + codNf + ", codVendaModel=" + codVendaModel + "]";
	}

}
