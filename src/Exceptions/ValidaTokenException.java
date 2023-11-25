package Exceptions;

public class ValidaTokenException extends Exception{
	public String mensagem;

	public ValidaTokenException() {
		super();
		this.setMensagem("Token Invalido!");
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
