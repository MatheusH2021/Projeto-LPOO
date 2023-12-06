package MainProjeto;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controle.CrudEncomenda;
import Controle.CrudFuncionario;
import Controle.ICRUD;
import Entidades.Cliente;
import Entidades.Correspondencia;
import Entidades.Encomenda;
import Entidades.Endereco;
import Entidades.Entregador;
import Entidades.Entregas;
import Entidades.Funcionario;
import Entidades.Gerente;
import Entidades.Produto;

public class MainCorreio {

	public static void main(String[] args) throws InterruptedException {
		Scanner keyboard = new Scanner(System.in);
		ICRUD<Funcionario>  crud = new CrudFuncionario();
		ICRUD<Encomenda> crudEnc = new CrudEncomenda();
		boolean cont = true;
		
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
							System.out.print("Informe sua data de nascimento (Obs: Siga o modelo: dd/mm/AAAA, com as barras): ");
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
							System.out.print("Informe seu CEP (EX:XXXXX-XXX, Insira o traço junto): ");
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
							System.out.println("|*- [1]- Cadastrar Funcionario        ->                         -|");
							System.out.println("|*- [2]- Consultar Funcionarios       ->                         -|");
							System.out.println("|*- [3]- Alterar senha do Funcionario ->                         -|");
							System.out.println("|*- [4]- Deletar Funcionario          ->                         -|");
							System.out.println("|*- [5]- Consultar Token de Acesso    ->                         -|");
							System.out.println("|*- [6]- Logs de Ações do Sistema     ->                         -|");
							System.out.println("|*- [7]- Encerrar Sessão              ->                         -|");
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
												System.out.print("|*- Informe a data de nascimento do funcionario (Obs: Siga o modelo: dd/mm/AAAA, com as barras): ");
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
												System.out.print("|*- Informe o CEP do Funcionario (EX: XXXXX-XXX, Inclua o traço): ");
												String cep = keyboard.nextLine();
												Endereco end = new Endereco(rua, numero, bairro, cidade, estado, cep);
												System.out.println();
												System.out.print("|*- Qual será o cargo do novo Funcionario: \n|*- [1]- Entregador \n|*- [2]- Funcionario \n|*- Sua Escolha: ");
												int opc = keyboard.nextInt();
												if (opc == 1) {
													ent = new Entregador(nome, CPF, end, data_nascimento, senhaCad);
													System.out.println(crud.insertDados(ent));													
												} else if (opc == 2) {
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
										System.out.println();
										System.out.println("|*- Funcionarios Cadastrados no sistema->");
										System.out.println(crud.selectDados());
										System.out.println();
										Thread.sleep(5000);
										break;
									case 3:
										while(cont_form) {
											try {
												System.out.println("|*- Alterar Senha de funcionario");
												System.out.println("|*- Será exigido seu token de acesso, para acessalo use a opção [5] do menu!");
												keyboard.nextLine();
												System.out.print("|*- Informe o codigo de registro do funcionario: ");
												String codRegistro = keyboard.nextLine();
												Funcionario upFunc = (Funcionario) crud.selectPorCodigo(codRegistro);
												
												if (upFunc != null) {
													System.out.print("|*- Informe a nova senha do funcionario: ");
													String novaSenha = keyboard.nextLine();
													System.out.print("|*- Informe seu token: ");
													String token = keyboard.nextLine();
													upFunc.setSenha(novaSenha);
													System.out.println();
													System.out.println(crud.updateDados(upFunc, token));
													if (CrudFuncionario.actionSuccess) {
														cont_form = false;
														System.out.println();
														Thread.sleep(1000);
													} else {
														System.out.println("|*- Não foi possivel alterar a senha do funcionario, aperte ENTER e tente novamente!");
													}
												} else {
													System.out.println("|*- Funcionario não encontrado!");
													cont_form = false;
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
									case 4:
										keyboard.nextLine();
										System.out.println("|*- Deletar Funcionario");
										System.out.println("|*- Será solicitado seu token de acesso, para acessa-lo use a opção [5] do menu!");
										while(cont_form) {
											try {
												System.out.print("Informe o codigo de cadastro do funcionario: ");
												String codFN = keyboard.nextLine();
												System.out.println("Confiram exclusão? \n[1]-Sim \n[2]- Não");
												switch(keyboard.nextInt()) {
													case 1:
														keyboard.nextLine();
														System.out.print("Informe seu token de acesso: ");
														String token = keyboard.nextLine();
														System.out.println(crud.deleteDados(codFN, token));
														cont_form = false;
														Thread.sleep(2000);
														break;
													case 2:
														System.out.println("Processo encerrado...");
														cont_form = false;
														Thread.sleep(2000);
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
										break;
									case 5:
										System.out.println("|*- Token de Acesso");
										System.out.println("|*- O token serve para realizar atividades criticas, como deletar um funcionario.");
										System.out.println("|*- Seu Token: "+atualLogado.getTokenAcesso());
										Thread.sleep(2000);
										break;
									case 6:
										System.out.println(crud.LogsAcoes());
										Thread.sleep(2000);
										break;
									case 7:
										cont = false;
										System.out.println("|*- Realiando Logout...!");
										atualLogado = null;
										Thread.sleep(1000);
										break;
									default:
										System.out.println("|*- Opção inválida");
										cont = false;
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
							} catch(Exception e) {
								System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
							}
						}
						
					} else if (userLogin instanceof Entregador) {
						Entregador atualLogado = (Entregador) userLogin;
						userLogin = null;
						cont = true;
						
						while(cont) {
							boolean cont_form = true;
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
										while(cont_form) {
											System.out.println("|*- Gerenciamento de Entregas-> ");
											try {
												keyboard.nextLine();
												System.out.print("|*- Informe o codigo da encomenda que deseja alterar: ");
												String codEntr = keyboard.nextLine();
												System.out.println("|*- Esolha o novo status para a entraga escolhida \n|*- [1]- Entregue \n|*- [2]- Entrega Cancelada \n|*- Sua Escolha: ");
												try {
													switch(keyboard.nextInt()) {
													case 1:
														System.out.println(atualLogado.gerenciarEntrega("Entregue", codEntr));
														cont_form = false;
														break;
													case 2:
														System.out.println(atualLogado.gerenciarEntrega("Entrega Cancelada", codEntr));
														cont_form = false;													
														break;													
													}
												}catch(IllegalArgumentException e) {
													System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
													break;
												} catch (InputMismatchException e) {
													System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
													break;
												} catch(Exception e) {
													System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
													break;
												}
											}catch(IllegalArgumentException e) {
												System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
												break;
											} catch (InputMismatchException e) {
												System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
												break;
											} catch(Exception e) {
												System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
												break;
											}
										}
										Thread.sleep(2000);
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
									default:
										System.out.println("|*- Opção inválida");
										cont = false;
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
							} catch(Exception e) {
								System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
							}
						}
						
					} else {
						Funcionario atualLogado = userLogin;
						userLogin = null;
						cont = true;
												
						while(cont) {
							boolean cont_form = true;
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Bem Vindo Funcionario "+atualLogado.getNome()+"                           -*|");
							System.out.println("|*- No Modulo de Funcionario, você possui as seguintes funções  -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*- Cadastro de encomendas                                      -*|");
							System.out.println("|*- Atribuição de entregas a entregador                         -*|");
							System.out.println("|*- Visualizar entregadores                                     -*|");
							System.out.println("|*- Visualizar de Encomendas                                    -*|");
							System.out.println("|*- Visualizar Lista de Entregas                                -*|");
							System.out.println("|*---------------------------------------------------------------*|");
							System.out.println("|*--------------------- Escolha uma opção ------------------------|");
							System.out.println("|*- [1]- Cadastrar Encomenda             ->                      -|");
							System.out.println("|*- [2]- Atribuir Encomenda a entregador ->                      -|");
							System.out.println("|*- [3]- Visualizar Entregadores         ->                      -|");
							System.out.println("|*- [4]- Visualizar Encomendas           ->                      -|");
							System.out.println("|*- [5]- Deleter Encomenda");
							System.out.println("|*- [6]- Visualizar Lista de Entregas    ->                      -|");
							System.out.println("|*- [7]- Encerrar Sessão                 ->                      -|");
							System.out.println("|*----------------------------------------------------------------|");
							System.out.println("|*- Pressione enter->");
							keyboard.nextLine();
							System.out.print("|*- Sua Escolha: ");
							try {
								int opc = keyboard.nextInt(); 
								switch(opc) {
									case 1:
										while(cont_form) {
											System.out.println("|*--------------------- Cadastro de Encomenda ------------------------|");
											try {
												System.out.println("|*- Qual o tipo de encomenda que deseja cadastrar? ");
												System.out.println("|*- [1]- Correspondencia \n|*- [2]- Produto");
												Date dataPostagem = new Date();
												try {
													switch(keyboard.nextInt()) {
														case 1:
															keyboard.nextLine();
															System.out.println("|*- Cadastro do remetente->");
															System.out.print("|*- Informe o nome do Remetente: ");
															String nomeRem = keyboard.nextLine();
															System.out.print("|*- Informe o CPF do Remetente: ");
															String cpfRem = keyboard.nextLine();
															System.out.print("|*- Informe a Rua do remetente: ");
															String ruaRem = keyboard.nextLine();
															System.out.print("|*- Informe o numero da casa do remetente (Informe apenas numeros): ");
															int numRem = keyboard.nextInt();
															System.out.print("|*- Informe o Bairro do remetente: ");
															String bairroRem = keyboard.nextLine();
															bairroRem = keyboard.nextLine();
															System.out.print("|*- Informe a cidade do remetente: ");
															String cidadeRem = keyboard.nextLine();
															System.out.print("|*- Informe o estado do remetente (Obs: apenas a sigla do estado, EX: SP, PE, RJ): ");
															String estadoRem = keyboard.nextLine();
															System.out.print("|*- Informe o CEP do remetente (EX: XXXXX-XX, Inclua o traço)");
															String cepRem = keyboard.nextLine();
															System.out.println();
															System.out.println("|*- Cadastro do Destinatário->");
															System.out.print("|*- Informe o nome do Destinatário: ");
															String nomeDes = keyboard.nextLine();
															System.out.print("|*- Informe o CPF do Destinatário: ");
															String cpfDes = keyboard.nextLine();
															System.out.print("|*- Informe a Rua do Destinatário: ");
															String ruaDes = keyboard.nextLine();
															System.out.print("|*- Informe o numero da casa do Destinatário (Informe apenas números): ");
															int numDes = keyboard.nextInt();
															System.out.print("|*- Informe o Bairro do Destinatário: ");
															String bairroDes = keyboard.nextLine();
															bairroDes = keyboard.nextLine();
															System.out.print("|*- Informe a cidade do Destinatário: ");
															String cidadeDes = keyboard.nextLine();
															System.out.print("|*- Informe o estado do Destinatário (Obs: apenas a sigla do estado, EX: SP, PE, RJ): ");
															String estadoDes = keyboard.nextLine();
															System.out.print("|*- Informe o CEP do Destinatário (EX: XXXXX-XXX, Inclua o traço): ");
															String cepDes = keyboard.nextLine();
															System.out.println();
															System.out.println("|*- Informe o tipo de correspondencia (EX: Carta, Convite, Documento): ");
															String tipoCorr = keyboard.nextLine();
															
															Endereco endRem = new Endereco(ruaRem, numRem, bairroRem, cidadeRem, estadoRem, cepRem);
															Cliente remetente = new Cliente(nomeRem, cpfRem, endRem);
															
															Endereco endDes = new Endereco(ruaDes, numDes, bairroDes, cidadeDes, estadoDes, cepDes);
															Cliente destinatario = new Cliente(nomeDes, cpfDes, endDes);
															Correspondencia encomenda = new Correspondencia(destinatario, remetente, dataPostagem, tipoCorr);
															
															System.out.println(crudEnc.insertDados(encomenda));
															
															if (CrudEncomenda.acaoSucesso) {
																cont_form = false;
																System.out.println();
																Thread.sleep(2000);
															} else {
																System.out.println();
																Thread.sleep(2000);
															}
															
															System.out.println(crudEnc.selectDados());
															break;
														case 2:
															keyboard.nextLine();
															System.out.println("|*- Cadastro do remetente->");
															System.out.print("|*- Informe o nome do Remetente: ");
															nomeRem = keyboard.nextLine();
															System.out.print("|*- Informe o CPF do Remetente: ");
															cpfRem = keyboard.nextLine();
															System.out.print("|*- Informe a Rua do remetente: ");
															ruaRem = keyboard.nextLine();
															System.out.print("|*- Informe o numero da casa do remetente (Obs: Informe apenas numeros): ");
															numRem = keyboard.nextInt();
															System.out.print("|*- Informe o Bairro do remetente: ");
															bairroRem = keyboard.nextLine();
															bairroRem = keyboard.nextLine();
															System.out.print("|*- Informe a cidade do remetente: ");
															cidadeRem = keyboard.nextLine();
															System.out.print("|*- Informe o estado do remetente (Obs: apenas a sigla do estado, EX: SP, PE, RJ): ");
															estadoRem = keyboard.nextLine();
															System.out.print("|*- Informe o CEP do remetente (EX: XXXXX-XXX, Inclua o traço)");
															cepRem = keyboard.nextLine();
															System.out.println();
															System.out.println("|*- Cadastro do Destinatário->");
															System.out.print("|*- Informe o nome do Destinatário: ");
															nomeDes = keyboard.nextLine();
															System.out.print("|*- Informe o CPF do Destinatário: ");
															cpfDes = keyboard.nextLine();
															System.out.print("|*- Informe a Rua do Destinatário: ");
															ruaDes = keyboard.nextLine();
															System.out.print("|*- Informe o numero da casa do Destinatário (Obs: Informe apenas numeros): ");
															numDes = keyboard.nextInt();
															System.out.print("|*- Informe o Bairro do Destinatário: ");
															bairroDes = keyboard.nextLine();
															bairroDes = keyboard.nextLine();
															System.out.print("|*- Informe a cidade do Destinatário: ");
															cidadeDes = keyboard.nextLine();
															System.out.print("|*- Informe o estado do Destinatário (Obs: apenas a sigla do estado, EX: SP, PE, RJ): ");
															estadoDes = keyboard.nextLine();
															System.out.print("|*- Informe o CEP do Destinatário (EX: XXXXX-XXX, Inclua o traço)");
															cepDes = keyboard.nextLine();
															System.out.println();
															System.out.print("|*- Informe o peso do produto a ser enviado: ");
															double peso = keyboard.nextDouble();
															System.out.print("|*- O produto a ser enviado é frágil: \n|*- [1]- sim \n|*- [2]- não \n|*- Sua escolha: ");
															int opc2 = keyboard.nextInt();
															boolean eFragil;
															
															if (opc2 == 1) {
																eFragil = true;
															} else if (opc2 == 2) {
																eFragil = false;
															} else {
																eFragil = false;
															}
															
															endRem = new Endereco(ruaRem, numRem, bairroRem, cidadeRem, estadoRem, cepRem);
															remetente = new Cliente(nomeRem, cpfRem, endRem);
															
															endDes = new Endereco(ruaDes, numDes, bairroDes, cidadeDes, estadoDes, cepDes);
															destinatario = new Cliente(nomeDes, cpfDes, endDes);
															Produto encomendaProd = new Produto(destinatario, remetente, dataPostagem, peso, eFragil);
															
															System.out.println(crudEnc.insertDados(encomendaProd));
															
															if (CrudEncomenda.acaoSucesso) {
																cont_form = false;
																System.out.println();
																Thread.sleep(2000);
															} else {
																System.out.println();
																Thread.sleep(2000);
															}
															System.out.println(crudEnc.selectDados());
															Thread.sleep(2000);
															break;
													}
												} catch(IllegalArgumentException e) {
													cont_form = false;
													System.out.println(e.getMessage());
													break;
												} catch (InputMismatchException e) {
													cont_form = false;
													System.out.println("Caracter Inválido");
													break;
												} catch (Exception e) {
													cont_form = false;
													System.out.println("Erro ao Cadastrar: Tente Novamente!");
													break;
												}
											}catch(NullPointerException e) {
												cont_form = false;
												cont = false;
												System.out.println(e.getMessage());
												break;
											} catch(IllegalArgumentException e) {
												cont_form = false;
												cont = false;
												System.out.println(e.getMessage());
												break;
											} catch (InputMismatchException e) {
												cont_form = false;
												cont = false;
												System.out.println("Caracter Inválido");
												break;
											} catch (Exception e) {
												cont_form = false;
												cont = false;
												System.out.println("Erro ao Cadastrar: Tente Novamente!");
												break;
											}
										}
										break;
									case 2:
										while(cont_form) {
											System.out.println("|*- Atribuir Entrega a Entregador->");
											try {
												keyboard.nextLine();
												Encomenda enc = null;
												Entregador ent= null;
												System.out.print("Informe o codigo da Encomenda a ser entrege: ");
												String codEnc = keyboard.nextLine();
												if (crudEnc.selectPorCodigo(codEnc) == null) {
													System.out.println("|*- Encomenda não encontrada...");
													cont_form = false;
													break;
												} else {
													enc = (Encomenda) crudEnc.selectPorCodigo(codEnc);
												}
												System.out.print("Informe o codigo do Entregador que será responsável: ");
												String codEnt = keyboard.nextLine();
												if (crud.selectPorCodigo(codEnt) == null) {
													System.out.println("|*- Entregador não encontrado...");
													cont_form = false;
													break;
												} else {
													ent = (Entregador) crud.selectPorCodigo(codEnt);
												}
												
												boolean atribuido = CrudEncomenda.atribuirEntregador(ent, enc);
												if (atribuido) {
													System.out.println("|*- Encomenda Atribuida com sucesso!");
													crud.updateDados(ent, "Att entrega");
													cont_form = false;
												} else {
													System.out.println("|*- Erro ao atribiuir encomenda, Encomenda já consta na lista de entregas...");
												}
											} catch(IllegalArgumentException e) {
												System.out.println(e.getMessage());
												cont_form = false;
												break;
											} catch (InputMismatchException e) {
												cont_form = false;
												System.out.println("Caracter Inválido");
												break;
											} catch (Exception e) {
												cont_form = false;
												System.out.println("Erro ao Cadastrar: Tente Novamente!");
												break;
											}
										}
										Thread.sleep(2000);
										break;
									case 3:
										System.out.println("|*- Visualizando entregadores.");
										System.out.println(CrudFuncionario.visualizaEntregadores());
										Thread.sleep(2000);
										break;
									case 4:
										System.out.println("|*- Listando Encomendas->");
										System.out.println(crudEnc.selectDados());
										break;
									case 5:
										keyboard.nextLine();
										System.out.println("|*- Deletar Encomenda");
										while(cont_form) {
											try {
												System.out.print("Informe o codigo da encomenda: ");
												String codFN = keyboard.nextLine();
												System.out.println("Confiram exclusão? \n[1]-Sim \n[2]- Não");
												switch(keyboard.nextInt()) {
													case 1:
														keyboard.nextLine();
														System.out.println(crudEnc.deleteDados(codFN, ""));
														cont_form = false;
														Thread.sleep(2000);
														break;
													case 2:
														System.out.println("Processo encerrado...");
														cont_form = false;
														Thread.sleep(2000);
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
										break;
									case 6:
										System.out.println("|*- Visualizando Lista de Entregas->");
										for (Entregas entrega : CrudEncomenda.entregas) {
											System.out.println("|*---------------------------------------------------------------------------------");
											System.out.println("\n|*- Cod Entrega            -> "+entrega.getCodEntrega());
											System.out.println("\n|*- Status Entrega         -> "+entrega.getStatus());
											System.out.println("\n|*- Cod Entregador         -> "+entrega.getCodEntregador());
											Entregador ent = (Entregador) crud.selectPorCodigo(entrega.getCodEntregador());
											System.out.println("\n|*- Entregador Responsalve -> "+ent.getNome());
											System.out.println("\n|*- Endereco de entrega    -> "+entrega.getEncomenda().getDestinatario().getEndereco());
											System.out.println("\n|*- Destinatario           -> "+entrega.getEncomenda().getDestinatario().getNome());
											System.out.println("\n|*- Remetente              -> "+entrega.getEncomenda().getRemetente().getNome());
											System.out.println("\n/*---------------------------------------------------------------------------------");
										}
										Thread.sleep(2000);
										break;
									case 7:
										cont = false;
										System.out.println("|*- Realiando Logout...!");
										atualLogado = null;
										Thread.sleep(1000);
										break;
									default:
										System.out.println("|*- Opção inválida");
										cont = false;
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
							} catch(Exception e) {
								System.out.println("|*- Caracter Inválido, digite apenas números! Sessão Encerrada.");
								break;
							}
							
						}
					}
					
				} else {
					System.out.println("|*------- Login não encontrado, Tente Novamente! -------*|");
				}
				
			}
		}		
	}

}
