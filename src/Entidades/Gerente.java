package Entidades;
import java.util.ArrayList;
import java.util.Random;

import Controle.CrudFuncionario;

public class Gerente extends Funcionario{
	private String TokenAcesso;
	
	public Gerente(String nome, String CPF, Endereco endereco, String data_nascimento, String senha) {
		super(nome, CPF, endereco, data_nascimento, senha);
		this.gerarCodFuncionario(CrudFuncionario.funcionarios);
		this.gerarTokenAcesso();
	}
	
	private void gerarCodFuncionario(ArrayList<Funcionario> funcionarios) {
		if (funcionarios.isEmpty()) {
			String codFuncionario = "GR0001";
			super.setCodFuncionario(codFuncionario);
		} else {
			int codigo = 0;
			String novoCodigo;
			for (Funcionario funcionario2 : funcionarios) {
				Funcionario ultimo = funcionario2;
				if (ultimo instanceof Gerente) {
					codigo += 1;
				}
			}
			if (codigo == 0) {
				novoCodigo = "GR000"+String.valueOf(codigo + 1);				
			} else {
				novoCodigo = "GR000"+String.valueOf(codigo + 1);
			}
			super.setCodFuncionario(novoCodigo);
		}
	}
	
	@Override
	public String toString() {
		return "Gerente [TokenAcesso=" + TokenAcesso + ", codFuncionario=" + super.getCodFuncionario() + ", Nome=" + super.getNome() + ", CPF=" + super.getCPF() + ", senha=" + super.getSenha()
				+ ", endereco=" + super.getEndereco()+ ", Data_nascimento=" + super.getData_nascimento() + "]";
	}

	private void gerarTokenAcesso() {
		String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		String tokenAcc = "";
		
		Random numero = new Random();
		
		for (int i=0; i<=10; i++) {
			int indice = numero.nextInt(letras.length - 1);
			if (indice % 2 != 0) {
				tokenAcc += letras[indice].toLowerCase();
			} else {
				tokenAcc += letras[indice];								
			}
			tokenAcc += numeros[numero.nextInt(numeros.length - 1)];
		}
		this.setTokenAcesso(tokenAcc);
	}
	
	public String getTokenAcesso() {
		return TokenAcesso;
	}
	
	private void setTokenAcesso(String tokenAcesso) {
		TokenAcesso = tokenAcesso;
	}
	
}
