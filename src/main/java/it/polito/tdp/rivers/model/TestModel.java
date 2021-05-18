package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = new Model();

		System.out.println(m.getAllRivers().get(0).getFlowAvg());
		River r = m.getAllRivers().get(0);
		m.setSim(r, 1);
		System.out.println(m.getcMedia());
		System.out.println(m.getnGiorni());		
	}

}
