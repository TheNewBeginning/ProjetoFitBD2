package principal;

import javax.swing.JOptionPane;

import ADO.ProdutoAdo;
import model.ProdutoModel;

public class Produto {

	public static void main(String[] args) {
		ProdutoAdo pAdo = new ProdutoAdo();
		// Instacia o objeto Modal de produto e ja manda o que foi pegado pelos
		// JOpTionPane por parametro
		int op = Integer.parseInt(JOptionPane.showInputDialog(
				"" + "digite 1- para inserir\nou 2- para Consultar\nOu 3- para excluir\nOu 4- para alterar"));
		switch (op) {
		case 1:
			ProdutoModel p = new ProdutoModel(JOptionPane.showInputDialog("Digite uma descrição:"),
					Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produtos:")));
			pAdo.salvar(p);
			break;
		case 2:
			String nome = JOptionPane.showInputDialog("digite o nome de um produto:");
			p = pAdo.consultaPorNome(nome);
			if (p != null) {
				JOptionPane.showMessageDialog(null, pAdo.getMensagem());
			} else {
				JOptionPane.showMessageDialog(null, pAdo.getMensagem());
			}
			break;
		default:
			break;
		}

	}

}
