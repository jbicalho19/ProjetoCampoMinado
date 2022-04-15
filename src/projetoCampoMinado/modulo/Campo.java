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

	//construtor
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	
	List<Campo> vizinhos = new ArrayList<>();

	
	boolean adicionarVizinhos(Campo vizinho) {

		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaAbsoluto = deltaLinha + deltaColuna;

		if (diagonal && deltaAbsoluto == 2) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaAbsoluto == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else
			return false;
	}

	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}

			return true;
		} else {
			return false;
		}

	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);

	}

	public boolean isMarcado() {
		return marcado;
	}

	void minar() {
		minado = true;
	}

	boolean isAberto() {
		return aberto;
	}

	boolean isFechado() {
		return !aberto;
	}

	public boolean isMinado() {
		return minado;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

}
