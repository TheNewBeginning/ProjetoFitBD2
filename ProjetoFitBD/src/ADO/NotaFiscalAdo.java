package ADO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.NotaFiscalModel;
import model.VendaModel;
import objectTransferData.ObjectData;

public class NotaFiscalAdo {
	private ConexaoAdo minhaConexao;
	private String mensagem;
	
	public NotaFiscalAdo() {
		try {
			minhaConexao = new ConexaoAdo();
		} catch (Exception e) {
			Logger.getLogger(NotaFiscalAdo.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public boolean salvar(NotaFiscalModel nfModel) throws RuntimeException {
		boolean check = false;

		String query = "INSERT INTO fit_academia.nota_fiscal(cod_vendas_nf) VALUES (?);";
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setInt(1, nfModel.getCodVendaModel().getCodVendas());
			
			if (!stmt.execute()) {
				this.mensagem = "Nota Fiscal inserido Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;
			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao Inserir NF!";
				check = false;
			}
			// System.out.println(stmt);

		} catch (SQLException e) {
			ObjectData.SendToMsg("Erro ao inserir NF");
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	/*
	public boolean consultaPorNome(String nome){
		String query = "Select nome_marca from servitel_system.marca where nome_marca = ?;";
		ResultSet rs = null;
		boolean check = false;
		try {
			PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				this.mensagem = "Nota Fiscal consultado";
				System.out.println("entrou V");

				check = true;
				System.err.println("if" + check);
			}
			System.out.println("não passou no if:" + check);
			stmt.close();

		} catch (SQLException | RuntimeException ex) {
			ex.printStackTrace();
		}
		return check;
	}*/

	public List<NotaFiscalModel> ConsultaTodos() {
		String query = "SELECT cod_nf, cod_vendas_nf, vendas.descricao FROM fit_academia.nota_fiscal"
				+ "inner join vendas on vendas.cod_vendas = cod_vendas_nf";
		NotaFiscalModel nfModel = null;
		List<NotaFiscalModel> caixaLista = new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nfModel = new NotaFiscalModel();
				VendaModel vModel = new VendaModel();
				nfModel.setCodNf(rs.getInt(1));
				vModel.setCodVendas(rs.getInt("cod_vendas_nf"));
				vModel.setDescricao("descricao");
				nfModel.setCodVendaModel(vModel);
				caixaLista.add(nfModel);
			}

			stmt.close();
		} catch (SQLException ex) {
			ex.getStackTrace();
			throw new RuntimeException(ex);
		}
		return caixaLista;
	}

	public boolean alterar(NotaFiscalModel notaFiscalModel) {
		System.out.println("chamou alteraMarca");
		String alter = "UPDATE fit_academia.nota_fiscal SET cod_vendas_nf=? WHERE cod_nf=?;";
		boolean check;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setInt(1, notaFiscalModel.getCodVendaModel().getCodVendas());
			stmt2.setInt(2, notaFiscalModel.getCodNf());

			if (stmt2.executeUpdate() != 0) {
				this.mensagem = "NF alterada Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;

			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao alterar NF!\n A marca nao existe ou foi informado dados invalidos para alteração";
				check = false;
			}
			stmt2.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public boolean excluir(int id) throws RuntimeException {
		if (id == 0) {
			throw new RuntimeException("id invalido");
		}
		String alter = "DELETE FROM fit_academia.nota_fiscal WHERE cod_nf = ?;";
		boolean check = false;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setInt(1, id);
			if (!stmt2.execute()) {
				this.mensagem = "Marca do Produdo excluida Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;

			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao excluir NF!\n A marca nao existe ou foi informado dados invalidos para alteração";
				check = false;
			}
			stmt2.close();
		} catch (SQLException e) {
			e.getErrorCode();
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
