package ADO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.CaixaModel;
import model.ProdutoModel;
import objectTransferData.ObjectData;

public class ProdutoAdo {
	private ConexaoAdo minhaConexao;
	private String mensagem;

	public ProdutoAdo() {
		try {
			minhaConexao = new ConexaoAdo();
		} catch (Exception e) {
			this.mensagem = "Erro ao se conectar com banco de dados.";
			JOptionPane.showMessageDialog(null, this.mensagem);
			Logger.getLogger(ProdutoAdo.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public boolean salvar(ProdutoModel produtoModel) throws RuntimeException {
		boolean check = false;

		String query = "INSERT INTO fit_academia.produto" + "(descricao, quantidade) VALUES (?, ?);";
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setString(1, produtoModel.getDescricao());
			stmt.setInt(2, produtoModel.getQuantidade());
			System.out.println("Insert: " + stmt.toString());
			if (!stmt.execute()) {
				this.mensagem = "Produto inserido Com Sucesso!";
				ObjectData.SendToMsg(this.mensagem);
				check = true;
			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao No banco de dados de Produto!";
				ObjectData.SendToMsg(this.mensagem + " - " + minhaConexao.getMsg());
				check = false;
			}
			// System.out.println(stmt);

		} catch (SQLException e) {
			ObjectData.SendToMsg("Erro ao registrar produto");
		} finally {
			minhaConexao.fecharConexao();
		}
		return check;
	}

	public List<ProdutoModel> ConsultaTodosProdutos() {
		System.out.println("Entrou");
		String query = "SELECT cod_produto, descricao, quantidade FROM fit_academia.produto;";
		ProdutoModel produtoModel = null;
		List<ProdutoModel> produtoLista = new ArrayList<>();
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					produtoModel = new ProdutoModel();
					produtoModel.setCod_produto(rs.getInt(1));
					produtoModel.setDescricao(rs.getString(2).trim());
					produtoModel.setQuantidade(rs.getInt(3));
					produtoLista.add(produtoModel);
				}
				if (rs.getRow() == 0) {
					this.mensagem = "Não contém nenhum Produto cadastrado.";

				}

			} else {
				this.mensagem = "Não contém nenhum Produto cadastrado.";
			}

			stmt.close();
		} catch (SQLException ex) {
			ex.getStackTrace();
			throw new RuntimeException(ex);
		}
		return produtoLista;
	}

	public ProdutoModel consultaPorNome(String nome) {
		ResultSet rs = null;
		ProdutoModel p = null;
		try {
			String query = "SELECT cod_produto, descricao, quantidade "
					+ "FROM fit_academia.produto where descricao = ?;";
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			System.out.println(stmt);
			if (rs != null) {
				if (rs.next()) {
					p = new ProdutoModel(rs.getInt(1), rs.getString(2), rs.getInt(3));
					this.mensagem = "Consulta do produto feita com sucesso";
					System.out.println("entrou V");
				} else {
					this.mensagem = "nenhum produto encontrado.";

				}
			}
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			minhaConexao.fecharConexao();
		}

		return p;

	}

	public boolean delete(int id) {
		if (id == 0) {
			throw new RuntimeException("id invalido");
		}
		String alter = "DELETE FROM fit_academia.produto WHERE cod_produto = ?;" + ";";
		boolean check = false;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setInt(1, id);
			if (!stmt2.execute()) {
				this.mensagem = "Produto excluida Com Sucesso!";
				System.out.println(minhaConexao.getMsg());
				check = true;

			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao excluir o Produto!\n ele nao existe ou foi informado dados invalidos para excluir";
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

	public boolean alterar(ProdutoModel produtoModel) {
		String alter = "UPDATE fit_academia.produto SET descricao=?, quantidade=? WHERE cod_produto = ?;";

		boolean check;

		try {
			java.sql.PreparedStatement stmt2 = this.minhaConexao.getConexao().prepareStatement(alter);
			stmt2.setString(1, produtoModel.getDescricao());
			stmt2.setInt(2, produtoModel.getQuantidade());
			stmt2.setInt(3, produtoModel.getCod_produto());

			if (stmt2.executeUpdate() != 0) {
				this.mensagem = "Produto alterada Com Sucesso!";
				check = true;

			} else {
				this.mensagem = "Erro ao alterar Produto!\n Ele nao existe ou foi informado dados invalidos para alteração";
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
