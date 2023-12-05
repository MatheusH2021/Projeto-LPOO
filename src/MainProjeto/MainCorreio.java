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

	public static void main(String[] args) throws InterruptedException {
		Scanner keyboard = new Scanner(System.in);
		ICRUD<Funcionario>  crud = new CrudFuncionario();
		ICRUD<Encomenda> crudEnc = new CrudEncomenda();
		boolean cont = true;
		
		Endereco endereco = new Endereco("rua", 1, "bairro", "cidade", "PE", "55294-200");
		Gerente fun5 = new Gerente("Paula", "33333333333", endereco, "10/10/2001", "123456");
		System.out.println(crud.insertDados(fun5));
		while(true){			
			System.out.println("|*------------------------------------------------------*|");
			System.out.println("|*- Bem Vindo ao Sistema de Gerenciamento dos Correios -*|");
			System.out.println("|*-          Pressione enter para Fazer Login          -*|");
			System.out.println("|*------------------------------------------------------*|");
			keyboard.nextLine();
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
					cont = true;
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
					System.out.println("|*---- Sistema já inciado, Faça login para Acessar -----*|");
				}
	 
			} else {
				Funcionario userLogin = ((CrudFuncionario) crud).realizaLogin(codFuncionario, senha);
				
				if (userLogin != null) {
					if (userLogin instanceof Gerente) {
						Gerente atualLogado = (Gerente) userLogin;
						userLogin = null;
						cont = true;
						while(cont) {							
							boolean cont_form = true;
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Bem Vindo Gerente "+atualLogado.getNome()+"                                   -*|");
							System.out.println("|*- No Modulo de Gerente, você possui as seguintes funções      -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Gerenciamento de Funcionarios (Cadastro, Edição e Exclusão) -*|");
							System.out.println("|*- Realtorio de entregas                                       -*|");
							System.out.println("|*- Consulta do Token de Acesso                                 -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*--------------------- Escolha uma opção ------------------------|");
							System.out.println("|*- [1]- Cadastrar Funcionario     ->                            -|");
							System.out.println("|*- [2]- Relatório de entregas     ->                            -|");
							System.out.println("|*- [3]- Consultar Funcionarios    ->                            -|");
							System.out.println("|*- [4]- Consultar Token de Acesso ->                            -|");
							System.out.println("|*- [5]- Logs de Ações do Sistema  ->                            -|");
							System.out.println("|*- [6]- Encerrar Sessão           ->                            -|");
							System.out.println("|*----------------------------------------------------------------|");
							System.out.print("|*- Sua Escolha: ");									
						
							try{
								switch(keyboard.nextInt()) {
									case 1:
										Entregador ent;
										Funcionario func;
										while(cont_form) {
											System.out.println("|*--------------------- Cadastro de Funcionario ------------------------|");
											try {
												keyboard.nextLine();
												System.out.println("|*-               Primeira etapa: Informações Basicas                  -|");
												System.out.print("|*- Informe o nome do funcionario: ");
												String nome = keyboard.nextLine();
												System.out.print("|*- Informe o CPF do funcionario: ");
												String CPF = keyboard.nextLine();
												System.out.print("|*- Informe a senha de acesso do funcionario: ");
												String senhaCad = keyboard.nextLine();
												System.out.print("|*- Informe a data de nasciment do funcionario: ");
												String data_nascimento = keyboard.nextLine();
												System.out.println("|*-              Segunda etapa: Enderecço                              -|");
												System.out.print("|*- Informe a rua do Funcionario: ");
												String rua = keyboard.nextLine();
												System.out.print("|*- Informe o numero da casa do Funcionario (Obs: Apenas Numeros!): ");
												int numero = keyboard.nextInt();
												System.out.print("|*- Informe o Bairro do Funcionario: ");
												String bairro = keyboard.nextLine();
												bairro = keyboard.nextLine();
												System.out.print("|*- Informe a cidade do Funcionario: ");
												String cidade = keyboard.nextLine();
												System.out.print("|*- Informe o estado do Funcionario (Obs: apenas a sigla do estado, EX: SP, PE, RJ): ");
												String estado = keyboard.nextLine().toUpperCase();
												System.out.print("|*- Informe o CEP do Funcionario (EX:XXXXX-XXX): ");
												String cep = keyboard.nextLine();
												Endereco end = new Endereco(rua, numero, bairro, cidade, estado, cep);
												System.out.println();
												System.out.print("|*- Qual será o cargo do novo Funcionario: \n[1]- Entregador \n[2]- Funcionario \nSua Escolha: ");
												if (keyboard.nextInt() == 1) {
													ent = new Entregador(nome, CPF, end, data_nascimento, senhaCad);
													System.out.println(crud.insertDados(ent));													
												} else if (keyboard.nextInt() == 2) {
													func = new Funcionario(nome, CPF, end, data_nascimento, senhaCad);
													System.out.println(crud.insertDados(func));																									
												} else {
													System.out.println("|*- Opção inválida");
													break;	
												}
												
												if (CrudFuncionario.actionSuccess) {
													cont_form = false;	
													System.out.println();
												} else {
													System.out.println("Pressione ENTER para retornar ao cadastro...");
													System.out.println();
												}
											}catch(NullPointerException e) {
												System.out.println(e.getMessage());
											} catch(IllegalArgumentException e) {
												System.out.println(e.getMessage());
											} catch (InputMismatchException e) {
												System.out.println("Caracter Inválido");
											} catch (Exception e) {
												System.out.println("Erro ao Cadastrar: Tente Novamente!");
											}
										}
										break;
									case 2:
										System.out.println("Relatório de Entregas!");
										break;
									case 3:
										System.out.println();
										System.out.println("|*- Funcionarios Cadastrados no sistema->");
										System.out.println(crud.selectDados());
										System.out.println();
										Thread.sleep(5000);
										break;
									case 4:
										System.out.println("|*- Token de Acesso");
										System.out.println("|*- O token serve para realizar atividades criticas, como deletar um funcionario.");
										System.out.println("|*- Seu Token: "+atualLogado.getTokenAcesso());
										Thread.sleep(2000);
										break;
									case 5:
										System.out.println(crud.LogsAcoes());
										Thread.sleep(2000);
										break;
									case 6:
										cont = false;
										System.out.println("|*- Realiando Logout...!");
										atualLogado = null;
										Thread.sleep(1000);
										break;
								}
							}catch(IllegalArgumentException e) {
								System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
								break;
							} catch (InputMismatchException e) {
								System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
								break;
							}
						}
						
					} else if (userLogin instanceof Entregador) {
						Entregador atualLogado = (Entregador) userLogin;
						userLogin = null;
						cont = true;
						boolean cont_form = true;
						
						while(cont) {
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Bem Vindo Entregador "+atualLogado.getNome()+"                             -*|");
							System.out.println("|*- No Modulo de Entregador, você possui as seguintes funções   -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Gerenciamento de Entregas (Atualizar Status, Concluir)      -*|");
							System.out.println("|*- Visualizar lista de entregas                                -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*--------------------- Escolha uma opção ------------------------|");
							System.out.println("|*- [1]- Gerenciar suas entregas      ->                         -|");
							System.out.println("|*- [2]- Visualizar lista de entregas ->                         -|");
							System.out.println("|*- [3]- Encerrar Sessão              ->                         -|");
							System.out.println("|*----------------------------------------------------------------|");
							System.out.print("|*- Sua Escolha: ");				
							try {
								switch(keyboard.nextInt()) {
									case 1:
										System.out.println("Gerenciando entregas");
										break;
									case 2:
										System.out.println(atualLogado.visualizarEntregas());
										Thread.sleep(2000);
										break;
									case 3:
										cont = false;
										System.out.println("|*- Realiando Logout...!");
										atualLogado = null;
										Thread.sleep(1000);
										break;
								}
							}catch(NullPointerException e) {
								System.out.println(e.getMessage());
							} catch(IllegalArgumentException e) {
								System.out.println(e.getMessage());
							} catch (InputMismatchException e) {
								System.out.println("Caracter Inválido");
							} catch (Exception e) {
								System.out.println("Erro ao Cadastrar: Tente Novamente!");
							}
						}
						
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
					System.out.println("|*------- Login não encontrado, Tente Novamente! -------*|");
				}
				
			}
		}		
	}

}
