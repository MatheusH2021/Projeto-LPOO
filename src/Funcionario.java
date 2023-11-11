import java.util.Date;

public class Funcionario {
	private String codFuncionario;
	private String Nome;
	private String CPF;
	private String senha;
	private Endereco endereco;
	private Date Data_nascimento;
	
	public Funcionario(String codFuncionario, String nome, String CPF, Endereco endereco, Date data_nascimento, String senha) {
		super();
		setCodFuncionario(codFuncionario);
		setNome(nome);
		setCPF(CPF);
		setSenha(senha);
		setEndereco(endereco);
		setData_nascimento(data_nascimento);
	}
	

	public String getCodFuncionario() {
		return codFuncionario;
	}
	
	public void setCodFuncionario(String codFuncionario) {
		this.codFuncionario = codFuncionario;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Date getData_nascimento() {
		return Data_nascimento;
	}
	
	public void setData_nascimento(Date data_nascimento) {
		Data_nascimento = data_nascimento;
	}
	
}
