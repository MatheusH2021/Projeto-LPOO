import java.util.Date;

public class Correspondencia extends Encomenda{
	private String tipoCorrespondencia;
	
	public Correspondencia(String codigo, Cliente destinatario, Cliente remetente, Date dataPostagem, String tipoCorrespondencia) {
		super(codigo, destinatario, remetente, dataPostagem);
		this.setTipoCorrespondencia(tipoCorrespondencia);
	}

	public String getTipoCorrespondencia() {
		return tipoCorrespondencia;
	}

	public void setTipoCorrespondencia(String tipoCorrespondencia) {
		this.tipoCorrespondencia = tipoCorrespondencia;
	}

	
	
}
