package TP2;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ClusteringTEST {

	@Test
	void AristaDelClusterSuelta() {
		ArrayList<Point> puntos = crearListaDePuntos();
		
		Clustering c= new Clustering(puntos);
		
		Grafo g= c.obtenerClusters(2);
		
		assertEquals(0,g.vecinos(0).size());
	}
	
	@Test
	void AristasDelClusterCon1vecino() {
		ArrayList<Point> puntos = crearListaDePuntos();
		
		Clustering c= new Clustering(puntos);
		
		Grafo g= c.obtenerClusters(2);
		
		assertEquals(1, g.vecinos(1).size());
		assertEquals(1, g.vecinos(2).size());
	}

	void VerticesSinVecinos() {
		ArrayList<Point> puntos = crearListaDePuntos();
		
		Clustering c= new Clustering(puntos);
		
		Grafo g= c.obtenerClusters(3);
		
		assertEquals(0,g.vecinos(0).size());
		assertEquals(0,g.vecinos(1).size());
		assertEquals(0,g.vecinos(2).size());
	}
	

	private ArrayList<Point> crearListaDePuntos() {
		ArrayList<Point> conjunto=new ArrayList<Point>();
		Point p1=new Point(1,1);
		Point p2= new Point(3,5);
		Point p3= new Point (4,4);
		conjunto.add(p1);
		conjunto.add(p2);
		conjunto.add(p3);
		return conjunto;
	}

}
