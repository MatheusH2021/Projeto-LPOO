package Entidades;
import java.util.ArrayList;

import Controle.CrudFuncionario;

public class Entregador extends Funcionario{
	private ArrayList<Entregas> entregas;
	
	public Entregador(String nome, String CPF, Endereco endereco, String data_nascimento, String senha) {
		super(nome, CPF, endereco, data_nascimento, senha);
		this.gerarCodFuncionario(CrudFuncionario.funcionarios);
		entregas = new ArrayList<Entregas>();
	}
	
	public String visualizarEntregas() {
		if (temEntrega()) {
			String visuEntregas = "";
			for (Entregas entregas2 : entregas) {
				visuEntregas += "--------------------------------------------------------------------------------------------------------\n";
				visuEntregas += "|*- Codigo Entrega: "+entregas2.getCodEntrega()+
						        "\n|*- Status da entretga: "+entregas2.getStatus()+
						        "\n|*- Endereco de Entrega: "+entregas2.getEncomenda().getDestinatario().getEndereco()+
						        "\n|*- Destinatário: "+entregas2.getEncomenda().getDestinatario().getNome();
				visuEntregas += "\n--------------------------------------------------------------------------------------------------------------\n";
			}
			return visuEntregas;
		} else {
			return "|*- Sem entregas para visualizar ";
		}
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
	
	private void gerarCodFuncionario(ArrayList<Funcionario> funcionario) {
		if (funcionario.isEmpty()) {
			String codFuncionario = "ENT0001";
			super.setCodFuncionario(codFuncionario);
		} else {
			int codigo = 0;
			String novoCodigo;
			for (Funcionario funcionario2 : funcionario) {
				Funcionario ultimo = funcionario2;
				if (ultimo instanceof Entregador) {
					codigo += 1;
				}
			}
			if (codigo == 0) {
				novoCodigo = "ENT000"+String.valueOf(codigo + 1);				
			} else {
				novoCodigo = "ENT000"+String.valueOf(codigo + 1);
			}
			super.setCodFuncionario(novoCodigo);
		}
	}
	@Override
	public String toString() {
		return "Entregador [entregas=" + entregas + ", codFuncionario=" + super.getCodFuncionario() + ", Nome=" + super.getNome() + ", CPF=" + super.getCPF() + ", senha=" + super.getSenha()
				+ ", endereco=" + super.getEndereco() + ", Data_nascimento=" + super.getData_nascimento() +"]";
	}

	/** Verifica se o entregador possui entregas associadas**/
	private boolean temEntrega() {
		if (this.getEntregas().isEmpty()) {
			return false;
		}
		return true;
	}
	
	private ArrayList<Entregas> getEntregas() {
		return entregas;
	}

	public void setEntregas(Entregas entregas) {
		entregas.setStatus("À caminho do destinatário");
		entregas.setCodEntregador(this.getCodFuncionario());
		this.entregas.add(entregas);
	}
	
}
