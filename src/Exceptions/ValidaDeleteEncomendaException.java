package Exceptions;

public class ValidaDeleteEncomendaException extends Exception{
	public String mensagem;

	public ValidaDeleteEncomendaException() {
		super();
		this.setMensagem("Não é possivel deletar encomendas que estão na lista de entrega.");
	}

	public ValidaDeleteEncomendaException(String message) {
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
