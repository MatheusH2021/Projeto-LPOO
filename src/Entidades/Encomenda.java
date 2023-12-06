package Entidades;
import java.util.Date;

import Controle.CrudEncomenda;

public abstract class Encomenda {
	private String Codigo;
	private Cliente Destinatario;
	private Cliente Remetente;
	private Date DataPostagem;
	
	public Encomenda(Cliente destinatario, Cliente remetente, Date dataPostagem) {
		this.gerarCodEncomeda();
		this.setDestinatario(destinatario);
		this.setRemetente(remetente);
		this.setDataPostagem(dataPostagem);
	}
	
	private void gerarCodEncomeda() {
		if (CrudEncomenda.encomendas.isEmpty()) {
			String codEncomenda = "CRR0001";
			this.setCodigo(codEncomenda);
		} else {
			int codigo = 0;
			String novoCodigo = "";
			for (Encomenda encomenda : CrudEncomenda.encomendas) {
				codigo += 1;
			}
			novoCodigo = "CRR000"+String.valueOf(codigo + 1);
			
			this.setCodigo(novoCodigo);
		}
	}
	
	public String getCodigo() {
		return Codigo;
	}

	private void setCodigo(String codigo) {
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
