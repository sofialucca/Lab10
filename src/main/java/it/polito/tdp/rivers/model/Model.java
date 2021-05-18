package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private List<River> rivers;
	
	public Model() {
		dao = new RiversDAO();
		rivers = new LinkedList<>(dao.getAllRivers());
		for(River r : rivers) {
			dao.setFlows(r);
		}
	}
	
	public List<River> getAllRivers(){
		return rivers;
	}
}
