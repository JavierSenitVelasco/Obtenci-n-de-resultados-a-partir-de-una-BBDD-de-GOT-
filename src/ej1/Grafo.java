package ej1;

import java.util.*;
import java.util.Map.Entry;

/**Clase Grafo
 * @author Alberto Perez y Javier Senit
 *
 * @param <T> La clase Grafo es de tipo generico
 */
public abstract class Grafo<T> { 
	
	protected Map<Integer, Vertice<T>> vertices;
	private Map<Integer, Map<Vertice<T>, Double>> aristas;
	String name;
	

	
	/**Constructor de la clase Grafo, el cual es vacio
	 * 
	 */
	public Grafo() {
		 vertices = new HashMap<Integer, Vertice<T>>();
		 aristas = new HashMap<Integer, Map<Vertice<T>, Double>>();
	}

	/**Funcion que anade un vertice al grafo llamando al metodo protegido de igual nombre
	 * @param datos datos del vertice que se va a introducir
	 * @return Vertice aniadido al grafo
	 */
	public Vertice<T> addVertice(T datos) {
		
		return addVertice(vertices.size()+1, datos);
		
	}
	
	/**Funcion que anade un vertice al grafo
	 * @param id id del vertice a anadir
	 * @param datos datos del vertice a anadir
	 * @return Vertice anadido
	 */
	protected Vertice<T> addVertice(int id, T datos) {
		Vertice<T> aux = new Vertice<T>(datos,id);
		
		this.vertices.put(aux.getId(), aux);
		return aux;
	
	}
	
	

	

	
	
	/**Funcion que obtiene la listas de vertices de la cual esta formada el grafo
	 * @return lista de vertices del grafo
	 */
	public List<Vertice<T>> getVertices() {
		List<Vertice<T>> listaVertices = new ArrayList<Vertice<T>>();
		 for (Entry<Integer, Vertice<T>> aux : this.vertices.entrySet()){
	            
	            Vertice<T> valor = aux.getValue();
	            listaVertices.add(valor);
		 }
		 return listaVertices;
	}
	
	public int getNumVertices() {
		
		return this.vertices.size();
	}
	
	/**Funcion que comprueba si existe un arco entre dos vertices de un grafo
	 * @param v1 vertice inicio
	 * @param v2 vertice destino
	 * @return true si existe arco, false si no
	 */
	public boolean existeArco(Vertice<T> v1, Vertice<T> v2) {
		 for (Entry<Integer, Map<Vertice<T>, Double>> aux : this.getAristas().entrySet()){
	            Integer origen = aux.getKey();
	            Map<Vertice<T>, Double> map2 = aux.getValue();
	            for (Entry<Vertice<T>, Double> aux2 : map2.entrySet()){
	            	Vertice<T> destino = aux2.getKey();
	            	if(origen.equals(v1.getId()) && destino.equals(v2)) {
	            			return true;
	            	}
	            }
		 }
		 return false;
	}
	
	/**Funcion que obtiene el numero de arcos de un grafo
	 * @return numero de arcos del grafo
	 */
	public int getNumArcos() {
		return aristas.size();
	}
	
	/**Funcion que devuelve el mapa de arcos(aristas) del grafo
	 * @return mapa con el id de un vertice y el vertice con el que esta unido
	 */
	public Map<Integer, Map<Vertice<T>, Double>> getAristas() {
		return aristas;
	}

	/*Clases abstractas a implementrar en los hijos*/	
	/**Funcion abstracta que anadira un arco al grafo
	 * @param v1 vertice de origen
	 * @param v2 vertice de destino
	 * @param peso peso entre ambos vertices
	 */
	public abstract void addArco(Vertice<T> v1, Vertice<T> v2, double peso);
	
	
	/**Funcion abstracta que obtiene el peso entre dos vertices
	 * @param v1 vertice origen
	 * @param v2 vertice destino
	 * @return peso entre ambos vertices
	 */
	public abstract double getPesoDe(Vertice<T> v1, Vertice<T> v2) ;
	
	
	
	
	/**Funcion abstracta que obtiene los vecinos de un vertice dado
	 * @param v vertice del cual se quieren los vecinos
	 * @return lista de vecinos del vertice
	 */
	public abstract List<Vertice<T>> getVecinosDe(Vertice<T> v); // devuelve los vértices que tienen un arco con v 
	                                                            //(en grafo dirigido,v ha de ser origen de los arcos) 
	public String toString() {
		return "Vertices : " + this.getVertices().toString() +  "\nAristas : " + this.getAristas().toString();
		// los vértices del grafo han de presentarse ORDENADOS POR IDENTIFICADO
	}

	

}