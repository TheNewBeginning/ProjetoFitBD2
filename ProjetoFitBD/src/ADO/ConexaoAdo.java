package ADO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import principal.StopWatch;

public class ConexaoAdo {

    private String driver = "org.postgresql.Driver";
    private String user = "postgres";
    private String senha = "451236";
    private String url = "jdbc:postgresql://localhost:5432/fit";
    private Connection con = null;
    private String msg;
    public ConexaoAdo() {
        try {
            Class.forName(driver);
            this.con = (Connection) DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão Feita Com Sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.print(e.getMessage());
        }
    }

    public Connection getConexao() {
        return this.con;
    }

    public void fecharConexao() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
