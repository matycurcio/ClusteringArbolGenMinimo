package TP2;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class AGM {
	
	
	
	public static Grafo ArbolGeneradorMinimo(Grafo g){
		
		if (g == null) {
			throw new IllegalArgumentException("El grafo no puede ser null.");
		}
		Grafo h= new Grafo(g.tamano());
		
		Set<Integer> verticesEnArbol=new HashSet<Integer>();
		verticesEnArbol.add(0);
		
		for(int i=1; i<=g.tamano()-1; ++i) {
			Point p= seleccionarMinimo(g.getPesos(), verticesEnArbol);
			verticesEnArbol.add(p.y);
			h.agregarArista(p.x, p.y, g.getPesos()[p.x][p.y]);
		}		
		return h;
	}

	private static Point seleccionarMinimo(double[][] pesos, Set<Integer> verticesEnArbol) {
		double minimoProvisorio=-1;
		int iProvisorio = 0;
		int jProvisorio = 0;
		for(int i=0; i<pesos.length; ++i) {
			for(int j=0; j<pesos.length; ++j) {
				if(verticesEnArbol.contains(i) && !verticesEnArbol.contains(j) && pesos[i][j]!=0 && i!=j) {
					if(minimoProvisorio==-1 || minimoProvisorio>pesos[i][j]) {
						minimoProvisorio=pesos[i][j];
						iProvisorio=i; 
						jProvisorio=j;
					}
				}
					
			}
		}
		Point p= new Point(iProvisorio,jProvisorio);
		
		return p;
	}
}
