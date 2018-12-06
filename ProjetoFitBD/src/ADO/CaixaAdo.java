package ADO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.CaixaModel;
import objectTransferData.ObjectData;

public class CaixaAdo {
	private ConexaoAdo minhaConexao;
	private String mensagem;

	public CaixaAdo() {
		try {
			minhaConexao = new ConexaoAdo();
		} catch (Exception e) {
			Logger.getLogger(CaixaAdo.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public boolean salvar(CaixaModel caixa) throws RuntimeException {
		boolean check = false;
		/*
		 * if (marca.getNomeMarca().equals("")) { throw new
		 * RuntimeException("nome da marca está vazio ou incorreto!"); }
		 */

		String query = "INSERT INTO fit_academia.caixa(cod_caixa, data_entrada, data_saida, valores_entrada, va                                                                                                          lores_saida) "
				+ "VALUES (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setInt(1, caixa.getCod_caixa());
			stmt.setString(2, caixa.getData_entrada());
			stmt.setString(3, caixa.getData_saida());
			stmt.setDouble(4, caixa.getValores_entrada());
			stmt.setDouble(5, caixa.getValores_saida());
			if (!stmt.execute()) {
				this.mensagem = "caixa inserido com sucesso";
				System.out.println("stmt: " + stmt);
				check = true;
			}
			stmt.close();

			// System.out.println(stmt);

		} catch (SQLException e) {
			ObjectData.SendToMsg(this.mensagem = "Erro de banco de dados ao inserir Caixa");
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public CaixaModel consultaPorId(int id) {
		String query = "SELECT cod_caixa,data_entrada, data_saida, valores_entrada, valores_saida FROM fit_academia.caixa where cod_caixa = ?;";
		CaixaModel model = null;
		try {
			PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					if(rs.getRow() == 0) {
						this.mensagem = "Nenhum Caixa Cadastrado";
					}
					model = new CaixaModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
							rs.getDouble(5));
					this.mensagem = "Marca do Produdo já existe por favor insera outro";
					System.out.println("entrou V");
				}
			}
			stmt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);

		} finally {
			minhaConexao.fecharConexao();
		}
		return model;
	}

	public List<CaixaModel> ConsultaTodos() {
		String query = "SELECT cod_caixa, data_entrada, " + "data_saida, valores_entrada, valores_saida "
				+ "FROM fit_academia.caixa;";
		CaixaModel caixaModel = null;
		List<CaixaModel> caixaLista = new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					caixaModel = new CaixaModel();
					caixaModel.setCod_caixa(rs.getInt(1));
					caixaModel.setData_entrada(rs.getString(2));
					caixaModel.setData_saida(rs.getString(3));
					caixaModel.setValores_entrada(rs.getDouble(4));
					caixaModel.setValores_saida(rs.getDouble(5));
					caixaLista.add(caixaModel);
				}

			} else {
				this.mensagem = "Não contém nenhum Caixa cadastrado.";
			}

			stmt.close();
		} catch (SQLException ex) {
			ex.getStackTrace();
			throw new RuntimeException(ex);
		} finally {
			minhaConexao.fecharConexao();
		}
		return caixaLista;
	}

	public boolean alterar(CaixaModel caixaModel) {
		System.out.println("chamou alteraMarca");
		String alter = "UPDATE fit_academia.caixa " + "SET data_entrada=?, data_saida=?, "
				+ "valores_entrada=?, valores_saida=? " + "WHERE cod_caixa = ?;";
		boolean check;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setString(1, caixaModel.getData_entrada());
			stmt2.setString(2, caixaModel.getData_saida());
			stmt2.setDouble(3, caixaModel.getValores_entrada());
			stmt2.setDouble(4, caixaModel.getValores_saida());
			stmt2.setInt(5, caixaModel.getCod_caixa());

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
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public boolean excluir(int id) throws RuntimeException {
		if (id == 0) {
			throw new RuntimeException("id invalido");
		}
		String alter = "DELETE FROM fit_academia.caixa WHERE cod_caixa = ? ;";
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
