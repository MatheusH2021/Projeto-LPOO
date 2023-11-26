package Exceptions;

public class EnderecoValidacaoException extends Exception{
	public String mensagem;

	
	
	public EnderecoValidacaoException() {
		super();
		this.setMensagem("Endereço inválido!");
	}

	public EnderecoValidacaoException(String message) {
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
