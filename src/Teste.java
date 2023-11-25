import java.util.ArrayList;
import java.util.Random;

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
		
		/*- Teste CRUD de funcionario -*/
		ICRUD crud = new CrudFuncionario();
		ICRUD crudEnc = new CrudEncomenda();
		Endereco endereco = new Endereco("rua", 1, "bairro", "cidade", "estado", "cEP");
		
		Funcionario fun1 = new Funcionario("Matheus", "11133344455", endereco, "10/10/2001", "123321444");//
		ICRUD.arr.add(fun1);
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		Gerente fun2 = new Gerente("Pedro", "11111111111", endereco, "", "123321444");//
		ICRUD.arr.add(fun2);
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		Entregador fun3 = new Entregador("João", "123.321.456-12", endereco, "10/10/2001", "1234567");//
		ICRUD.arr.add(fun3);
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		Funcionario fun4 = new Funcionario("Ana", "22222222222", endereco, "10/10/2001", "123456");//
		ICRUD.arr.add(fun4);
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		Gerente fun5 = new Gerente("Paula", "33333333333", endereco, "", "123321444");
		ICRUD.arr.add(fun5);
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		Entregador fun6 = new Entregador("Tirano", "11111111111", endereco, "10/10/2001", "123321444");
		ICRUD.arr.add(fun6);		
		System.out.println(crud.insertDados(ICRUD.arr));
		ICRUD.arr.clear();
		
		System.out.println(crud.selectDados());
		System.out.println(crud.selectFuncionario("FN0002"));
		
//		crud.deleteDados("FN0001");
//		System.out.println();
		
		

	}

}
