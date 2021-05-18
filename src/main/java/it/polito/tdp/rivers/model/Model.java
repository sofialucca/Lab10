package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private List<River> rivers;
	private Simulatore sim;
	
	public Model() {
		dao = new RiversDAO();
		rivers = new LinkedList<>(dao.getAllRivers());
		for(River r : rivers) {
			dao.setFlows(r);
		}
		sim = new Simulatore();
	}
	
	public List<River> getAllRivers(){
		return rivers;
	}
	
	public void setSim(River r, double k) {
		sim.init(r, k);
		sim.run();
	}
	
	public int getnGiorni() {
		return sim.getnGiorni();
	}
	
	public double getcMedia() {
		return sim.getcMedia();
	}
}
