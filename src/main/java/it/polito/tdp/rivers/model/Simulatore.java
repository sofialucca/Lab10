package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

public class Simulatore {
	
	//Eventi
	private double differenza;

	//valori fissi
	private double fMinOut;
	
	//input	
	private double capienzaTot;
	private double capienzaGiorno;
	private double fOut;
	private double fIn;
	private List<Flow> flussi;
	
	//output
	private int nGiorni;
	private double cMedia;
	
	//inizializzo
	
	public void init(River r, double k) {
		nGiorni = 0;
		cMedia = 0;
		capienzaTot = k*30*r.getFlowAvg()*86400;
		capienzaGiorno = capienzaTot/2;
		fMinOut = 0.8*r.getFlowAvg()*86400;
		flussi = r.getFlows();
	}
	
	public void run() {
		for(Flow f: flussi) {
			fIn = f.getFlow()*86400;
			double prob = Math.random();
			if(prob<=0.05) {
				fOut=10*fMinOut;
			}else {
				fOut = fMinOut;
			}
			this.differenza = fIn-fOut;
			this.capienzaGiorno+= differenza;
			if(this.capienzaGiorno>=0){
				if(this.capienzaGiorno>this.capienzaTot)
					this.capienzaGiorno=this.capienzaTot;
			}else{	
				this.capienzaGiorno = 0;
				this.nGiorni++;
			}
			cMedia += this.capienzaGiorno;
		}
		cMedia = cMedia/flussi.size();
	}

	public int getnGiorni() {
		return nGiorni;
	}

	public double getcMedia() {
		return cMedia;
	}
	
}
