package Entidades;

import Controle.CrudEncomenda;

public class Entregas {
	private String codEntrega, codEntregador, Status;
	private Encomenda encomenda;
	
	public Entregas(Encomenda encomenda) {
		this.gerarCodEntrega();
		this.setEncomenda(encomenda);		
	}
	
	private void gerarCodEntrega() {
		if (CrudEncomenda.entregas.isEmpty()) {
			String codEntrega = "ENTR0001";
			this.setCodEntrega(codEntrega);
		} else {
			int codigo = 0;
			String novoCodigo = "";
			for (Entregas entrega : CrudEncomenda.entregas) {
				codigo += 1;
			}
			novoCodigo = "CRR000"+String.valueOf(codigo + 1);
			
			this.setCodEntrega(novoCodigo);
		}
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
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
