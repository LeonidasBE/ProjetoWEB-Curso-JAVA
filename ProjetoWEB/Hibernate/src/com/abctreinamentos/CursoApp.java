package com.abctreinamentos;

import java.util.List;
import java.util.Scanner;

public class CursoApp {

	public static void main(String[] args) {

		try {
		Scanner entry = new Scanner(System.in);
		int opcao = 0;
		long cdcurso,valor;
		String nome,url, email;
		CursoDAO dao = new CursoDAO();
		
			while (opcao != 6) {
				System.out.println("Sistema de gerenciamento de Cursos");
				System.out.println("====================================");
				System.out.println("Digite [1] para Consultar todos os Cursos");
				System.out.println("Digite [2] para Consultar um Curso específico");
				System.out.println("Digite [3] para Cadastrar um Curso");
				System.out.println("Digite [4] para Alterar um Curso");
				System.out.println("Digite [5] para Excluir um Curso");
				System.out.println("Digite [6] para sair");
				opcao = entry.nextInt();
				
				switch(opcao) {
					case 1:{//Consultar todos
						System.out.println("[1]Consultar todos");
						List<Curso> cursos = dao.findAll();
						cursos.forEach(System.out::println);
						break;
					}
					case 2:{
						System.out.println("[2]Consultar um Curso específico");
						System.out.println("Favor informar o Codigo do curso");
						cdcurso = entry.nextLong();
						System.out.println(dao.find(cdcurso));
						break;
					}
					case 3:{
						System.out.println("[3]Cadastrar um novo Curso");
						System.out.println("Favor informar o Codigo do curso");
							cdcurso = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o Nome");
							nome = entry.nextLine();
						System.out.println("Favor informar o Valor");
							valor = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar a URL");
							url = entry.nextLine();
						Curso Curso = new Curso(cdcurso,nome,valor,url);
						dao.persist(Curso);
						break;
					}
					case 4:{
						System.out.println("[4]Alterar um Curso");
						System.out.println("Favor informar o Codigo do curso");
							cdcurso = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar o Nome");
							nome = entry.nextLine();
						System.out.println("Favor informar o Valor");
							valor = entry.nextLong();
						entry.nextLine(); //Esvaziar o buffer do teclado
						System.out.println("Favor informar a URL");
							url = entry.nextLine();
						Curso Curso = new Curso(cdcurso,nome,valor,url);
						dao.merge(Curso);
						break;
					}
					case 5:{
						System.out.println("[5]Excluir um Curso");
						System.out.println("Favor informar o Codigo do curso");
							cdcurso = entry.nextLong();
						Curso Curso = dao.find(cdcurso);
						dao.delete(Curso);
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
