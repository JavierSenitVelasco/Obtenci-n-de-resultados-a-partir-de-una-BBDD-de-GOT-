package ej2;
import java.util.*;

import java.util.stream.Stream;

import ej1.Vertice;

public class main2 {

	public static void main(String[] args) throws Exception {
		
		GrafoGOT grafo = new GrafoGOT ("got-s01-vertices.csv", "got-s01-arcos.csv");
		
		System.out.println(grafo.getVertice("Arthur Dayne"));
		System.out.println(grafo.casas());
		System.out.println(grafo.miembrosCasa("Mormont"));
		System.out.println(grafo.gradoPersonajes());
		System.out.println(grafo.gradoPonderadoPersonajes());
		System.out.println(grafo.personajesRelevantes());
	}

}
