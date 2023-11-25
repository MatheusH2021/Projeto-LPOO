
public class Funcionario{
	private String codFuncionario, Nome, CPF, senha;
	private Endereco endereco;
	private String Data_nascimento;

	public Funcionario(String nome, String CPF, Endereco endereco, String data_nascimento, String senha) {
		this.gerarCodFuncionario();
		this.setNome(nome);
		this.setCPF(CPF);
		this.setSenha(senha);
		this.setEndereco(endereco);
		this.setData_nascimento(data_nascimento);
	}
		
	private void gerarCodFuncionario() {
		if (CrudFuncionario.funcionarios.isEmpty()) {
			String codFuncionario = "FN0001";
			this.setCodFuncionario(codFuncionario);
		} else {
			int codigo = 0;
			String novoCodigo;
			for (Funcionario funcionario2 : CrudFuncionario.funcionarios) {
				Funcionario ultimo = funcionario2;
				if (ultimo instanceof Gerente == false && ultimo instanceof Entregador == false) {
					codigo += 1;
				}
			}
			if (codigo == 0) {
				novoCodigo = "FN000"+String.valueOf(codigo + 1);				
			} else {
				novoCodigo = "FN000"+String.valueOf(codigo + 1);
			}
			this.setCodFuncionario(novoCodigo);
		}
	}
	
	public String getCodFuncionario() {
		return codFuncionario;
	}
	
	protected void setCodFuncionario(String codFuncionario) {
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
	
	public String getData_nascimento() {
		return Data_nascimento;
	}
	
	public void setData_nascimento(String data_nascimento) {
		Data_nascimento = data_nascimento;
	}
	
	/** To String **/
	@Override
	public String toString() {
		return "Funcionario [codFuncionario=" + codFuncionario + ", Nome=" + Nome + ", CPF=" + CPF + ", senha=" + senha
				+ ", endereco=" + endereco + ", Data_nascimento=" + Data_nascimento + "]";
	}
	
}
