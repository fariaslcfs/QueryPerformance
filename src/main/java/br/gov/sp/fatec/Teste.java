package br.gov.sp.fatec;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.jfree.ui.RefineryUtilities;

import antlr.ParserSharedInputState;

/**
 * @author Luiz Carlos Farias da Silva
 * email - fariaslcfs@gmail.com luiz.silva116@fatec.sp.gov.br
 */

public class Teste {
	
	Form form = new Form();
	int i;
	
	double time_before;
	double time_after;

	double time_elapsed_sqlserver_hard;
	double time_elapsed_sqlserver_firm;
	double time_elapsed_sqlserver_soft;
	double time_elapsed_mysql_hard;
	double time_elapsed_mysql_firm;
	double time_elapsed_mysql_soft;
	double time_elapsed_oracle_hard;
	double time_elapsed_oracle_firm;
	double time_elapsed_oracle_soft;

	ResultSet r;

	String servico; // serviço correspondente à  chave

	String userMysql = "root";
	String passMysql = "741555";
	String connectorMysql = "jdbc:mysql://localhost/teste";

	String connectorSqlServer = "jdbc:sqlserver://hpquartinho:1433;databaseName=teste;selectMethod=cursor";
	String userSqlServer = "sa";
	String passSqlServer = "741555";

	String userOracle = "system";
	String passOracle = "741555";
	String connectorOracle = "jdbc:oracle:thin:@//localhost:1521/xe";
	
	// --------------------------- HARDCODED SQLSERVER ----------------------------------------
	public double StatSqlServerHard(int limite, String usqlserver, String psqlserver) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(connectorSqlServer, usqlserver, psqlserver);
		Statement st = con.createStatement();
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			String query = "SELECT SERVICO FROM PRESTADO WHERE ID = 1";
			r = st.executeQuery(query);
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		st.close();
		time_after = System.currentTimeMillis();
		time_elapsed_sqlserver_hard = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo HARDCODED_SQLSERVER: " + time_elapsed_sqlserver_hard + " segundo(s) - " + servico);
		return time_elapsed_sqlserver_hard;
	}

	// --------------------------- HARDCODED MYSQL --------------------------------------------
	public double StatMysqlHard(int limite, String umysql, String pmysql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(connectorMysql, umysql, pmysql);
		Statement st = con.createStatement();
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			String query = "SELECT SERVICO FROM PRESTADO WHERE ID = 1";
			r = st.executeQuery(query);
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		st.close();
		time_after = System.currentTimeMillis();
		time_elapsed_mysql_hard = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo HARDCODED_MYSQL: " + time_elapsed_mysql_hard + " segundo(s) - " + servico);
		return time_elapsed_mysql_hard;
	}

	// --------------------------- HARDCODED ORACLE -------------------------------------------
	public double StatOracleHard(int limite, String uoracle, String poracle) throws ClassNotFoundException,	SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(connectorOracle, uoracle, poracle);
		Statement st = con.createStatement();
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			String query = "SELECT SERVICO FROM PRESTADO WHERE ID = 1";
			r = st.executeQuery(query);
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		st.close();
		time_after = System.currentTimeMillis();
		time_elapsed_oracle_hard = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo HARDCODED_ORACLE: " + time_elapsed_oracle_hard + " segundo(s) - " + servico);
		return time_elapsed_oracle_hard;
	}

	// --------------------------- FIRMCODED SQLSERVER ---------------------------------------
	public double PrepStatSqlServerlFirm(int limite, String usqlserver, String psqlserver)	throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(connectorSqlServer, usqlserver, psqlserver);
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			PreparedStatement pst = con.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		time_after = System.currentTimeMillis();
		time_elapsed_sqlserver_firm = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo FIRMCODED_SQLSERVER: " + time_elapsed_sqlserver_firm + " segundo(s) - " + servico);
		return time_elapsed_sqlserver_firm;
	}

	// --------------------------- FIRMCODED MYSQL -------------------------------------------
	public double PrepStatMysqlFirm(int limite, String umysql, String pmysql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(connectorMysql, umysql, pmysql);
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			PreparedStatement pst = con.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		time_after = System.currentTimeMillis();
		time_elapsed_mysql_firm = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo FIRMCODED_MYSQL: " + time_elapsed_mysql_firm + " segundo(s) - " + servico);
		return time_elapsed_mysql_firm;
	}

	// --------------------------- FIRMCODED ORACLE -------------------------------------------
	public double PrepStatOracleFirm(int limite, String uoracle, String poracle) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(connectorOracle, uoracle, poracle);
		time_before = System.currentTimeMillis();
		for (i = 1; i <= limite; i++) {
			PreparedStatement pst = con.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
			pst.close();
		}
		time_after = System.currentTimeMillis();
		time_elapsed_oracle_firm = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo FIRMCODED_ORACLE: " + time_elapsed_oracle_firm + " segundo(s) - " + servico);
		return time_elapsed_oracle_firm;
	}

	// --------------------------- SOFTCODED SQLSERVER ----------------------------------------
	public double PrepStatSqlServerSoft(int limite, String usqlserver, String psqlserver) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(connectorSqlServer, usqlserver, psqlserver);
		time_before = System.currentTimeMillis();
		PreparedStatement pst = con
				.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
		for (i = 1; i <= limite; i++) {
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		pst.close();
		time_after = System.currentTimeMillis();
		time_elapsed_mysql_soft = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo SOFTCODED_SQLSERVER: " + time_elapsed_mysql_soft + " segundo(s) - " + servico);
		return time_elapsed_mysql_soft;
	}

	// ------------------------------ SOFTCODED MYSQL -----------------------------------------
	public double PrepStatMysqlSoft(int limite, String umysql, String pmysql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(connectorMysql, umysql, pmysql);
		time_before = System.currentTimeMillis();
		PreparedStatement pst = con.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
		for (i = 1; i <= limite; i++) {
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		pst.close();
		time_after = System.currentTimeMillis();
		time_elapsed_mysql_soft = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo SOFTCODED_MYSQL: " + time_elapsed_mysql_soft + " segundo(s) - " + servico);
		return time_elapsed_mysql_soft;
	}

	
	// ------------------------------ SOFTCODED ORACLE ----------------------------------------
	public double PrepStatlOracleSoft(int limite, String uoracle, String poracle) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(connectorOracle, uoracle, poracle);
		time_before = System.currentTimeMillis();
		PreparedStatement pst = con.prepareStatement("SELECT SERVICO FROM PRESTADO WHERE ID = ?");
		for (i = 1; i <= limite; i++) {
			pst.setInt(1, 1);
			r = pst.executeQuery();
			if (r.next()) {
				servico = r.getString(1);
			}
			r.close();
		}
		pst.close();
		time_after = System.currentTimeMillis();
		time_elapsed_oracle_soft = (time_after - time_before) / 1000;
		System.out.println("Tempo total para o modo SOFTCODED_ORACLE: " + time_elapsed_oracle_soft + " segundo(s) - " + servico);
		return time_elapsed_oracle_soft;
	}

	// ---------------------------------------- Testar ------------------------------------------
	public Collection<Double> Testar(int limite, String usersqlserver, String passsqlserver, String usermysql, String passmysql, String useroracle, String passoracle
								   ) throws ClassNotFoundException, SQLException {

		Teste teste = new Teste();
		
		Collection<Double> colrets = new ArrayList<Double>();
		
		GraficoBarra gbSqlServer = new GraficoBarra("");
		GraficoBarra gbMysql = new GraficoBarra("");
		GraficoBarra gbComplete = new GraficoBarra("");
		
		GraficoBarra gbOracle = new GraficoBarra("");
		double t1 = teste.StatSqlServerHard(limite,usersqlserver,passsqlserver);
		double t2 = teste.PrepStatSqlServerlFirm(limite,usersqlserver,passsqlserver);
		double t3 = teste.PrepStatSqlServerSoft(limite,usersqlserver,passsqlserver);

		double t4 = teste.StatMysqlHard(limite,usermysql,passmysql);
		double t5 = teste.PrepStatMysqlFirm(limite,usermysql,passmysql);
		double t6 = teste.PrepStatMysqlSoft(limite,usermysql,passmysql);

		Double t7 = teste.StatOracleHard(limite,useroracle,passoracle);
		colrets.add(t7); 
		Double t8 = teste.PrepStatOracleFirm(limite,useroracle,passoracle);
		colrets.add(t8);
		Double t9 = teste.PrepStatlOracleSoft(limite,useroracle,passoracle);
		colrets.add(t9);
		Color r = new Color(255, 0, 0, 200);
		Color g = new Color(0, 255, 0, 200);
		Color b = new Color(0, 0, 255, 200);

		// GRÁFICO SQLSERVER
		gbSqlServer.geraGrafico("DESEMPENHO PARSER SQLSERVER", limite, r, t1, t2, t3);
		gbSqlServer.pack();
		RefineryUtilities.positionFrameOnScreen(gbSqlServer, 0.0, 0.0);
		gbSqlServer.setVisible(true);

		// GRÁFICO MYSQL
		gbMysql.geraGrafico("DESEMPENHO PARSER MYSQL", limite, g, t4, t5, t6);
		gbMysql.pack();
		RefineryUtilities.positionFrameOnScreen(gbMysql, 0.50, 0.0);
		gbMysql.setVisible(true);

		// GRÁFICO ORACLE
		gbOracle.geraGrafico("DESEMPENHO PARSER ORACLE", limite, b, t7, t8, t9);
		gbOracle.pack();
		RefineryUtilities.positionFrameOnScreen(gbOracle, 1.0, 0.0);
		gbOracle.setVisible(true);

		// GRÁFICO SQLSERVER-MYSQL-ORACLE
		gbComplete.geraGraficoCompleto("DESEMPENHO COMPARATIVO PARSERS SQLSERVER-MYSQL-ORACLE", limite, t1, t4, t7, t2, t5, t8, t3, t6, t9);
		gbComplete.pack();
		RefineryUtilities.positionFrameOnScreen(gbComplete, 0.5, 0.9);
		gbComplete.setVisible(true);
		
		return colrets;
	
	}

	// -------------------------------------------- MAIN ---------------------------------------------
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Teste t = new Teste();
		Form f = new Form();
		f.criaForm();
		f.frm.setResizable(false);
		f.frm.setVisible(true);
	}
}
