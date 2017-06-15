package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Studente implements Comparable<Studente>{
	
	private int idStudente ;
	private List<ArtObject> opereViste ;
	private List<Mostra> mostre ;
	
	public Studente(int idStudente) {
		super();
		this.setIdStudente(idStudente);
		this.opereViste = new ArrayList<ArtObject> () ;
		this.mostre = new ArrayList<Mostra> () ;
	}

	public int getSizeMostr(){
		return this.mostre.size();
	}
	public int getIdStudente() {
		return idStudente;
	}

	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}

	public List<ArtObject> getOpereViste() {
		return opereViste;
	}

	public void addOpere(List<ArtObject> opere) {
		for(ArtObject a : opere){
			if(!this.opereViste.contains(a))
				this.opereViste.add(a);
		}
		
	}

	@Override
	public int compareTo(Studente altro) {
		int differenza = this.getOpereViste().size() - altro.getOpereViste().size() ;
		if (differenza < 0)
			return +1;
		if (differenza > 0)
			return -1;
		return 0;
	}

	public void setMostra(Mostra m) {
		if(!mostre.contains(m)){
			mostre.add(m);
			this.addOpere(m.getOpere());
			}
		
		
		
	}
	
	
}
