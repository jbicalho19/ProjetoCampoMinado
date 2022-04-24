package projetoCampoMinado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import projetoCampoMinado.excecao.ExplosaoException;
import projetoCampoMinado.excecao.SairException;
import projetoCampoMinado.modulo.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			

			while (continuar) {

				cicloDoJogo();

				System.out.print("Quer continuar: S/n -  ");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();

				}

			}

		} catch (SairException e) {
			System.out.println("Ate mais!!!");
		} finally {
			entrada.close();

		}
	}

	private void cicloDoJogo() {
		try {

			while (!tabuleiro.objetivoAlcancado()) {

				System.out.println(tabuleiro.toString());

				String digitado = capturarValorDigitado("Digite um valor x,y: ");

				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = capturarValorDigitado("\n'1' - para abrir ou '2' para (des)marcar: ");

				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}

				else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu :(");
		}

	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);

		String resposta = entrada.nextLine();

		if ("sair".equalsIgnoreCase(resposta)) {
			throw new SairException();
		}

		return resposta;
	}

	// executar jogo: logica da repetição, com saida, continuar...

	// ciclo do jogo: destacando o objetivo do jogo

	// método pra capturar as entradas do jogador(acompanhado do texto do jogo)

}
