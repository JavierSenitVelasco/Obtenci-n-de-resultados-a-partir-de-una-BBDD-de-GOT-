package ej1;

import ej2.PersonajeGOT;

/**Clase Vertice
 * @author Alberto Perez y Javier Senit
 *
 * @param <T> Un vertice es de tipo generico
 */
public class Vertice<T> {
	private final int id; // identificador del vértice dentro del grafo
	private T datos; 

	
	/**Constructor de la clase Vertice
	 * @param datos datos del vertice
	 * @param id id del vertice
	 */
	public Vertice(T datos,int id) {
		this.id = id;
		this.datos=datos;
		
	}
	
	/**Funcion que devuelve el id de un vertice
	 * @return id del vertice
	 */
	public int getId() {
		return this.id;
	}


	/**Funcion que obtiene los datos de un vertice
	 * @return datos del vertice
	 */
	public T getDatos() {
		if(datos instanceof PersonajeGOT) {
			((PersonajeGOT) datos).getNombre();
		}
		return datos;
	}


	
	
	public String toString() {
	
		
		return  datos.toString();
	
	}
	
}
