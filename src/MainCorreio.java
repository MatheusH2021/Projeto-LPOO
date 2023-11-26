import java.util.Scanner;

public class MainCorreio {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ICRUD crud = new CrudFuncionario();
		
		while(true){
			
			System.out.println("|*------------------------------------------------------*|");
			System.out.println("|*- Bem Vindo ao Sistema de Gerenciamento dos Correios -*|");
			System.out.println("|*-                    Faça Login                      -*|");
			System.out.println("|*------------------------------------------------------*|");
			if (CrudFuncionario.funcionarios.size() == 0) {
				System.out.println("|*- OBS: Se for seu primeiro acesso, utilizar o login admin com senha admin para inicializar o sistema!");								
			}
			System.out.print("|*- Codigo de Funcionario: ");
			String codFuncionario = keyboard.nextLine();
			System.out.print("|*- Senha: ");
			String senha = keyboard.nextLine();
			System.out.println("|*------------------------------------------------------*|");
			
			if (codFuncionario.equals("admin") && senha.equals("admin")){
				boolean cont = true;
				System.out.println("Inicialização do sistema...");
				while(cont) {
					Endereco end = new Endereco("rua", 1, "bairro", "cidade", "estado", "cep");
					System.out.println("Para utilizar o sistema realize o cadastro do acesso para o gerente->");
					System.out.println("Após o cadastro, você poderá realizar o login com seu acesso de gerente e começar as atividades.");
					System.out.print("Informe seu nome: ");
					String nome = keyboard.nextLine();
					System.out.print("Informe seu CPF: ");
					String CPF = keyboard.nextLine();
					System.out.println("Informe sua data de nascimento: ");
					String data_nascimento = keyboard.nextLine();
					System.out.println("Informe sua senha: ");
					String senhaCad = keyboard.nextLine();
					Gerente grFirst = new Gerente(nome, CPF, end, data_nascimento, senhaCad);
					ICRUD.arr.add(grFirst);
					System.out.println(crud.insertDados(ICRUD.arr));
					if (CrudFuncionario.actionSuccess) {
						cont = false;
						System.out.println("O seu acesso foi criado, para realizar o login utilize seu codigo->"+grFirst.getCodFuncionario()+", jutnamente com sua senha!");
						System.out.println();
					}
					ICRUD.arr.clear();					
				}
				
			}
		}
		
		
	}

}
