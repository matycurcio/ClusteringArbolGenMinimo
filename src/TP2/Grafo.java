package TP2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo
{
	// Representamos el grafo por su matriz de adyacencia
	private boolean[][] Ady;
	
	//Guardamos en la matriz pesos el peso de cada arista. en la posicion ij de la matriz estará el peso de la arista ij.
	private double[][] pesos; 
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices)
	{
		Ady = new boolean[vertices][vertices];
		pesos = new double[vertices][vertices];
	}
	
	// Agregado de aristas
	public void agregarArista(int i, int j, double d)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		Ady[i][j] = true;
		Ady[j][i] = true;
		
		pesos[i][j]=d;
		pesos[j][i]=d;
	}
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		Ady[i][j] = false;
		Ady[j][i] = false;
		
		pesos[i][j]=0;
		pesos[j][i]=0;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return Ady[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return Ady.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArista(i,j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	public double[][] getPesos(){
		return this.pesos;
	}
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= Ady.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	public void generarGrafoCompleto(ArrayList<Point> puntos) {			//genera grafo completo a partir de una lista de puntos
																		//es decir, hace que todos los vertices sean vecinos de todos menos de si mismo
		if(puntos.size()!=Ady.length) {
			throw new RuntimeException("La lista debe tener la misma cantidad de puntos que grafos del vertice");
		}
		else {
			for (int i=0; i<puntos.size();++i) {							
				for (int j=0; j<puntos.size();++j) {
					if(i!=j) {
						this.agregarArista(i, j, puntos.get(i).distance(puntos.get(j)));
					}
				}
			}
		}	
	}

	public void eliminarMayorArista() {								//elimina la arista de mayor peso.
		double maximoTemp = 0;
		int iTemp = 0;
		int jTemp = 0;
		for(int i=0; i<pesos.length; ++i) {
			for (int j=0; j<pesos.length; ++j) {
				if(pesos[i][j] > maximoTemp) {
					maximoTemp=pesos[i][j];
					iTemp=i;
					jTemp=j;
				}
			}
		}
		eliminarArista(iTemp,jTemp);
	}
	
	public boolean[][] getMatrizAdy(){
		return Ady;
	}

}

