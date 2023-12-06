package Entidades;
import java.util.Date;

public class Produto extends Encomenda{
	public double Peso;
	public boolean Fragil;
	
	public Produto(Cliente destinatario, Cliente remetente, Date dataPostagem, double peso, boolean fragil) {
		super(destinatario, remetente, dataPostagem);
		this.setPeso(peso);
		this.setFragil(fragil);
	}
	
	public double getPeso() {
		return Peso;
	}
	public void setPeso(double peso) {
		Peso = peso;
	}
	public boolean isFragil() {
		return Fragil;
	}
	public void setFragil(boolean fragil) {
		Fragil = fragil;
	}
	
	
}
