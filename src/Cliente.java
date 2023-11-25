
public class Cliente {
	private String Nome, CPF;
	private Endereco Endereco;

	public Cliente(String nome, String CPF, Endereco endereco) {
		this.setNome(nome);
		this.setCPF(CPF);
		this.setEndereco(endereco);
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public Endereco getEndereco() {
		return Endereco;
	}
	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}
	
	
}
