package projetoCampoMinado.modulo;

import java.util.ArrayList;
import java.util.List;

import projetoCampoMinado.excecao.ExplosaoException;

public class Campo {
	private final int linha;
	private final int coluna;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	    boolean adicionarVizinhos(Campo vizinho) {
		int distanciaLinha = Math.abs(linha - vizinho.linha);
		int distanciaColuna = Math.abs(coluna - vizinho.coluna);
		int distanciaTotal = distanciaLinha + distanciaColuna;

		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		if (diagonal && distanciaTotal == 2) {
			vizinhos.add(vizinho);
			return true;
		} else if (!diagonal && distanciaTotal == 1) {
			vizinhos.add(vizinho);
			return true;
		} else
			return false;

	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			else if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		}
		return false;
	}

	void alternarMarcacao() {
		if (!aberto)
			marcado = !marcado;
	}

	void minar() {
		minado = true;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isMinado() {
		return minado;
	}

	public boolean isMarcado() {
		return marcado;
	}

	void resetar() {
		aberto = false;
		marcado = false;
		minado = false;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	boolean objetivoAlcancado() {
		boolean obj1 = aberto && !minado;
		boolean obj2 = marcado && minado;
		return obj1 || obj2;
		
	}
	
	
	public String toString() {
		if(marcado)
			return "!";
		
		else if(aberto && minado)
			return "*";
		
		else if(aberto && minasNaVizinhanca() > 0)
			return Long.toString(minasNaVizinhanca());
		
		else if(aberto)
			return " ";
		
		else
			return "o";
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	
	
}
