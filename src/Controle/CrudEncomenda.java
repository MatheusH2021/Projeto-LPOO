package Controle;
import java.util.ArrayList;
import java.util.Date;

import Entidades.Correspondencia;
import Entidades.Encomenda;
import Entidades.Entregador;
import Entidades.Entregas;
import Entidades.Produto;
import Exceptions.CPFValidacaoException;
import Exceptions.EnderecoValidacaoException;
import Exceptions.ValidaCamposException;
import Exceptions.ValidaEntregaException;
import Exceptions.ValidaPesoProdutoException;

public class CrudEncomenda implements ICRUD<Encomenda>{
	
	public static ArrayList<Encomenda> encomendas;
	public static ArrayList<Entregas> entregas;
	public static boolean acaoSucesso = true;
	public Date Data;
	
	public CrudEncomenda() {
		encomendas = new ArrayList<Encomenda>();
		entregas = new ArrayList<Entregas>();
		Data = new Date();
	}
	@Override
	public String insertDados(Encomenda dados) {
		if (dados instanceof Correspondencia) {
			Correspondencia novaEnc = (Correspondencia) dados;
			try {
				ExceptionsHandling.CpfValidacao(novaEnc.getDestinatario().getCPF());
				ExceptionsHandling.CpfValidacao(novaEnc.getRemetente().getCPF());
				ExceptionsHandling.CampoVazio(novaEnc.getDestinatario().getNome(), "Nome");
				ExceptionsHandling.CampoVazio(novaEnc.getRemetente().getNome(), "Nome");
				ExceptionsHandling.ValidaEndereco(novaEnc.getDestinatario().getEndereco());
				ExceptionsHandling.ValidaEndereco(novaEnc.getRemetente().getEndereco());
			}catch(CPFValidacaoException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			} catch(ValidaCamposException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			} catch (EnderecoValidacaoException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			}
			
			encomendas.add(novaEnc);
			CrudFuncionario.LogsAcoes.add("Nova Encomenda Inserido: Data->"+this.Data+" | Codigo da encomenda inserida->"+novaEnc.getCodigo()+" |");
			CrudEncomenda.acaoSucesso = true;
			
			return "Encomenda Cadastrada com sucesso!";
			
		} else if (dados instanceof Produto) {
			Produto novaEnc = (Produto) dados;
			try {
				ExceptionsHandling.CpfValidacao(novaEnc.getDestinatario().getCPF());
				ExceptionsHandling.CpfValidacao(novaEnc.getRemetente().getCPF());
				ExceptionsHandling.CampoVazio(novaEnc.getDestinatario().getNome(), "Nome");
				ExceptionsHandling.CampoVazio(novaEnc.getRemetente().getNome(), "Nome");
				ExceptionsHandling.ValidaEndereco(novaEnc.getDestinatario().getEndereco());
				ExceptionsHandling.ValidaEndereco(novaEnc.getRemetente().getEndereco());
				ExceptionsHandling.ValidaPesoProduto(novaEnc.getPeso());
			}catch(CPFValidacaoException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			} catch(ValidaCamposException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			} catch (EnderecoValidacaoException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMessage();
			} catch(ValidaPesoProdutoException e) {
				CrudEncomenda.acaoSucesso = false;
				return "|*- Erro: "+e.getMensagem();
			}
			
			encomendas.add(novaEnc);
			CrudFuncionario.LogsAcoes.add("Nova Encomenda Inserida: Data->"+this.Data+" | Codigo da encomenda->"+novaEnc.getCodigo()+" |");
			CrudEncomenda.acaoSucesso = true;
			
			return "Encomenda Cadastrada com sucesso!";
			
		} else {
			CrudEncomenda.acaoSucesso = false;
			return "Erro ao cadastrar Encomenda.";
		}
	}

	@Override
	public String selectDados() {
		if (encomendas.isEmpty()) {
			return "Sem encomendas cadastradas";
		} else {
			String retorno = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
			retorno += "Codigo Encomenda | Data de Postagem             | Remetente 		| Destinatario 		| Tipo de Encomenda | Endereco Entrega                                                                                    |";
			retorno += "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			for (Encomenda encomenda : encomendas) {
				if (encomenda instanceof Correspondencia) {
					retorno += "\n "+encomenda.getCodigo()+"         | "+encomenda.getDataPostagem()+" | "+encomenda.getRemetente().getNome()+"		| "+encomenda.getDestinatario().getNome()+"	 	| "+"Correspondencia   | "+encomenda.getDestinatario().getEndereco();
				} else if (encomenda instanceof Produto) {
					retorno += "\n "+encomenda.getCodigo()+"         | "+encomenda.getDataPostagem()+" | "+encomenda.getRemetente().getNome()+"		| "+encomenda.getDestinatario().getNome()+"	 	| "+"Produto           | "+encomenda.getDestinatario().getEndereco();				
				} 
			}
			retorno += "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
			
			return retorno;
		}
	}
	
	public static boolean atribuirEntregador(Entregador ent, Encomenda enc) {
		try {
			ExceptionsHandling.ValidaEntrega(enc.getCodigo());
		}catch(ValidaEntregaException e) {
			return false;
		}
		Entregas entrega = new Entregas(enc);
		ent.setEntregas(entrega);
		CrudEncomenda.entregas.add(entrega);
		return true;
	}

	@Override
	public String updateDados(Encomenda novosDados, String Token) {
		return null;
	}

	@Override
	public String deleteDados(String codPesquisa, String Token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Encomenda selectPorCodigo(String codPesquisa) {
		if (encomendas.isEmpty()) {
			return null;
		} else {
			for (Encomenda encomenda : encomendas) {
				if (encomenda.getCodigo().equals(codPesquisa)) {
					return encomenda;
				}
			}
		}
		return null;
	}


	@Override
	public String LogsAcoes() {
		// TODO Auto-generated method stub
		return null;
	}

}
