package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import ADO.CaixaAdo;
import ADO.ProdutoAdo;
import ADO.VendaAdo;
import model.CaixaModel;
import model.ProdutoModel;
import model.ProdutoVendas;
import model.VendaModel;
import objectTransferData.ObjectData;

public class ProgramaFit {

	private static ProdutoAdo pAdo = null;
	private static VendaAdo vAdo = null;
	private static CaixaAdo cAdo = null;

	public static void main(String[] args) {
		// Instacia o objeto Modal de produto e ja manda o que foi pegado pelos
		// JOpTionPane por parametro
		// do {
		int op = Integer.parseInt(JOptionPane.showInputDialog(
				"digite:" + "\n1- para realizar vendas!\nou 3- para registrar um caixa para venda!\nOu 4- para Sair"));

		/*
		 * int op2 = Integer.parseInt(JOptionPane.showInputDialog( "" +
		 * "digite:\n1- para inserir\nou 2- para Consultar\nOu 3- para excluir\nOu 4- para alterar"
		 * ));
		 */
		switch (op) {

		case 1:
			List<CaixaModel> listCaixa = new ArrayList();
			vAdo = new VendaAdo();
			cAdo = new CaixaAdo();
			listCaixa = cAdo.ConsultaTodos();

			if (listCaixa.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						cAdo.getMensagem() + " - " + "\nComo não existe caixa estamos adicionando os caixas agora!.");
				adicionaCaixa();

			} else {

				for (CaixaModel caixaModel : listCaixa) {
					System.out.println("\nCodigo Caixa:" + caixaModel.getCod_caixa() + "\n" + "Data entrada:"
							+ caixaModel.getData_entrada() + "\n" + "Data Saida:" + caixaModel.getData_saida() + "\n"
							+ "Valor Entrada:" + caixaModel.getValores_entrada() + "\n" + "Valor Saida:"
							+ caixaModel.getValores_saida());
				}
			}

			/*
			 * Fiz essa função para estanciar novamente a classe ado pois sempre que
			 * precisava consultar denovo dava erro pois ja tinha //fechado a conexao com a
			 * Dba e essa tabela
			 */
			CaixaModel model = escolheCaixa();
			if (model.equals(null)) {
				JOptionPane.showMessageDialog(null, ObjectData.getMsg());

			}
			// ------------------------------------------//

			System.out.println(model.toString());
			pAdo = new ProdutoAdo();
			List<ProdutoModel> listProduto = new ArrayList();
			listProduto = pAdo.ConsultaTodosProdutos();

			if (listProduto.isEmpty()) {
				JOptionPane.showMessageDialog(null, pAdo.getMensagem());

			} else {
				for (ProdutoModel produtoModel : listProduto) {
					System.out.println("Codigo Produto:" + produtoModel.getCod_produto() + "\n" + "Descrição:"
							+ produtoModel.getDescricao() + "\n" + "Quantidade:" + produtoModel.getQuantidade() + "\n");
				}
			}

			VendaModel v = new VendaModel(JOptionPane.showInputDialog("Digite a descrição da venda:"), model);
			vAdo.salvar(v, 1);
			
			System.out.println(v.toString());
			// v.setCodCaixaVendas();
			/*
			 * ProdutoModel p = new
			 * ProdutoModel(JOptionPane.showInputDialog("Digite uma descrição:"),
			 * Integer.parseInt(JOptionPane.
			 * showInputDialog("Digite a quantidade de produtos:")));
			 * 
			 * if (p.checkAtributos()) { if (pAdo.salvar(p)) {
			 * JOptionPane.showMessageDialog(null, ObjectData.getMsg()); } else {
			 * JOptionPane.showMessageDialog(null, ObjectData.getMsg()); } } else {
			 * JOptionPane.showMessageDialog(null, ObjectData.getMsg()); }
			 */
			break;
		case 2:
			adicionaCaixa();
			break;
		default:
			break;
		}

		// } while (op <= 4);

	}

	public static CaixaModel escolheCaixa() {
		CaixaModel caixamodel = new CaixaModel();
		cAdo = new CaixaAdo();
		int id = Integer.parseInt(JOptionPane.showInputDialog("Escolha um caixa para a venda começando do zero:"));

		System.out.println("Identificador:" + id);

		if ((caixamodel = cAdo.consultaPorId(id)) == null) {
			JOptionPane.showMessageDialog(null, ObjectData.getMsg());
		}
		return caixamodel;
	}

	public static void adicionaCaixa() {
		cAdo = new CaixaAdo();
		String data = "05/12/2018";
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dt.parse(data);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			CaixaModel caixa = new CaixaModel(2, dt1.format(date), dt1.format(date), 0.00, 0.00);
			cAdo.salvar(caixa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
