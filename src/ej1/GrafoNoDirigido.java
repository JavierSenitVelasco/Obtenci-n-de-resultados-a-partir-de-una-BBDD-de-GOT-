package ej1;



import java.util.*;
import java.util.Map.Entry;


/**Clase GrafoNoDirigido
 * @author Alberto Perez y Javier Senit
 *
 * @param <T>Un GrafoNoDirigido es de tipo generico
 */
public class GrafoNoDirigido<T> extends Grafo<T>{

	
	

	@Override
	public void addArco(Vertice<T> v1, Vertice<T> v2, double peso) {
	
		Map<Integer, Map<Vertice<T>, Double>> aristas = super.getAristas();
		Map<Vertice<T>, Double> mapa = aristas.get(v1.getId());
		Map<Vertice<T>, Double>  mapa2 = aristas.get(v2.getId());
		
		if(this.existeArco(v1, v2) || mapa != null ) {
			mapa.put(v2, peso);
		}
		else {
			mapa = new HashMap<Vertice<T>, Double>();
			mapa.put(v2, peso);
			this.getAristas().put(v1.getId(), mapa);
		}
		
		if(this.existeArco(v2, v1) || mapa2!= null) {
			mapa2.put(v1, peso);
		}
		else {
			mapa2 = new HashMap<Vertice<T>, Double>();
			mapa2.put(v1, peso);
			this.getAristas().put(v2.getId(), mapa2);
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
	            	else if(origen.equals(v2.getId()) && destino.equals(v1)) {
	            		return peso;
	            	}
	            }
		 }
		return 0;
	}

	@Override
	public List<Vertice<T>> getVecinosDe(Vertice<T> v) {
		List<Vertice<T>> listaVertices = new ArrayList<>();
		Set<Vertice<T>> sinRepetidos = new HashSet<Vertice<T>>();
		 for (Entry<Integer, Map<Vertice<T>, Double>> aux : this.getAristas().entrySet()){
	            Integer origen = aux.getKey();
	            Map<Vertice<T>, Double> mapa = aux.getValue();
		            for (Entry<Vertice<T>, Double> aux2 : mapa.entrySet()){
		            	Vertice<T> destino = aux2.getKey();
			            	
			            if(origen.equals(v.getId())) {
			            	listaVertices.add(destino); 
			            	sinRepetidos.addAll(listaVertices);
							listaVertices.clear();
							listaVertices.addAll(sinRepetidos);
						} else if (destino.equals(v)) {
							listaVertices.add(vertices.get(origen));
							sinRepetidos.addAll(listaVertices);
							listaVertices.clear();
							listaVertices.addAll(sinRepetidos);
						
						}
		            }
		 }
 
	return listaVertices;
	}
	
	
}
