
public interface ICrudFuncionario {
	
	/** Funções para classe CrudFuncionario **/
	String insertDados(Funcionario novoFuncionario);
	String updateDados(Funcionario novosDados);
	String deleteDados(String codFuncionario);
	Funcionario selectFuncionario(String codFun);
	String selectDados();
}
