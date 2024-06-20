package TP2;
import java.awt.Point;
import java.util.ArrayList;

public class Clustering {

	private ArrayList<Point> puntos;
	private Grafo grafo;
	
	public Clustering(ArrayList<Point> p){
		puntos=p;
		
		grafo=new Grafo(puntos.size());
		
		grafo.generarGrafoCompleto(puntos);
	
	}
	
	public Grafo getGrafo() {
		return this.grafo;
	}
	
	public Grafo obtenerClusters(int cantClusters) {					//para generar clusters, cortamos la arista más larga, es decir la de mayor peso
		Grafo ret = AGM.ArbolGeneradorMinimo(this.grafo);				//si queremos 2 grupos, cortamos 1 arista, si queremos 3, cortamos 2, y así sucesivamente.
		for(int i=1; i<cantClusters;++i) {
			ret.eliminarMayorArista();
		}
		
		return ret;
	}
	
	
	
	
}
