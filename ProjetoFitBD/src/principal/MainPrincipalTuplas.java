package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

import ADO.ConexaoAdo;

public class MainPrincipalTuplas {
	private static ConexaoAdo conectar = null;
	private static StopWatch t = null;
	private static int cont = 1000;
	private static Connection con = null;
private static  PreparedStatement stmtCon = null;
	public static void main(String[] args) {
		conectar = new ConexaoAdo();
		t = new StopWatch();
		con = conectar.getConexao();

		int op = JOptionPane.showConfirmDialog(null, "sim/ para Inserir 100000 ou Não/ Consultar");
		int idConsulta;

		switch (op) {

		case 0:
			insert();// Função para inserir as 100000 tuplas no banco de dados;
			conectar.fecharConexao();
			break;
		case 1:
			idConsulta = Integer.parseInt(JOptionPane.showInputDialog("digite um id para consulta no Banco de dados:"));
			consulta(idConsulta);// Função para consultar uma das 100000 no banco de

			break;
		case 2:
			System.exit(0);

			break;
		default:
			System.exit(0);
			break;
		}

		System.out.println(t.elapsed() + " - " + t.toString());
	}

	public static void insert() {
		t.start();
		Random r = new Random();
		// Connection con = conectar.getConexao();

		try {
			String query = "INSERT INTO fit_academia.exercicio" + "(descricao, cod_agenda_exer) VALUES (?, ?);";
			PreparedStatement stmt = con.prepareStatement(query);
			// variavel
			String desc = "";

			for (int i = 0; i < cont; i++) {
				for (int y = 0; y < 100; y++) {
					// For para gerar nomes
					for (int x = 0; x < 5; x++) {
						char descfit = (char) (r.nextInt(26) + 65);
						desc += descfit;
					}
					stmt.setString(1, desc);
					stmt.setInt(2, 1);
					stmt.executeUpdate();
					desc = "";
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Função Inserir: "+t.elapsed() + " - " + t.toString());

	}

	public static void consulta(int consulta) {
		t.start();
		
		String query = "select cod_exercicio,descricao,cod_agenda_exer from fit_academia.exercicio where cod_exercicio = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			//stmt = con.prepareStatement(query);
			stmt.setInt(1, consulta);
			ResultSet rs = stmt.executeQuery();
			
			if (rs != null) {
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Codigo: "+rs.getInt(1)+" - "+rs.getString(2)+ " - "+rs.getInt(3) );
				}
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Função Consultar: "+t.elapsed() + " - " + t.toString());
	}
}
