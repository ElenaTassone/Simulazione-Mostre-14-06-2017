package it.polito.tdp.artsmia.model;

public class Event implements Comparable<Event> {

	private Studente s ;
	private Mostra m ;
	private int tempo ;
	
	public Event(Studente s, Mostra m, int tempo) {
		super();
		this.s = s;
		this.m = m;
		this.tempo = tempo;
	}

	public Studente getS() {
		return s;
	}
	public void setS(Studente s) {
		this.s = s;
	}
	public Mostra getM() {
		return m;
	}
	public void setM(Mostra m) {
		this.m = m;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int compareTo(Event o) {
		
		return this.tempo-o.tempo;
	}
}
