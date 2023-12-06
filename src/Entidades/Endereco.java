package Entidades;

public class Endereco {

	private String Rua, Bairro, Cidade, Estado, CEP;
	private int Numero;
	
	public Endereco(String rua, int numero, String bairro, String cidade, String estado, String CEP) {
		this.setRua(rua);
		this.setNumero(numero);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
		this.setCEP(CEP);
	}
	
	@Override
	public String toString() {
		return "Endereco: Rua: " + Rua + " | Bairro=" + Bairro + " | Cidade=" + Cidade + " | Estado=" + Estado + " | CEP="
				+ CEP + " | Numero=" + Numero + " |";
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		this.Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
	
	
}
