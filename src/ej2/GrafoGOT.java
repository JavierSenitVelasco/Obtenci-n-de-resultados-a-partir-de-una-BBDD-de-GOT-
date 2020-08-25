package ej2;
 
import java.io.*;
 
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
import ej1.*;
 
/**Clase GrafoGOT
 * @author Alberto Perez y Javier Senit
 *
 * @param <T> Un GrafoGOT es de tipo generico
 */
public class GrafoGOT<T> extends GrafoNoDirigido {
 
    private ArrayList<String> valoresVertices;
    private ArrayList<String> valoresArcos;
 
    /**Constructor de la clase GrafoGOT, el cual lee los ficheros de datos y se guarda los valores en variables propias
     * @param ficheroVertices fichero con los vertices del grafo
     * @param ficheroArcos fichero con los arcos del grafo
     * @throws Exception excepcion que ocurre si no se lee bien el fichero
     */
    public GrafoGOT(String ficheroVertices, String ficheroArcos) throws Exception {
 
        BufferedReader br1 = null;
        FileReader fr1 = null;
 
        fr1 = new FileReader(ficheroVertices);
        br1 = new BufferedReader(fr1);
 
        String linea;
        valoresVertices = new ArrayList<String>();
 
        //Se lee el ficheroVertice y se anade sus valores a la lista valoresVertices
        while ((linea = br1.readLine()) != null) {
            valoresVertices.add(linea);
 
        }
 
        BufferedReader br2 = null;
        FileReader fr2 = null;
 
        fr2 = new FileReader(ficheroArcos);
        br2 = new BufferedReader(fr2);
 
        String linea2;
        valoresArcos = new ArrayList<String>();
 
        //Se lee el ficheroArcos y se anade sus valores a la lista valoresArcos
        while ((linea2 = br2.readLine()) != null) {
            valoresArcos.add(linea2);
 
        }
 
        String[] valor = null;
        String[] valor2 = null;
        int i;
 
        //Se recorre la lista valoresVertices y se separan los valores por ","
        //Se crea un personaje con estos datos y se anade como vertice al grafo
        for (i = 0; i < valoresVertices.size(); i++) {
            valor = valoresVertices.get(i).split(",");
            PersonajeGOT p = new PersonajeGOT(Integer.parseInt(valor[0]), valor[1], valor[2]);
 
            this.addVertice(p);
             
 
        }
        
        //Se recorre la lista valoresArcos y se separan los valores por ","
        //Se anade un arco con estos valores
        for (i = 0; i < valoresArcos.size(); i++) {
            valor2 = valoresArcos.get(i).split(",");
             
            Vertice<T> v1 = (Vertice<T>) this.getVertices().get(Integer.parseInt(valor2[0])-1);
            Vertice<T> v2 = (Vertice<T>) this.getVertices().get(Integer.parseInt(valor2[1])-1);
            Double peso = Double.parseDouble(valor2[2]);
 
             
            this.addArco(v1, v2, peso);
             
 
        }
 
        br1.close();
        br2.close();
    }
 
    /**Funcion que obtiene un vertice del grafo pasandole el nombre del personaje
     * @param nombre nombre del personaje a buscar
     * @return vertice encontrado
     */
    public Vertice<PersonajeGOT> getVertice(String nombre) {
 
        Optional<Vertice<PersonajeGOT>> opt = this.getVertices().stream()
                .filter(a -> ((Vertice<PersonajeGOT>) a).getDatos().getNombre().equals(nombre)).findFirst();
 
        return opt.get();
 
    }
     
    /**Funcion que devuelve una lista con los nombres de los personajes de una casa indicada como argumento de entrada
     * @param casa casa de la cual se quieren sus personajes
     * @return lista con los nombres de los personajes de una casa indicada como argumento de entrada
     */
    public List<String> miembrosCasa(String casa){
        List<String> lista = new ArrayList<String>();
        this.getVertices().stream()
        .filter(a -> ((Vertice<PersonajeGOT>) a).getDatos().getCasa().equals(casa))
        .forEach(b -> {
 
            lista.add(((Vertice<PersonajeGOT>) b).getDatos().getNombre());
            });
        return lista;
    }
     
     
    /**Funcion que  devuelve los personajes y sus “grados”, esto es el número de vecinos que tiene cada vértice
     * @return personajes y sus “grados”, esto es el número de vecinos que tiene cada vértice
     */
    public Map<String, Integer> gradoPersonajes(){
        Map<String, Integer> mapa = new TreeMap<String, Integer>();
         
        this.getVertices().stream().forEach(a->{
         
             
             
         
         
        mapa.put((((Vertice<PersonajeGOT>) a).getDatos().getNombre()), 
                this.getVecinosDe((Vertice<T>) a).size());
        });
         
        return mapa;
    }
     
     
    /**Funcion que devuelve los personajes y sus “grados ponderados”, pero sólo para aquellos personajes cuyo grado ponderado es superior al grado ponderado medio en la red social.
     * @return personajes y sus “grados ponderados”, pero sólo para aquellos personajes cuyo grado ponderado es superior al grado ponderado medio en la red social.
     */
    public Map<String, Double> personajesRelevantes(){
        Map<String, Double> mapa = new TreeMap<String, Double>();
        Map<String, Double> mapa2 = new TreeMap<String, Double>();
        List<Double> listaPesos = new ArrayList<Double>();
        List<Double> listaPesosTotal = new ArrayList<Double>();
         
        this.getVertices().stream().forEach(a->{
         
             
            this.getVecinosDe((Vertice<T>) a).stream().forEach(b->{
             listaPesos.add(this.getPesoDe((Vertice<T>)a, (Vertice<T>)b));
             
              
            });
             
            mapa.put((((Vertice<PersonajeGOT>) a).getDatos().getNombre()), 
                    listaPesos.stream().reduce((double) 0,(c,d) -> c+d));
              
             listaPesosTotal.addAll(listaPesos);
             
            listaPesos.clear();
                 
        });
         OptionalDouble average =listaPesosTotal.stream().mapToDouble(e->e).average();
         
             
        this.getVertices().stream().forEach(a->{
                 
                 
                this.getVecinosDe((Vertice<T>) a).stream().forEach(b->{
                 listaPesos.add(this.getPesoDe((Vertice<T>)a, (Vertice<T>)b));
                 
                  
                });
                 
                mapa2.put((((Vertice<PersonajeGOT>) a).getDatos().getNombre()), 
                        listaPesos.stream().filter(h->h>average.getAsDouble()).reduce((double) 0,(c,d) -> c+d));
                  
                  
                 
                listaPesos.clear();
                     
            }); 
             
             
             
             
        return mapa2;
    }
     
    /**Funcion que devuelve los personajes y sus “grados ponderados”, esto es la suma de los pesos de los arcos que unen a cada vértice con sus vecinos
     * @return  personajes y sus “grados ponderados”, esto es la suma de los pesos de los arcos que unen a cada vértice con sus vecinos
     */
    public Map<String, Double> gradoPonderadoPersonajes(){
        Map<String, Double> mapa = new TreeMap<String, Double>();
         
        List<Double> listaPesos = new ArrayList<Double>();
         
        this.getVertices().stream().forEach(a->{
         
             
            this.getVecinosDe((Vertice<T>) a).stream().forEach(b->{
             listaPesos.add(this.getPesoDe((Vertice<T>)a, (Vertice<T>)b));
         
              
            });
             
            mapa.put((((Vertice<PersonajeGOT>) a).getDatos().getNombre()), 
                    listaPesos.stream().reduce((double) 0,(c,d) -> c+d));
            listaPesos.clear();
                 
        });
         
    return mapa;
    }
     
     
 
    /**Funcion que devuelve una lista con los nombres de las casas sin repeticiones, ordenadas, y sin valores null. 
     * @return lista con los nombres de las casas sin repeticiones, ordenadas, y sin valores null. 
     */
    public List<String> casas() {
        List<String> lista = new ArrayList<String>();
        Set<String> sinRepetidos = new TreeSet<String>();
        this.getVertices()
        .stream()
        .filter(a -> ((Vertice<PersonajeGOT>) a)
                .getDatos().getCasa() != null)
        .forEach(b -> {
 
            lista.add(((Vertice<PersonajeGOT>) b).getDatos().getCasa());
            });
 
        sinRepetidos.addAll(lista);
        lista.clear();
        lista.addAll(sinRepetidos);
        lista.remove("null");
        return lista;
 
    }
 
    @Override
    public String toString() {
        return "GrafoGOT [valoresVertices=" + valoresVertices + ", valoresArcos=" + valoresArcos + "]";
    }
 
}