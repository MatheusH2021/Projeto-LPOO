package Controle;

public interface ICRUD<E>{

	/** Funções basicas para os CRUDS **/
	
	String insertDados(E dados);
	String updateDados(E novosDados);
	String deleteDados(String codPesquisa, String Token);
	Object selectFuncionario(String codPesquisa);
	String selectDados();
	String LogsAcoes();
	
	
}
