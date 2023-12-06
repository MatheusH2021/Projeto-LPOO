package Exceptions;

public class ValidaDeleteEntregadorException extends Exception{
	public String mensagem;

	
	
	public ValidaDeleteEntregadorException() {
		super();
		this.setMensagem("Não foi possivel deletear entregador, pois esse entregador possui entregas pendentes.");
	}

	public ValidaDeleteEntregadorException(String message) {
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
