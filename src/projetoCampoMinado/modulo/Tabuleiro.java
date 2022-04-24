package projetoCampoMinado.modulo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import projetoCampoMinado.excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
    private int colunas;
    private int minas;
	
    List <Campo> campos = new ArrayList<Campo>();
    
    
      public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		}while(minasArmadas < minas);
		
		
	}

	private void associarVizinhos() {
		for(Campo c1 : campos) {
			for(Campo c2 : campos) {
			c1.adicionarVizinhos(c2);	
			}
		}
	}

	private void gerarCampos() {
		for(int linha = 0; linha < linhas; linha++) {
			for(int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
				
		}
		
	}
	
	public void abrir(int linha, int coluna) {
		try {
		
		campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst().ifPresent(c -> c.abrir());;
		
		}catch(ExplosaoException e) {
		campos.forEach(c -> c.setAberto(true));
			
			throw e;
		
		}
		}
    
	public void alternarMarcacao(int linha, int coluna) {
		campos.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst().ifPresent(c -> c.alternarMarcacao());;
	}
    

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
		
	}
	
	public void reiniciar(){
		campos.forEach(c -> c.resetar());
		sortearMinas();
		
	}
	
	public String toString() {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int linha = 0; linha < linhas; linha++) {
			
			for (int coluna = 0; coluna < colunas; coluna++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			
			sb.append("\n");
				
			}
		return sb.toString();
		}
		
	}
	
	

