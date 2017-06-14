package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class TestArtsmiaDAO {

	public static void main(String[] args) {
		
		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		
		List<ArtObject> objects = dao.listObject() ;
		System.out.println(objects.size());
		
		List<Mostra> mostre = dao.getMostreSuccessive(1925);
		System.out.println(mostre.get(0).getExhibition_id());
		List<Mostra> mostre2=dao.getSuccessiveMostra(mostre.get(0),1925);
		System.out.println(mostre2.size());

		System.out.println(dao.getOpere(mostre.get(0)));
	}

}
