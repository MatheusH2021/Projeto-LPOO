package Exceptions;

public class ValidaEntregaException extends Exception{
	String mensagem;
	
	
	
	public ValidaEntregaException() {
		super();
		this.setMensagem("A encomenda já está na lista de entregas");
	}

	public ValidaEntregaException(String message) {
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
