package it.polito.tdp.artsmia.model;

import java.util.PriorityQueue;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;


public class Simulator {

	private PriorityQueue<Event> queue = new PriorityQueue<>() ;
	Random random = new Random();

	public void addEvent(Event e) {
		this.queue.add(e) ;
		
	}
	public void run(DirectedGraph<Mostra, DefaultEdge> grafo) {
		while(!queue.isEmpty()) {
			
			Event e = queue.remove() ;
			
			Studente s = e.getS();
			if(s.getSizeMostr()==grafo.vertexSet().size())
				break;
			Mostra m = e.getM() ;
			//aggiungo le opere allo studente
//			s.setMostra(m);
			s.addOpere(m.getOpere());
			System.out.println(s.getIdStudente()+" ha visto "+s.getOpereViste().size()+"opere presso"+m.getExhibition_id()+"\n");

//			List<Mostra> vicini =  Graphs.neighborListOf(grafo, m) ;
			List<Mostra> vicini = new ArrayList<Mostra> ();
			for(DefaultEdge d : grafo.outgoingEdgesOf(m)){
				if(!vicini.contains(grafo.getEdgeTarget(d)))
					vicini.add(grafo.getEdgeTarget(d));
			
			}
			int size = vicini.size();
			if(size!=0){
				int k = random.nextInt(size);
				Mostra successiva = vicini.get(k);
				Event nuovo = new Event(s,successiva,e.getTempo()+1);
				this.addEvent(nuovo);
			}
		}

	
	}
}
