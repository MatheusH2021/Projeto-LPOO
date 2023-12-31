package Controle;

import Entidades.Endereco;
import Entidades.Entregas;
import Entidades.Funcionario;
import Entidades.Gerente;
import Exceptions.CPFValidacaoException;
import Exceptions.EnderecoValidacaoException;
import Exceptions.ValidaCamposException;
import Exceptions.ValidaDeleteEncomendaException;
import Exceptions.ValidaDeleteEntregadorException;
import Exceptions.ValidaEntregaException;
import Exceptions.ValidaPesoProdutoException;
import Exceptions.ValidaTokenException;

public class ExceptionsHandling {
		
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
		} else if (nomeCampo == "Data de Nascimento") {
			int dia = Integer.parseInt(campo.substring(0, 2));
			int mes = Integer.parseInt(campo.substring(3, 5));
			int ano = Integer.parseInt(campo.substring(6));
			if (dia > 31 || dia < 1) {
				throw new ValidaCamposException("Data informada inválida, informe seguindo o exemplo: dd/mm/AAAA (com as barras)");				
			} else if (mes < 1 || mes > 12){
				throw new ValidaCamposException("Data informada inválida, informe seguindo o exemplo: dd/mm/AAAA (com as barras)");
			} else if (ano < 1900 || ano > 2023) {
				throw new ValidaCamposException("Data informada inválida, informe seguindo o exemplo: dd/mm/AAAA (com as barras)");
			}
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
	
	public static void ValidaEndereco(Endereco endereco) throws EnderecoValidacaoException{
		if (endereco.getBairro().equals("") || endereco.getBairro().isEmpty()) {
			throw new EnderecoValidacaoException("Informe o *Bairro* do seu enderecço!");
		} else if(endereco.getCidade().equals("") || endereco.getCidade().isEmpty()) {
			throw new EnderecoValidacaoException("Informe a *Cidade* do seu enderecço!");
		} else if (endereco.getEstado().equals("") || endereco.getCidade().isEmpty() || endereco.getEstado().length() > 2) {
			throw new EnderecoValidacaoException("Informe a *Estado* do seu enderecço, EX: SP, RJ, PE!");
		} else if(endereco.getCEP().equals("") || endereco.getCEP().isEmpty() || !endereco.getCEP().matches("[0-9]{5}-[0-9]{3}")) {
			throw new EnderecoValidacaoException("Informe a *CEP* do seu enderecço, No formato: XXXXX-XXX");
		} else if(endereco.getNumero() < 0) {
			throw new EnderecoValidacaoException("Informe a *Numero* do seu enderecço, Insira um numero válido!");
		} else if(endereco.getRua().isEmpty() || endereco.getRua().equals("")) {
			throw new EnderecoValidacaoException("Informe a *Rua* do seu enderecço!");
		}
	}
	
	public static void ValidaPesoProduto(double peso) throws ValidaPesoProdutoException{
		if (peso < 0) {
			throw new ValidaPesoProdutoException();
		} else if (peso > 20){
			throw new ValidaPesoProdutoException("Pesso acima do permitido! Máximo: 20kg.");
		}
	}
	
	public static void ValidaEntrega(String codEnc) throws ValidaEntregaException{
		for (Entregas entrega : CrudEncomenda.entregas) {
			if (entrega.getEncomenda().getCodigo().equals(codEnc)) {
				throw new ValidaEntregaException();
			}
		}
	}
	
	public static void ValidaDeleteEntregador(String codEnt) throws ValidaDeleteEntregadorException{
		for (Entregas entrega : CrudEncomenda.entregas) {
			if (entrega.getCodEntregador().equals(codEnt) && entrega.getStatus().equals("À caminho do destinatário")) {
				throw new ValidaDeleteEntregadorException();
			}
		}
	}
	
	public static void ValidaDeleteEncomenda(String codEnc) throws ValidaDeleteEncomendaException{
		for (Entregas entrega : CrudEncomenda.entregas) {
			if (entrega.getEncomenda().getCodigo().equals(codEnc)) {
				throw new ValidaDeleteEncomendaException();
			}
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
