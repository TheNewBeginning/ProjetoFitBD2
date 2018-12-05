package ADO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.CaixaModel;
import model.VendaModel;
import objectTransferData.ObjectData;

public class VendasAdo {
	private ConexaoAdo minhaConexao;
	private String mensagem;

	public VendasAdo() {
		try {
			minhaConexao = new ConexaoAdo();
		}catch (Exception e) {
			this.mensagem = "Erro ao se conectar com banco de dados.";
			Logger.getLogger(VendasAdo.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public boolean salvar(VendaModel vendaModel) throws RuntimeException {
		boolean check = false;
		/*
		 * if (marca.getNomeMarca().equals("")) { throw new
		 * RuntimeException("nome da marca está vazio ou incorreto!"); }
		 */

		String query = "INSERT INTO fit_academia.vendas"
				+ "(descricao, cod_caixa_vendas)"
				+ " VALUES (?, ?);";
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setInt(1, vendaModel.getCodVendas());
			stmt.setString(2, vendaModel.getDescricao());
			stmt.setInt(3, vendaModel.getCodCaixaVendas().getCod_caixa());			
			
			if (!stmt.execute()) {
				this.mensagem = "venda feita Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;
			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao No banco de dados de Vendas!";
				check = false;
			}
			// System.out.println(stmt);

		} catch (SQLException e) {
			ObjectData.SendToMsg("Erro ao registrar vendas");
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public boolean consultaPorNome(String nome){
		String query = "Select nome_marca from servitel_system.marca where nome_marca = ?;";
		ResultSet rs = null;
		boolean check = false;
		try {
			PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				this.mensagem = "Marca do Produdo já existe por favor insera outro";
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
	}

	public List<VendaModel> ConsultaTodos() {
		String query = "SELECT cod_vendas, descricao,cod_caixa_vendas, "
				+ "fit_academia.caixa.data_entrada "
				+ "FROM fit_academia.vendas "
				+ "inner join fit_academia.caixa on caixa.cod_caixa = cod_caixa_vendas;";
		VendaModel vendasModel = null;
		List<VendaModel> caixaList = new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CaixaModel caixa = new CaixaModel();
				vendasModel = new VendaModel();
				vendasModel.setCodVendas(rs.getInt(1));
				vendasModel.setDescricao(rs.getString(2));
				caixa.setCod_caixa(rs.getInt("cod_caixa"));
				caixa.setData_entrada(rs.getString("data_entrada"));
				vendasModel.setCodCaixaVendas(caixa);
				caixaList.add(vendasModel);
			}

			stmt.close();
		} catch (SQLException ex) {
			ex.getStackTrace();
			throw new RuntimeException(ex);
		}
		return caixaList;
	}

	public boolean alterar(VendaModel vendaModel) {
		System.out.println("chamou alteraMarca");
		String alter = "UPDATE fit_academia.vendas "
				+ "SET descricao=?, cod_caixa_vendas=? "
				+ "WHERE cod_vendas = ?;";

		boolean check;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setString(1, vendaModel.getDescricao());
			stmt2.setInt(2, vendaModel.getCodCaixaVendas().getCod_caixa());
			stmt2.setDouble(3, vendaModel.getCodVendas());

			if (stmt2.executeUpdate() != 0) {
				this.mensagem = "Marca do Produdo alterada Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;

			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao alterar marca do Produto!\n A marca nao existe ou foi informado dados invalidos para alteração";
				check = false;
			}
			stmt2.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			this.minhaConexao.fecharConexao();
		}
		return check;
	}

	public boolean excluir(int id) throws RuntimeException {
		if (id == 0) {
			throw new RuntimeException("id invalido");
		}
		String alter = "DELETE FROM fit_academia.vendas WHERE cod_vendas = ?;";
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
				this.mensagem = "Erro ao excluir marca do Produto!\n A marca nao existe ou foi informado dados invalidos para alteração";
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
