package com.abctreinamentos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClienteDAO {
	
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String usuario = "curso_java";
	static String senha = "schema";
	static Connection conexao;
	
	/**************************************************************/
	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection(url,usuario,senha);
		DatabaseMetaData meta = conexao.getMetaData();
		System.out.println("Conectado" + meta.getDatabaseProductVersion());
	}
	
	/**************************************************************/
	public static void desconectar() throws SQLException {
		conexao.close();
	}
	
	/**************************************************************/
	public static void inserir(Cliente cliente) throws SQLException{
		String sql = "INSERT INTO Cliente values ("+cliente.getCpf()+", '"+cliente.getNome()+"', '"+cliente.getEmail()+"')";
		Statement stmt = conexao.createStatement();
		stmt.execute(sql);
		conexao.commit();
	}
	
	/************** @throws SQLException ************************************************/
	public static Cliente consultar(long cpf) throws SQLException{
		String sql = "SELECT * from cliente where cpf = "+ cpf;
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		Cliente cliente = null;
		
		while(rs.next()) {
			cliente = new Cliente(rs.getLong(1),rs.getString(2), rs.getString(3));
		}
		return cliente;
	}
	
	/**************************************************************/
	public static List<Cliente> consultarTodos() throws SQLException{
		String sql = "SELECT * from cliente";
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Cliente> clientes = new LinkedList<>();
		while(rs.next()) {
			
			Cliente cliente = new Cliente(rs.getLong(1),rs.getString(2), rs.getString(3));
			clientes.add(cliente);
		}
		return clientes;
	}
	
	/**************************************************************/
	public static void alterar(Cliente cliente) throws SQLException {
		String sql = "UPDATE cliente SET NOME = '"+cliente.getNome()+ "', email ='"+ cliente.getEmail() + "' WHERE cpf = "+cliente.getCpf();
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
						List<Cliente> clientes = ClienteDAO.consultarTodos();
						clientes.forEach(System.out::println);
						System.out.println("Numero de clientes >>> " + clientes.size() + "\n");
						break;
					}
					case 2:{
						System.out.println("[2]Consultar um Cliente específico");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						Cliente cliente = ClienteDAO.consultar(cpf);
						System.out.println(cliente);
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
						Cliente cliente = new Cliente(cpf,nome,email);
						ClienteDAO.inserir(cliente);
						break;
					}
					case 4:{
						System.out.println("[4]Alterar um Cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o nome");
						nome = entry.nextLine();
						System.out.println("Favor informar o email");
						email = entry.nextLine();
						Cliente cliente = new Cliente(cpf,nome,email);
						ClienteDAO.alterar(cliente);
						break;
					}
					case 5:{
						System.out.println("[5]Excluir um cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						ClienteDAO.excluir(cpf);
						break;
					}
					case 6:{
						System.out.println("Encerrando o sistema...");
						break;
					}
				}
			}
			entry.close();
			desconectar();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}