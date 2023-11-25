package Exceptions;
public class CPFValidacaoException extends Exception{
	public String mensagem;

	public CPFValidacaoException() {
		super();
		this.setMensagem("CPF Invalido");
	}
	
	public CPFValidacaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
}
