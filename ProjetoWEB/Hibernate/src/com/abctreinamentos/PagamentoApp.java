package com.abctreinamentos;

import java.util.List;
import java.util.Scanner;

public class PagamentoApp {
	public static void main(String[] args) {

		try {
		Scanner entry = new Scanner(System.in);
		int opcao = 0;
		long cdPagamento,cdcurso,cpf;
		String datainscricao, email;
		PagamentoDAO dao = new PagamentoDAO();
		
			while (opcao != 6) {
				System.out.println("Sistema de gerenciamento de Pagamentos");
				System.out.println("====================================");
				System.out.println("Digite [1] para Consultar todos os Pagamentos");
				System.out.println("Digite [2] para Consultar um Pagamento específico");
				System.out.println("Digite [3] para Cadastrar um Pagamento");
				System.out.println("Digite [4] para Alterar data de um Pagamento");
				System.out.println("Digite [5] para Excluir um Pagamento");
				System.out.println("Digite [6] para sair");
				opcao = entry.nextInt();
				
				switch(opcao) {
					case 1:{//Consultar todos
						System.out.println("[1]Consultar todos");
						List<Pagamento> pgtos = dao.findAll();
						pgtos.forEach(System.out::println);
						break;
					}
					case 2:{
						System.out.println("[2]Consultar um Pagamento específico");
						System.out.println("Favor informar o Codigo do Curso");
							cdcurso = entry.nextLong();
						System.out.println("Favor informar o CPF");
							cpf = entry.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						System.out.println(dao.find(pgtoid));
						break;
					}
					case 3:{
						System.out.println("[3]Cadastrar um novo Pagamento");
						System.out.println("Favor informar o CPF");
							cpf = entry.nextLong();
							entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o Curso");
							cdcurso = entry.nextLong();

						//Cria objeto de pagamento
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						
							entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar a Data de Inscricao");
							datainscricao = entry.nextLine();
						Pagamento Pagamento = new Pagamento(pgtoid, datainscricao);
						dao.persist(Pagamento);
						break;
					}
					case 4:{
						System.out.println("[4]Alterar data de um Pagamento");
						System.out.println("Favor informar o CPF");
						cpf = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o Curso");
							cdcurso = entry.nextLong();
	
						//Cria objeto de pagamento
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						
							entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar a Data de Inscricao para alterar");
							datainscricao = entry.nextLine();
						Pagamento Pagamento = new Pagamento(pgtoid, datainscricao);
						dao.merge(Pagamento);
						break;
					}
					case 5:{
						System.out.println("[5]Excluir um Pagamento");
						System.out.println("Favor informar o CPF");
							cpf = entry.nextLong();
						System.out.println("Favor informar o Codigo do Curso");
							cdcurso = entry.nextLong();
						PagamentoId pgtoid = new PagamentoId(cpf, cdcurso);
						Pagamento Pagamento = dao.find(pgtoid);
						dao.delete(Pagamento);
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
