package unidade3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ClienteApp {
	
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "curso_java";
	static String senha = "schema";
	static Connection conexao;
	
	/**************************************************************/
	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection(url,usuario,senha);
		conexao.setAutoCommit(false);
	}
	
	/**************************************************************/
	public static void deconectar() throws SQLException {
		conexao.close();
	}
	
	/**************************************************************/
	public static void inserir(long cpf, String nome, String email) throws SQLException{
		String sql = "INSERT INTO Cliente values ("+cpf+", '"+nome+"', '"+email+"')";
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		conexao.commit();
	}
	
	/************************* Metodo PS *************************************/
	public static void inserirPS(long cpf, String nome, String email) throws SQLException{
		String sql = "INSERT INTO Cliente values (?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setLong(1, cpf);
		stmt.setString(2, nome);
		stmt.setString(3, email);
		stmt.executeUpdate(sql);
		conexao.commit();
	}
	
	/*********************** Metodo SP ***************************************/
	public static void inserirSP(long cpf, String nome, String email) throws SQLException{
		String sql = "{call sp_inserircliente(?,?,?)}";
		CallableStatement cstmt = conexao.prepareCall(sql);
		cstmt.setLong(1, cpf);
		cstmt.setString(2, nome);
		cstmt.setString(3, email);
		cstmt.execute();
		conexao.commit();
	}
	
	/************** @throws SQLException ************************************************/
	public static void consultar(long cpf) throws SQLException{
		String sql = "SELECT * from cliente where cpf = "+ cpf;
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println("cpf: "+ rs.getInt(1)+" nome: "+ rs.getString(2)+" email: "+ rs.getString(3));
		}
	}
	
	/**************************************************************/
	public static void consultarTodos() throws SQLException{
		String sql = "SELECT * from cliente";
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int contador = 0;
		while(rs.next()) {
			System.out.println("cpf: "+ rs.getInt(1)+" nome: "+ rs.getString(2)+" email: "+ rs.getString(3));
			contador++;
			System.out.println("=======================================================================");
		}
		System.out.println("Nro de clientes listados: " + contador+"\n");
	}
	
	/**************************************************************/
	public static void alterar(long cpf, String nome, String email) throws SQLException {
		String sql = "UPDATE cliente SET NOME = '"+nome+ "', email ='"+ email + "' WHERE cpf = "+cpf;
		Statement stmt = conexao.createStatement();
		stmt.executeUpdate(sql);
		conexao.commit();
	}
	
	/**************************************************************/
	public static void excluir(long cpf) throws SQLException{
		String sql = "DELETE FROM cliente WHERE cpf = "+cpf;
		Statement stmt = conexao.createStatement();
		stmt.executeUpdate(sql);
		conexao.commit();
	}
	
	/**************************************************************/
	public static void main(String[] args) {
		try {
		conectar();
		
		Scanner entry = new Scanner(System.in);
		int opcao = 0;
		long cpf;
		String nome, email;
		
			while (opcao != 6) {
				System.out.println("Sistema de gerenciamento de clientes");
				System.out.println("====================================");
				System.out.println("Digite [1] para Consultar todos os clientes");
				System.out.println("Digite [2] para Consultar um cliente específico");
				System.out.println("Digite [3] para Cadastrar um cliente");
				System.out.println("Digite [4] para Alterar um cliente");
				System.out.println("Digite [5] para Excluir um cliente");
				System.out.println("Digite [6] para sair");
				opcao = entry.nextInt();
				
				switch(opcao) {
					case 1:{//Consultar todos
						System.out.println("[1]Consultar todos");
						consultarTodos();
						break;
					}
					case 2:{
						System.out.println("[2]Consultar um Cliente específico");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						consultar(cpf);
						break;
					}
					case 3:{
						System.out.println("[3]Cadastrar um novo Cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o nome");
						nome = entry.nextLine();
						System.out.println("Favor informar o email");
						email = entry.nextLine();
						inserirSP(cpf, nome, email);
						break;
					}
					case 4:{
						System.out.println("[3]Cadastrar um novo Cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o nome");
						nome = entry.nextLine();
						System.out.println("Favor informar o email");
						email = entry.nextLine();
						alterar(cpf, nome, email);
						break;
					}
					case 5:{
						System.out.println("[5]Excluir um cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						excluir(cpf);
						break;
					}
					case 6:{
						System.out.println("Encerrando o sistema...");
						break;
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}