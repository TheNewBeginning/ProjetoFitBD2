package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CaixaModel {
	private int cod_caixa;
	private String data_entrada;
	private String data_saida;
	private double valores_entrada;
	private double valores_saida;
	
	
	
	public CaixaModel(int cod_caixa) {
		super();
		this.cod_caixa = cod_caixa;
	}

	public CaixaModel(int cod_caixa, String data_entrada, String data_saida, double valores_entrada,
			double valores_saida) {
		super();
		this.cod_caixa = cod_caixa;
		this.data_entrada = data_entrada;
		this.data_saida = data_saida;
		this.valores_entrada = valores_entrada;
		this.valores_saida = valores_saida;
	}

	public CaixaModel(String data_entrada, String data_saida, double valores_entrada,
			double valores_saida) {
		super();
		this.data_entrada = data_entrada;
		this.data_saida = data_saida;
		this.valores_entrada = valores_entrada;
		this.valores_saida = valores_saida;
	}

	public CaixaModel() {
		// TODO Auto-generated constructor stub
	}


	public int getCod_caixa() {
		return cod_caixa;
	}


	public void setCod_caixa(int cod_caixa) {
		this.cod_caixa = cod_caixa;
	}

	public String getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(String data_entrada) {
		this.data_entrada = data_entrada;
	}

	public String getData_saida() {
		return data_saida;
	}

	public void setData_saida(String data_saida) {
		this.data_saida = data_saida;
	}

	public double getValores_entrada() {
		return valores_entrada;
	}


	public void setValores_entrada(double valores_entrada) {
		this.valores_entrada = valores_entrada;
	}


	public double getValores_saida() {
		return valores_saida;
	}


	public void setValores_saida(double valores_saida) {
		this.valores_saida = valores_saida;
	}

	@Override
	public String toString() {
		return "CaixaModel [cod_caixa=" + cod_caixa + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida
				+ ", valores_entrada=" + valores_entrada + ", valores_saida=" + valores_saida + "]";
	}

}
