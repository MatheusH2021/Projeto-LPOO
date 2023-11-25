import java.util.ArrayList;

public interface ICRUD {
	
	static ArrayList<Object> arr = new ArrayList<Object>();
	
	/** Funções basicas para os CRUDS **/
	String insertDados(ArrayList<Object> dados);
	String updateDados(ArrayList<Object> novosDados);
	String deleteDados(String codPesquisa);
	Object selectFuncionario(String codPesquisa);
	String selectDados();
	
	
}
