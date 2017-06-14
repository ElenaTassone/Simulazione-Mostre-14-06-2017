package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {
	
	private Map <Integer, Mostra> mostreMap;

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Mostra> getMostre (){
		
		if(mostreMap != null){
			
			//possibileerrore con set
			return (List<Mostra>) mostreMap.values();
		}
		
		mostreMap=new TreeMap<Integer, Mostra> ();
		String sql = "SELECT * from exhibitions";

		List<Mostra> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				Mostra temp = new Mostra ( res.getInt("exhibition_id"), 
						res.getString("exhibition_department"),
						res.getString("exhibition_title"),
						res.getInt("begin"),
						res.getInt("end")) ;
				result.add(temp) ;
				mostreMap.put(temp.getExhibition_id(), temp);				
				
			}
				conn.close();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	public List<Mostra> getMostreSuccessive(int anno){
		
		String sql = "SELECT * from exhibitions WHERE begin>=?";

		List<Mostra> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Mostra temp = new Mostra ( res.getInt("exhibition_id"), 
						res.getString("exhibition_department"),
						res.getString("exhibition_title"),
						res.getInt("begin"),
						res.getInt("end")) ;
				result.add(temp) ;
							
				
			}
				conn.close();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	public List<Mostra> getSuccessiveMostra(Mostra m, int anno) {
		String sql = "SELECT * from exhibitions WHERE begin<=? And begin>=?";

		List<Mostra> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, m.getEnd());
			st.setInt(2, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Mostra temp = new Mostra ( res.getInt("exhibition_id"), 
						res.getString("exhibition_department"),
						res.getString("exhibition_title"),
						res.getInt("begin"),
						res.getInt("end")) ;
				result.add(temp) ;
							
				
			}
				conn.close();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	public List<ArtObject> getOpere(Mostra m){
		String sql = "SELECT * FROM objects AS o,exhibition_objects AS m"
				+ " where o.object_id=m.object_id and m.exhibition_id=?" ; 
		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, m.getExhibition_id());
			ResultSet res = st.executeQuery();

			while (res.next()) { 
				
				ArtObject temp = new ArtObject(res.getInt("object_id"),
						res.getString("classification"),  res.getString("continent"), 
						res.getString("country"),  res.getInt("curator_approved"), 
						res.getString("dated"),  res.getString("department"), 
						res.getString("medium"),  res.getString("nationality"),
						res.getString("object_name"),  res.getInt("restricted"), 
						res.getString("rights_type"),  res.getString("role"),
						res.getString("room"), res.getString( "style"),
						res.getString("title")); 
				
				result.add(temp) ;
							
				
			}
				conn.close();
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
}
