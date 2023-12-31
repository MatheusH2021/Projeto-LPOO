package Controle;
import java.util.ArrayList;
import java.util.Date;

import Entidades.Entregador;
import Entidades.Funcionario;
import Entidades.Gerente;
import Exceptions.CPFValidacaoException;
import Exceptions.EnderecoValidacaoException;
import Exceptions.ValidaCamposException;
import Exceptions.ValidaDeleteEntregadorException;
import Exceptions.ValidaTokenException;

public class CrudFuncionario implements ICRUD<Funcionario>{
	
	public static ArrayList<Funcionario> funcionarios;
	public static ArrayList<String> LogsAcoes;
	public static boolean actionSuccess;
	private Date Data;
	
	public CrudFuncionario() {
		funcionarios = new ArrayList<Funcionario>();
		LogsAcoes 	 = new ArrayList<String>();
		Data = new Date();
	}
	
	@Override
	public String insertDados(Funcionario dados) {
		Funcionario novoFuncionario = dados;
		try {
			ExceptionsHandling.CpfValidacao(novoFuncionario.getCPF());
			ExceptionsHandling.CampoVazio(novoFuncionario.getNome(), "Nome");
			ExceptionsHandling.CampoVazio(novoFuncionario.getSenha(), "Senha");
			ExceptionsHandling.ValidaEndereco(novoFuncionario.getEndereco());
			ExceptionsHandling.CampoVazio(novoFuncionario.getData_nascimento(), "Data de Nascimento");
		}catch(CPFValidacaoException e) {
			CrudFuncionario.actionSuccess = false;
			return "|*- Erro: "+e.getMessage();
		} catch(ValidaCamposException e) {
			CrudFuncionario.actionSuccess = false;
			return "|*- Erro: "+e.getMessage();
		} catch (EnderecoValidacaoException e) {
			CrudFuncionario.actionSuccess = false;
			return "|*- Erro: "+e.getMessage();
		}
		
		funcionarios.add(novoFuncionario);
		LogsAcoes.add("Novo Funcionario Inserido: Data->"+this.Data+" | Codigo do novo Funcionario->"+novoFuncionario.getCodFuncionario()+" | Gerente Responsavel->GR0001");
		
		/*- Define o sucesso da ação realizada */
		CrudFuncionario.actionSuccess = true;
		/*-------------------------------------*/
		
		return "|*- Funcionario Cadastrado com sucesso!";
	}

	@Override
	public String updateDados(Funcionario novosDados, String Token) {
		try {
			ExceptionsHandling.CampoVazio(novosDados.getSenha(), "Senha");
			ExceptionsHandling.ValidaToken(Token);
		}catch(ValidaCamposException e) {
			CrudFuncionario.actionSuccess = false;
			return "|*- Erro: "+e.getMessage();
		}catch(ValidaTokenException e) {
			CrudFuncionario.actionSuccess = false;
			return "|*- Erro: "+e.getMensagem();
		}
		for (Funcionario func : funcionarios) {
			if (func.getCodFuncionario().equals(novosDados.getCodFuncionario())) {
				func = novosDados;
				CrudFuncionario.actionSuccess = true;
				LogsAcoes.add("Edição de senha de funcionario: Data->"+this.Data+" | Codigo do Funcionario editado->"+novosDados.getCodFuncionario()+" | Gerente Responsavel->GR0001");
				return "|*- Senha de funcionario atualizada com sucesso!";
			}
		}
		CrudFuncionario.actionSuccess = true;
		return "|*- Erro ao atualizar senha de funcionario, tente novamente.";
	}

	@Override
	public String deleteDados(String codPesquisa, String Token) {
		
		try {
			ExceptionsHandling.ValidaToken(Token);
		}catch(ValidaTokenException e) {
			return "|*- "+e.getMensagem()+" Não Foi possivel deletar o funcionario!";
		}
		for (Funcionario funcionario : funcionarios) {
			if (funcionario instanceof Entregador) {
				Entregador ent = (Entregador) funcionario;
				try {
					ExceptionsHandling.ValidaDeleteEntregador(ent.getCodFuncionario());
				}catch(ValidaDeleteEntregadorException e) {
					return "Erro: "+e.getMensagem();
				}
			} 
			if (funcionario.getCodFuncionario().equals(codPesquisa)) {
				if (funcionario instanceof Gerente) {
					return "Erro: Não é possivel deletar usuarios do tipo gerente, caso esqueceu sua senha altere na opção 3 do menu.";
				}
				funcionarios.remove(funcionario);
				LogsAcoes.add("Funcionario Deletado: Data->"+this.Data+" | Codigo do Funcionario Deletado->"+codPesquisa+" | Gerente Responsavel->GR0001");
				return "|*- Funcionario deletado com sucesso!";
			}
		}
		return "|*- Funcionario não encontrado...";
	}

	@Override
	public String selectDados() {
		if (funcionarios.isEmpty()) {
			return "Sem Funcionarios Cadastrados";
		} else {
			String retorno = "-------------------------------------------------------------------------------------------------------------------------------\n";
			retorno += "Codigo Funcionario | Nome 		| CPF 		| Cargo 		| Data De nascimento | Senha do Funcionario   |";
			retorno += "\n-------------------------------------------------------------------------------------------------------------------------------";
			for (Funcionario funcionario : funcionarios) {
				if (funcionario instanceof Entregador) {
					retorno += "\n "+funcionario.getCodFuncionario()+"	   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Entregador		| "+funcionario.getData_nascimento()+"	     | "+funcionario.getSenha();
				} else if (funcionario instanceof Gerente) {
					retorno += "\n "+funcionario.getCodFuncionario()+"		   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Gerente		| "+funcionario.getData_nascimento()+"	     | "+funcionario.getSenha();				
				} else {
					retorno += "\n "+funcionario.getCodFuncionario()+"		   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Funcionario		| "+funcionario.getData_nascimento()+"	      | "+funcionario.getSenha();
				}
			}
			retorno += "\n-------------------------------------------------------------------------------------------------------------------------------";
			
			return retorno;
		}
		
	}
	public static String visualizaEntregadores() {
		if (funcionarios.isEmpty()) {
			return "Sem Funcionarios Cadastrados";
		} else {
			String retorno = "-------------------------------------------------------------------------------------------------------------------------------\n";
			retorno += "Codigo Funcionario | Nome 		| CPF 		| Cargo 		| Data De nascimento |";
			retorno += "\n-------------------------------------------------------------------------------------------------------------------------------";
			for (Funcionario funcionario : funcionarios) {
				if (funcionario instanceof Entregador) {
					retorno += "\n "+funcionario.getCodFuncionario()+"	   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Entregador		| "+funcionario.getData_nascimento()+"	     | ";
				}
			}
			retorno += "\n-------------------------------------------------------------------------------------------------------------------------------";
			
			return retorno;
		}
	}
	public Funcionario realizaLogin(String codFunc, String senha) {
		try {
			ExceptionsHandling.CampoVazio(codFunc, "Codigo de Funcionario");
			ExceptionsHandling.CampoVazio(senha, "Senha");
		}catch(ValidaCamposException e) {
			return null;
		}
		Funcionario logado = selectPorCodigo(codFunc);
		if (logado != null && logado.getSenha().equals(senha)) {
			return logado;
		}
		return null;
	}
	@Override
	public Funcionario selectPorCodigo(String codFun) {
		if (funcionarios.isEmpty()) {
			return null;
		} else {
			for (Funcionario funcionario : funcionarios) {
				if (funcionario.getCodFuncionario().equals(codFun)) {
					return funcionario;
				}
			}
		}
		return null;
		
	}

	@Override
	public String LogsAcoes() {
		String tabelaLogs = "|*-------------------------------------------------------------------------------------------------------------------------------\n";
		tabelaLogs += "|*- Lista de logs de ações realizadas no sistema\n";
		tabelaLogs += "|*-------------------------------------------------------------------------------------------------------------------------------\n";
		for (String logs : LogsAcoes) {
			tabelaLogs += "|*- "+logs+"\n";
		}
		tabelaLogs += "|*-------------------------------------------------------------------------------------------------------------------------------";
		return tabelaLogs;
	}

}
