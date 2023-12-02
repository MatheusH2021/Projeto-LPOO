package MainProjeto;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controle.CrudEncomenda;
import Controle.CrudFuncionario;
import Controle.ICRUD;
import Entidades.Encomenda;
import Entidades.Endereco;
import Entidades.Entregador;
import Entidades.Funcionario;
import Entidades.Gerente;

public class MainCorreio {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ICRUD<Funcionario>  crud = new CrudFuncionario();
		ICRUD<Encomenda> crudEnc = new CrudEncomenda();
		
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
				if (CrudFuncionario.funcionarios.isEmpty()) {
					boolean cont = true;
					System.out.println("Inicialização do sistema... Precione Enter para iniciar!");
					while(cont) {
						try {
							keyboard.nextLine();
							System.out.println("Para utilizar o sistema realize o cadastro do acesso para o gerente->");
							System.out.println("Após o cadastro, você poderá realizar o login com seu acesso de gerente e começar as atividades.");
							System.out.println();
							System.out.println("Etapa 1: Cadastro de informações base->");
							System.out.println();
							System.out.print("Informe seu nome: ");
							String nome = keyboard.nextLine();
							System.out.print("Informe seu CPF (Obs: Apenas numeros!): ");
							String CPF = keyboard.nextLine();
							System.out.print("Informe sua data de nascimento: ");
							String data_nascimento = keyboard.nextLine();
							System.out.print("Informe sua senha (Obs: no minimo 6 digitos): ");
							String senhaCad = keyboard.nextLine();
							System.out.println();
							System.out.println("Etapa 2: Cadastro de endereço->");
							System.out.println();
							System.out.print("Informe sua rua: ");
							String rua = keyboard.nextLine();
							System.out.print("Informe o Numero (Obs: Apenas Numeros!): ");
							int numero = keyboard.nextInt();
							System.out.print("Informe seu Bairro: ");
							String bairro = keyboard.nextLine();
							bairro = keyboard.nextLine();
							System.out.print("Informe sua cidade: ");
							String cidade = keyboard.nextLine();
							System.out.print("Informe seu estado (Obs: apenas as iniciais, EX: SP, PE, RJ): ");
							String estado = keyboard.nextLine().toUpperCase();
							System.out.print("Informe seu CEP (EX:XXXXX-XXX): ");
							String cep = keyboard.nextLine();
							System.out.println();
							
							Endereco end = new Endereco(rua, numero, bairro, cidade, estado, cep);
							Gerente grFirst = new Gerente(nome, CPF, end, data_nascimento, senhaCad);
							
							System.out.println(crud.insertDados(grFirst));
							
							if (CrudFuncionario.actionSuccess) {
								cont = false;	
								System.out.println("O seu acesso foi criado, para realizar o login utilize seu codigo->"+grFirst.getCodFuncionario()+", juntamente com sua senha!");
								System.out.println();
							} else {
								System.out.println("Pressione ENTER para retornar ao cadastro...");
								System.out.println();
							}
						
						} catch(NullPointerException e) {
							System.out.println(e.getMessage());
						} catch(IllegalArgumentException e) {
							System.out.println(e.getMessage());
						} catch (InputMismatchException e) {
							System.out.println("Caracter Inválido");
						} catch (Exception e) {
							System.out.println("Erro ao Cadastrar: "+e.getMessage());
						}
					}
				} else {
					System.out.println("|*- Sistema já inciado, Faça login para Acessar -*|");
				}
	 
			} else {
				Funcionario userLogin = ((CrudFuncionario) crud).realizaLogin(codFuncionario, senha);
				
				if (userLogin != null) {
					if (userLogin instanceof Gerente) {
						Gerente atualLogado = (Gerente) userLogin;
						userLogin = null;
						
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Bem Vindo Gerente "+atualLogado.getNome()+"                                   -*|");
						System.out.println("|*- No Modulo de Gerente, você possui as seguintes funções      -*|");
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Gerenciamento de Funcionarios (Cadastro, Edição e Exclusão) -*|");
						System.out.println("|*- Realtorio de entregas                                       -*|");
						System.out.println("|*- Consulta do Token de Acesso                                 -*|");
						System.out.println("|*---------------------------------------------------------------*|");
						
					} else if (userLogin instanceof Entregador) {
						Entregador atualLogado = (Entregador) userLogin;
						userLogin = null;
						
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Bem Vindo Entregador "+atualLogado.getNome()+"                             -*|");
						System.out.println("|*- No Modulo de Entrefador, você possui as seguintes funções   -*|");
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Gerenciamento de Entregas (Atualizar Status, Concluir)      -*|");
						System.out.println("|*- Visualizar lista de entregas                                -*|");
						System.out.println("|*---------------------------------------------------------------*|");

					} else {
						Funcionario atualLogado = userLogin;
						userLogin = null;
						
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Bem Vindo Funcionario "+atualLogado.getNome()+"                           -*|");
						System.out.println("|*- No Modulo de Funcionario, você possui as seguintes funções  -*|");
						System.out.println("|*---------------------------------------------------------------*|");
						System.out.println("|*- Cadastro de encomendas                                      -*|");
						System.out.println("|*- Atribuição de entregas a entregador                         -*|");
						System.out.println("|*- Gerenciamento de Encomendas                                 -*|");
						System.out.println("|*---------------------------------------------------------------*|");
					}
					
				} else {
					System.out.println("Login não encontrado!");
				}
				
			}
		}		
	}

}