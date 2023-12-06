package Exceptions;

public class ValidaPesoProdutoException extends Exception{
	public String mensagem;

	public ValidaPesoProdutoException() {
		super();
		this.setMensagem("Peso do produto Invalido!");
	}

	public ValidaPesoProdutoException(String message) {
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
