package ADO;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.ProdutoModel;
import objectTransferData.ObjectData;

public class ProdutoAdo {
	private ConexaoAdo minhaConexao;
	private String mensagem;
	public ProdutoAdo() {
		try {
			minhaConexao = new ConexaoAdo();
		}catch (Exception e) {
			this.mensagem = "Erro ao se conectar com banco de dados.";
			JOptionPane.showMessageDialog(null, this.mensagem);
			Logger.getLogger(ProdutoAdo.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public boolean salvar(ProdutoModel produtoModel) throws RuntimeException {
		boolean check = false;

		String query = "INSERT INTO fit_academia.produto"
				+ "(descricao, quantidade) VALUES (?, ?);";
		try {
			java.sql.PreparedStatement stmt = this.minhaConexao.getConexao().prepareStatement(query);
			stmt.setString(1, produtoModel.getDescricao());
			stmt.setInt(2, produtoModel.getQuantidade());			
			System.out.println("Insert: "+ stmt.toString());
			if (!stmt.execute()) {
				this.mensagem = "Produto inserido Com Sucesso!";
				ObjectData.SendToMsg(this.mensagem);
				System.out.println(minhaConexao.getMsg());
				check = true;
			} else {
				System.out.println(minhaConexao.getMsg());
				this.mensagem = "Erro ao No banco de dados de Produto!";
				ObjectData.SendToMsg(this.mensagem);
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
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
