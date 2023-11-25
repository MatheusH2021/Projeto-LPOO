import java.sql.Date;

public class Entregas {
	private String codEntrega, codEntregador, Status;
	private Encomenda encomenda;
	private Date Data_entrega;
	
	public Entregas(String codEntrega, String codEntregador, Encomenda encomenda, Date data_entrega, String status) {		
		this.setCodEntrega(codEntrega);
		this.setEncomenda(encomenda);
		this.setData_entrega(data_entrega);
		
	}
	
	public String getCodEntrega() {
		return codEntrega;
	}

	public String getCodEntregador() {
		return codEntregador;
	}

	public void setCodEntregador(String codEntregador) {
		this.codEntregador = codEntregador;
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
