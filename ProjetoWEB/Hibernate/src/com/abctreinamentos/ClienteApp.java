package com.abctreinamentos;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;

public class ClienteApp {

	public static void main(String[] args) {

		try {
		Scanner entry = new Scanner(System.in);
		int opcao = 0;
		long cpf;
		String nome, email;
		ClienteDAO dao = new ClienteDAO();
		
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
						List<Cliente> clientes = dao.findAll();
						clientes.forEach(System.out::println);
						
						break;
					}
					case 2:{
						System.out.println("[2]Consultar um Cliente específico");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						System.out.println(dao.find(cpf));
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
						dao.persist(cliente);
						
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
						dao.merge(cliente);
						break;
					}
					case 5:{
						System.out.println("[5]Excluir um cliente");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						
						Cliente cliente = dao.find(cpf);
						dao.delete(cliente);
						
						break;
					}
					case 6:{
						System.out.println("Encerrando o sistema...");
						break;
					}
				}
			}
			entry.close();
		}catch(Exception e) {
			System.out.println("Problem creating session factory");
		     e.printStackTrace();
		}

	}

}
