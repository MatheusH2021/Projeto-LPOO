import java.util.ArrayList;
import java.util.Date;

import Exceptions.CPFValidacaoException;
import Exceptions.ValidaCamposException;
import Exceptions.ValidaTokenException;

public class CrudFuncionario implements ICRUD{
	
	protected static ArrayList<Funcionario> funcionarios;
	protected static ArrayList<String> LogsAcoes;
	protected static boolean actionSuccess;
	private Date Data;
	
	public CrudFuncionario() {
		funcionarios = new ArrayList<Funcionario>();
		LogsAcoes 	 = new ArrayList<String>();
		Data = new Date();
	}
	
	@Override
	public String insertDados(ArrayList<Object> dados) {
		Funcionario novoFuncionario = (Funcionario) dados.get(0);
		try {
			ExceptionsHandling.CpfValidacao(novoFuncionario.getCPF());
			ExceptionsHandling.CampoVazio(novoFuncionario.getNome(), "Nome");
			ExceptionsHandling.CampoVazio(novoFuncionario.getSenha(), "Senha");
			ExceptionsHandling.CampoVazio(novoFuncionario.getData_nascimento(), "Data de Nascimento");
		}catch(CPFValidacaoException e) {
			this.actionSuccess = false;
			return e.getMessage();
		} catch(ValidaCamposException e) {
			this.actionSuccess = false;
			return e.getMessage();
		}
		
		funcionarios.add(novoFuncionario);
		LogsAcoes.add("Novo Funcionario Inserido: Data->"+this.Data+" | Codigo do novo Funcionario->"+novoFuncionario.getCodFuncionario()+" | Gerente Responsavel->GR0001");
		/*- Define o sucesso da ação realizada */
		this.actionSuccess = true;
		return "Funcionario Cadastrado com sucesso!";
	}

	@Override
	public String updateDados(ArrayList<Object> novosDados) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDados(String codPesquisa, String Token) {
		
		try {
			ExceptionsHandling.ValidaToken(Token);
		}catch(ValidaTokenException e) {
			return e.getMensagem()+" Não Foi possivel deletar o funcionario!";
		}
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCodFuncionario().equals(codPesquisa)) {
				funcionarios.remove(funcionario);
				LogsAcoes.add("Funcionario Deletado: Data->"+this.Data+" | Codigo do Funcionario Deletado->"+codPesquisa+" | Gerente Responsavel->GR0001");
				return "Funcionario deletado com sucesso!";
			}
		}
		return "Erro ao deletar Funcionario!";
	}

	@Override
	public String selectDados() {
		if (funcionarios.isEmpty()) {
			return "Sem Funcionarios Cadastrados";
		} else {
			String retorno = "------------------------------------------------------------------------------------------------------\n";
			retorno += "Codigo Funcionario | Nome 		| CPF 		| Cargo 		| Data De nascimento |";
			retorno += "\n------------------------------------------------------------------------------------------------------";
			for (Funcionario funcionario : funcionarios) {
				if (funcionario instanceof Entregador) {
					retorno += "\n "+funcionario.getCodFuncionario()+"	   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Entregador		| "+funcionario.getData_nascimento()+"	     |";
				} else if (funcionario instanceof Gerente) {
					retorno += "\n "+funcionario.getCodFuncionario()+"		   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Gerente		| "+funcionario.getData_nascimento()+"	     |";				
				} else {
					retorno += "\n "+funcionario.getCodFuncionario()+"		   | "+funcionario.getNome()+"		| "+funcionario.getCPF()+"	| Funcionario		| "+funcionario.getData_nascimento()+"	     |";
				}
			}
			retorno += "\n------------------------------------------------------------------------------------------------------";
			
			return retorno;
		}
		
	}

	@Override
	public Funcionario selectFuncionario(String codFun) {
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

}
