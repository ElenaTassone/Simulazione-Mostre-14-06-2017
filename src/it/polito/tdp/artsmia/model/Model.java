package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private ArtsmiaDAO dao ;
	private Collection <Mostra> mostre ;
	private DirectedGraph<Mostra, DefaultEdge> grafo ;
	
	
	public Model(){
		this.dao= new ArtsmiaDAO();
	}
	
	public List<Mostra> getMostre(){
		if(mostre==null){
			mostre= new ArrayList<Mostra> () ;
			mostre=dao.getMostre() ;
		}
		Collections.sort((List<Mostra>) mostre);
	return (List<Mostra>) mostre ;
	}
	
	public List<Integer> getAnni(){
		List <Integer> result = new ArrayList<Integer> () ;
		for(Mostra m :this.getMostre()){
			if(!result.contains(m.getBegin()))
				result.add(m.getBegin()) ;
		}
		
	
		return result;
	}

	public void creaGrafo(int anno) {
		
		Graphs.addAllVertices(grafo, this.getMostreDatoAnno(anno)) ;
		this.addEdges(anno) ;
		System.out.println(grafo);
	}

	private void addEdges(int anno) {
		for(Mostra m1 : grafo.vertexSet()){
			for(Mostra m2 : grafo.vertexSet()){
				if(!m1.equals(m2)){
					if(m1.getEnd()>m2.getBegin() && m1.getBegin()<m2.getBegin()){
						grafo.addEdge(m1, m2) ;
					}
				}
			}
			
		}
	}

	public Mostra getMaxOpere (int anno){
		grafo = new SimpleDirectedGraph<Mostra, DefaultEdge> (DefaultEdge.class) ;
		this.creaGrafo(anno);
		Mostra best = null ;
		int max=0;
		for(Mostra m :grafo.vertexSet()){
			this.getOpere(m) ;
			int nopere=m.getOpere().size() ;
			if(nopere>max){
				max=nopere;
				best =m ;
			}
		}
		return best ;
	}
	public List<ArtObject> getOpere(Mostra m){
		m.setOpere( dao.getOpere(m));
		return m.getOpere() ;
		
	}
	
	public boolean isConnected(){
		ConnectivityInspector <Mostra, DefaultEdge> magia = new ConnectivityInspector<Mostra, DefaultEdge>(grafo);
		return magia.isGraphConnected() ;
	}
	private List<Mostra> getMostreDatoAnno(int anno) {
		return dao.getMostreSuccessive(anno);
	}
}
