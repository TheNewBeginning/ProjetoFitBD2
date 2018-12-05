package model;

public class Exercicio {
	private int cod_exercicio;
	private String descricao;
	private int cod_agenda_exer;

	public Exercicio() {

	}

	public Exercicio(String descricao, int cod_agenda_exer) {
		super();
		this.descricao = descricao;
		this.cod_agenda_exer = cod_agenda_exer;
	}

	public int getCod_exercicio() {
		return cod_exercicio;
	}

	public void setCod_exercicio(int cod_exercicio) {
		this.cod_exercicio = cod_exercicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCod_agenda_exer() {
		return cod_agenda_exer;
	}

	public void setCod_agenda_exer(int cod_agenda_exer) {
		this.cod_agenda_exer = cod_agenda_exer;
	}
	
}
