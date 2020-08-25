package ej1;

import java.util.*;


/**Tester de prueba del ejercicio 1
 * @author Alberto Perez y Javier Senit
 *
 */
public class Tester {

	public static void main(String[] args) {
		Grafo<String> g = new GrafoNoDirigido<>();
		//Grafo<String> g = new GrafoDirigido<>();
		System.out.println(g.getClass().getName() + "\n");
		
		Vertice<String> v1 = g.addVertice("A");
		Vertice<String> v2 = g.addVertice("B");
		Vertice<String> v3 = g.addVertice("C");
		g.addArco(v1, v2, 10);
		g.addArco(v1, v2, 20);
		g.addArco(v3, v1, 30);
		g.addArco(v3, v2, 40);
		
		System.out.println(g);
		List<Vertice<String>> vecinos1 = g.getVecinosDe(v3);
		System.out.println("Vecinos de " + v3 + ":");
		for (Vertice<String> v : vecinos1) {
		 System.out.println(v);
		}
		double peso1 = g.getPesoDe(v1, v2);
		System.out.println("Peso entre " + v1 + " y " + v2 + ": " + peso1);
		double peso2 = g.getPesoDe(v1, v3);
		System.out.println("Peso entre " + v1 + " y " + v3 + ": " + peso2);
		double peso3 = g.getPesoDe(v2, v3);
		System.out.println("Peso entre " + v2 + " y " + v3 + ": " + peso3);

	}

}


