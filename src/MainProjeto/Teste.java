package MainProjeto;
import java.util.ArrayList;
import java.util.Random;

import Controle.CrudEncomenda;
import Controle.CrudFuncionario;
import Controle.ICRUD;
import Entidades.Encomenda;
import Entidades.Endereco;
import Entidades.Entregador;
import Entidades.Funcionario;
import Entidades.Gerente;

public class Teste {
	public static String gerarTokenAcesso() {
		String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		String tokenAcc = "";
		Random numero = new Random();
		for (int i=0; i<=10; i++) {
			int indice = numero.nextInt(letras.length - 1);
			if (indice % 2 != 0) {
				tokenAcc += letras[indice].toLowerCase();
			} else {
				tokenAcc += letras[indice];								
			}
			tokenAcc += numeros[numero.nextInt(numeros.length - 1)];
		}
		return tokenAcc;
	}
	public static String gerarCodFuncionario(ArrayList<Funcionario> fun) {
		if (fun.isEmpty()) {
			String codFuncionario = "FN0001";
			return (codFuncionario);
		} else {
			int codigo = 1;
			String novoCodigo;
			for (Funcionario funcionario2 : fun) {
				Funcionario ultimo = funcionario2;
				if (ultimo instanceof Gerente) {
					codigo += 1;
				}
			}
			novoCodigo = "GR000"+String.valueOf(codigo - 1);				
			
			return(novoCodigo);
		}
	}
	
	public static void main(String[] args) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		/*- Teste CRUD de funcionario -*/
		ICRUD<Funcionario> crud = new CrudFuncionario();
		ICRUD<Encomenda> crudEnc = new CrudEncomenda();
		Endereco endereco = new Endereco("rua", 1, "bairro", "cidade", "PE", "55294-200");
		String campo = "10/10/2001";
		int dia = Integer.parseInt(campo.substring(0, 2));
		int mes = Integer.parseInt(campo.substring(3, 5));
		int ano = Integer.parseInt(campo.substring(6));
		
		System.out.println(dia);
		System.out.println(mes);
		System.out.println(ano);
		Funcionario fun1 = new Funcionario("Matheus", "11133344455", endereco, "10/10/2001", "123321444");//
		
		System.out.println(crud.insertDados(fun1));
		
		
		Gerente fun2 = new Gerente("Pedro", "11111111111", endereco, "10/10/2001", "123321444");//
		System.out.println(crud.insertDados(fun2));
		
		
		Entregador fun3 = new Entregador("João", "123.321.456-12", endereco, "10/10/2001", "1234567");//
		
		System.out.println(crud.insertDados(fun3));
		
		
		Funcionario fun4 = new Funcionario("Ana", "22222222222", endereco, "10/10/2001", "123456");//
		
		System.out.println(crud.insertDados(fun4));
		
		
		Gerente fun5 = new Gerente("Paula", "33333333333", endereco, "10/10/2001", "123321444");
		
		System.out.println(crud.insertDados(fun5));
		
		
		Entregador fun6 = new Entregador("Tirano", "11111111111", endereco, "10/10/2001", "123321444");
				
		System.out.println(crud.insertDados(fun6));
		
		System.out.println(crud.selectDados());
//		System.out.println(crud.selectFuncionario("FN0002"));
		System.out.println(fun5.getTokenAcesso());
//		Gerente gerente = (Gerente) CrudFuncionario.funcionarios.get(4);
//		System.out.println(gerente.getNome());
//		System.out.println(gerente.getTokenAcesso());
		System.out.println(crud.deleteDados("FN0001", fun5.getTokenAcesso()));
		System.out.println(crud.selectDados());
//		System.out.println();
		for (String logs : CrudFuncionario.LogsAcoes) {
			System.out.println(logs);
		}
		
		

	}

}
