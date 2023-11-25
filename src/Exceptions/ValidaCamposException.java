package Exceptions;

public class ValidaCamposException extends Exception{
	public String mensagem;
	
	
	public ValidaCamposException() {
		this.setMensagem("Informação passada no campo invalida!");
	}

	public ValidaCamposException(String message) {
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
