import java.util.ArrayList;
import java.util.Date;

public class Entregador extends Funcionario{
	private ArrayList<Entregas> entregas;
	
	public Entregador(String codFuncionario, String nome, String CPF, Endereco endereco, Date data_nascimento, String senha, Entregas entregas) {
		super(codFuncionario, nome, CPF, endereco, data_nascimento, senha);
	}
	
	public ArrayList<Entregas> visualizarEntregas() {
		ArrayList<Entregas> visuEntregas = new ArrayList<Entregas>();
		visuEntregas = this.getEntregas();
		
		return visuEntregas;
	}
	
	public String gerenciarEntrega(String novoStatus, String codEntrega) {
		for (Entregas entrega : this.getEntregas()) {
			if (entrega.getCodEntrega().equals(codEntrega)) {
				entrega.setStatus(novoStatus);
						
				return "Status da entrega alterado com sucesso! Novo Status:"+entrega.getStatus();
			}
		}
		return "Entrega não encontrada!";
	}
	
	private ArrayList<Entregas> getEntregas() {
		return entregas;
	}

	public void setEntregas(Entregas entregas) {
		entregas.setStatus("À caminho do destinatário");
		this.entregas.add(entregas);
	}
	
}
