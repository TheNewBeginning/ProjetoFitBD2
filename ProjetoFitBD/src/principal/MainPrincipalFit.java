package principal;

import ADO.CaixaAdo;
import ADO.ProdutoAdo;
import ADO.VendasAdo;
import model.CaixaModel;
import model.ProdutoModel;
import model.VendaModel;

public class MainPrincipalFit {

	public static void main(String[] args) {
		// Instancias de todas Ados.
		VendasAdo vendaAdo = new VendasAdo();
		CaixaAdo caixaAdo = new CaixaAdo();
		ProdutoAdo produtoAdo = new ProdutoAdo();
		// Instancias de todas Modeles.
		CaixaModel caixa = new CaixaModel(1, "29/11/2018", "20/11/2018", 200.00, 50.00);
		CaixaModel caixa1 = new CaixaModel(2, "30/11/2018", "30/11/2018", 100.00, 10.00);
		VendaModel venda = new VendaModel(0, "O Cliente comprou notbook hp", caixa);
		ProdutoModel produto = new ProdutoModel("Notebook Samsumga", 5);
		ProdutoModel produto1 = new ProdutoModel("Computador Desktop HP", 25);
		ProdutoModel produto2 = new ProdutoModel("Celular Iphone", 1);
		ProdutoModel produto3 = new ProdutoModel("Mouse Kross elegance", 5);
		ProdutoModel produto4 = new ProdutoModel("Suplmento Max titanium", 10);
		ProdutoModel produto5 = new ProdutoModel("Luva de proteção", 5);
		ProdutoModel produto6 = new ProdutoModel("camisetas", 3);
		ProdutoModel produto7 = new ProdutoModel("bermuda sports", 5);

		produtoAdo.salvar(produto);
		produtoAdo.salvar(produto1);
		produtoAdo.salvar(produto2);
		produtoAdo.salvar(produto3);
		produtoAdo.salvar(produto4);
		produtoAdo.salvar(produto5);
		produtoAdo.salvar(produto6);
		produtoAdo.salvar(produto7);

		// Chamada de função de Ado.
	}

}
