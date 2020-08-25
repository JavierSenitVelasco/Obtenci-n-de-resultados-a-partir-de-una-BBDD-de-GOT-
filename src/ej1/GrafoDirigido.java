package ej1;

import java.util.*;
import java.util.Map.Entry;

/**Clase GrafoDirigido
 * @author Alberto Perez y Javier Senit
 *
 * @param <T> Un GrafoDirigido es de tipo generico
 */
public class GrafoDirigido<T> extends Grafo<T>{

	
	@Override
	public void addArco(Vertice<T> v1, Vertice<T> v2, double peso) {
		
		Map<Integer, Map<Vertice<T>, Double>> aristas = super.getAristas();
		Map<Vertice<T>, Double> mapa = aristas.get(v1.getId());
		if(this.existeArco(v1, v2) || mapa != null) {
			mapa.put(v2, peso);
		}
		else {
			mapa = new HashMap<Vertice<T>, Double>();
			mapa.put(v2, peso);
			this.getAristas().put(v1.getId(), mapa);
		}
	}

	
	@Override
	public double getPesoDe(Vertice<T> v1, Vertice<T> v2) {
		 for (Entry<Integer, Map<Vertice<T>, Double>> aux : this.getAristas().entrySet()){
	            Integer origen = aux.getKey();
	            Map<Vertice<T>, Double> mapa = aux.getValue();
	            for (Entry<Vertice<T>, Double> aux2 : mapa.entrySet()){
	            	Vertice<T> destino = aux2.getKey();
	            	Double peso = aux2.getValue();
	            	if(origen.equals(v1.getId()) && destino.equals(v2)) {
	    				return peso;
	    			}
	            }
		 }
		return 0;
	}

	
	@Override
	public List<Vertice<T>> getVecinosDe(Vertice<T> v) {
		List<Vertice<T>> listaVertices = new ArrayList<>();
		 for (Entry<Integer, Map<Vertice<T>, Double>> aux : this.getAristas().entrySet()){
	            Integer origen = aux.getKey();
	            Map<Vertice<T>, Double> mapa = aux.getValue();
		            for (Entry<Vertice<T>, Double> aux2 : mapa.entrySet()){
		            	Vertice<T> destino = aux2.getKey();
			            	
			            if(origen.equals(v.getId())) {
			            	listaVertices.add(destino);   
						} else if (destino.equals(v)) {
							listaVertices.add(vertices.get(origen));
						}
		            }
		 }
		 return listaVertices;
	}
	
	

	
	

}
