import java.util.Date;

public class Gerente extends Funcionario{
	private String TokenAcesso;
	
	public Gerente(String codFuncionario, String nome, String CPF, Endereco endereco, Date data_nascimento, String senha, String TokenAcesso) {
		super(codFuncionario, nome, CPF, endereco, data_nascimento, senha);
		setTokenAcesso(TokenAcesso);
	}

	public String getTokenAcesso() {
		return TokenAcesso;
	}

	public void setTokenAcesso(String tokenAcesso) {
		TokenAcesso = tokenAcesso;
	}
	
}
