package TP2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AGMTest {

	@Test
	void AristaExistenteEnAGM() {
		Grafo g = crearGrafo();
		
		Grafo h=AGM.ArbolGeneradorMinimo(g);
		assertTrue(h.existeArista(0, 1));
		assertTrue(h.existeArista(0, 2));
		assertTrue(h.existeArista(1, 3));

	}
	@Test	
	void AristaInexistenteEnAGM() {
		Grafo g = crearGrafo();
		
		Grafo h=AGM.ArbolGeneradorMinimo(g);
		assertFalse(h.existeArista(1, 2));
		assertFalse(h.existeArista(2, 3));
		
		assertEquals(2, h.getPesos()[0][2]);
	}
	@Test
	void PesoCorrectoEnAGM() {
		Grafo g = crearGrafo();
		
		Grafo h=AGM.ArbolGeneradorMinimo(g);
		assertEquals(2, h.getPesos()[0][2]);
		assertEquals(1, h.getPesos()[0][1]);
		
	}
	@Test
	void PesoIncorrectoEnAGM() {
		Grafo g = crearGrafo();
		
		Grafo h=AGM.ArbolGeneradorMinimo(g);
		assertNotEquals(3, h.getPesos()[0][2]);
	}
	
	@Test
	
	void EliminarAristaMayor() {
		Grafo g= crearGrafo();
		assertTrue(g.existeArista(2, 3));
		g.eliminarMayorArista();
		assertFalse(g.existeArista(2, 3));
	}
	
	private Grafo crearGrafo() {
		Grafo g=new Grafo(4);
		g.agregarArista(0, 1, 1);
		g.agregarArista(0, 2, 2);
		g.agregarArista(1, 2, 3);
		g.agregarArista(1, 3, 4);
		g.agregarArista(2, 3, 5);
		return g;
	}

}
