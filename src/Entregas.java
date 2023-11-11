import java.sql.Date;

public class Entregas {
	private String codEntrega;
	private Encomenda encomenda;
	private Cliente Remetente;
	private Cliente Destinatario;
	private Date Data_entrega;
	private String Status;
	
	public Entregas(String codEntrega, Encomenda encomenda, Cliente remetente, Cliente destinatario, Date data_entrega,
			String status) {
		
		this.codEntrega = codEntrega;
		this.encomenda = encomenda;
		Remetente = remetente;
		Destinatario = destinatario;
		Data_entrega = data_entrega;
		Status = "Postado";
		
	}
	
	public String getCodEntrega() {
		return codEntrega;
	}
	public void setCodEntrega(String codEntrega) {
		this.codEntrega = codEntrega;
	}
	public Encomenda getEncomenda() {
		return encomenda;
	}
	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}
	public Cliente getRemetente() {
		return Remetente;
	}
	public void setRemetente(Cliente remetente) {
		Remetente = remetente;
	}
	public Cliente getDestinatario() {
		return Destinatario;
	}
	public void setDestinatario(Cliente destinatario) {
		Destinatario = destinatario;
	}
	public Date getData_entrega() {
		return Data_entrega;
	}
	public void setData_entrega(Date data_entrega) {
		Data_entrega = data_entrega;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
