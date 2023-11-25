import java.util.ArrayList;

import Exceptions.CPFValidacaoException;
import Exceptions.ValidaCamposException;
import Exceptions.ValidaTokenException;

public class ExceptionsHandling {
	
	ArrayList<String> LogErros;

	public ExceptionsHandling() {
		LogErros = new ArrayList<String>();
	}
	
	public static void CpfValidacao (String CPF) throws CPFValidacaoException {
		if (CPF.isEmpty()) {
			throw new CPFValidacaoException("O CPF não pode ser vazio");
		} else if (CPF.length() < 11 || CPF.length() > 11) {
			throw new CPFValidacaoException("O CPF deve possuir 11 digitos! EX: XXX.XXX.XXX-XX | OBS: Informe apenas numeros.");
		} else if (!CPF.matches("^\\d+$")) {
			throw new CPFValidacaoException("Informe apenas Numeros no campo de CPF!");
		} else if (ExisteCPF(CPF)) {
			throw new CPFValidacaoException("O CPF Informado já está cadastrado, informe um novo cpf!");
		}
	}
	
	public static void CampoVazio(String campo, String nomeCampo) throws ValidaCamposException {
		if (campo.isEmpty()) {
			throw new ValidaCamposException("Informe a informação pedida no campo para poder realizar o cadastro! Campo a ser preenchido->"+nomeCampo);
		} else if (campo.length() < 6 && nomeCampo.equals("Senha")) {
			throw new ValidaCamposException("Quantidade de caracteres insulficiente, a quantidade minima a ser informada é *6*! Campo a ser preenchido->"+nomeCampo);
		}
	}
	
	public static void ValidaToken(String token) throws ValidaTokenException {
		boolean notFound = true;
		for (Funcionario funcionario : CrudFuncionario.funcionarios) {
			if (funcionario instanceof Gerente) {
				Gerente gerente = (Gerente) funcionario;
				if (gerente.getTokenAcesso().equals(token)) {
					notFound = false;
				}
			}
		}
		if (notFound) {
			throw new ValidaTokenException();
		}
	}
	
	
	private static boolean ExisteCPF(String CPF) {
		for (Funcionario funcionario : CrudFuncionario.funcionarios) {
			if (funcionario.getCPF().equals(CPF)) {
				return true;
			}
		}
		return false;
	}
	
}
