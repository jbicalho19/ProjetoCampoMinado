package projetoCampoMinado.modulo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetoCampoMinado.excecao.ExplosaoException;



public class CampoTeste {
private Campo campo;

@BeforeEach
void iniciarCampo() {
	campo = new Campo(3,3);
}

	
	@Test
	void adicionarVizinhoB() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}

	@Test
	void adicionarVizinhoD() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void adicionarVizinhoC() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void adicionarVizinhoE() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void adicionarVizinhoDiagonal() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void adicionarVizinhoFalsoDiagonal() {
		Campo vizinho = new Campo (1,1);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void adicionarVizinhoFalso() {
		Campo vizinho = new Campo(1,3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}
	@Test
	void alternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void abrirCampoNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void abrirCampoNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
        campo.abrir();
        }
		);
	}
	
	@Test
	void vizinhosSeguros() {
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinhos(campo11);
		campo.adicionarVizinhos(campo22);
		campo.abrir();
		
		
		assertTrue(campo11.isAberto() && campo22.isAberto());
	}
	
	void vizinhosMinados(){
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		campo22.adicionarVizinhos(campo12);
		campo22.adicionarVizinhos(campo11);
		campo.adicionarVizinhos(campo22);
		
		assertTrue(campo11.isAberto() && campo22.isFechado());
				
		
		}
}

