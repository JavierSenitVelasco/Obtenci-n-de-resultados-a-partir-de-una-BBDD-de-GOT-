package ej2;

import ej1.Vertice;

/**Clase PersonajeGOT
 * @author Alberto Perez y Javier Senit
 *
 */
public class PersonajeGOT {
	private int id;
	private String nombre;
	private String casa;
	
	/**Constructor de la clase PersonajeGOT
	 * @param id id del personaje
	 * @param nombre nombre del personaje
	 * @param casa casa del personaje
	 */
	public PersonajeGOT(int id, String nombre, String casa) {
		
		this.id=id;
		this.nombre=nombre;
		this.casa=casa;
				
	}

	

	/**Funcion que obtiene el id de un personaje
	 * @return id del personaje
	 */
	public int getId() {
		return id;
	}

	/**Funcion que obtiene el nombre del personaje
	 * @return nombre del personaje
	 */
	public String getNombre() {
		return nombre;
	}

	/**funcion que obtiene la casa del personaje
	 * @return casa del personaje
	 */
	public String getCasa() {
		return casa;
	}
	

	@Override
	public String toString() {
		return "PersonajeGOT [id=" + id + ", nombre=" + nombre + ", casa=" + casa + "]";
	}
	
}
