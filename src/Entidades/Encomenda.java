package Entidades;
import java.util.Date;

public abstract class Encomenda {
	private String Codigo;
	private Cliente Destinatario;
	private Cliente Remetente;
	private Date DataPostagem;
	
	public Encomenda(String codigo, Cliente destinatario, Cliente remetente, Date dataPostagem) {
		this.setCodigo(codigo);
		this.setDestinatario(destinatario);
		this.setRemetente(remetente);
		this.setDataPostagem(dataPostagem);
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public Cliente getDestinatario() {
		return Destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		Destinatario = destinatario;
	}

	public Cliente getRemetente() {
		return Remetente;
	}

	public void setRemetente(Cliente remetente) {
		Remetente = remetente;
	}

	public Date getDataPostagem() {
		return DataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		DataPostagem = dataPostagem;
	}
	
	
}
