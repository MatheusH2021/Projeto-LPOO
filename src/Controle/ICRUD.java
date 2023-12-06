package Controle;

public interface ICRUD<E>{

	/** Funções basicas para os CRUDS **/
	
	String insertDados(E dados);
	String updateDados(E novosDados, String Token);
	String deleteDados(String codPesquisa, String Token);
	Object selectPorCodigo(String codPesquisa);
	String selectDados();
	String LogsAcoes();
	
	
}
