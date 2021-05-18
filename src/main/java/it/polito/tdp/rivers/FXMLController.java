/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void setInfoFiume(ActionEvent event) {
    	River river = this.boxRiver.getValue();
    	txtResult.clear();
    	this.txtEndDate.clear();
    	this.txtFMed.clear();
    	this.txtNumMeasurements.clear();
    	this.txtStartDate.clear();
    	
    	List<Flow> flows = river.getFlows();
    	txtEndDate.setText(flows.get(flows.size()-1).getDay().toString());
    	txtStartDate.setText(flows.get(0).getDay().toString());
    	txtNumMeasurements.setText(""+flows.size());
    	txtFMed.setText(String.format("%.5g%n",river.getFlowAvg()));
    	this.btnSimula.setDisable(false);
    	this.txtK.setDisable(false);
    	this.txtK.setEditable(true);
    }
    
    
    @FXML
    void getSimulazione(ActionEvent event) {
    	txtResult.clear();
    	if(!isValid()) {
    		return;
    	}
    	double k = Double.parseDouble(this.txtK.getText());
    	model.setSim(this.boxRiver.getValue(), k);
    	double media = model.getcMedia();
    	int giorni = model.getnGiorni();
    	this.txtResult.appendText("Numero di giorni in cui non si è potuta garantire l’erogazione minima: "+giorni+"\n");
    	this.txtResult.appendText("Occupazione media del bacino: "+media+" m^3");
    	

    }

    private boolean isValid() {
		
		String k = this.txtK.getText();
		if(k == null) {
			this.txtResult.setText("ERRORE: inserire un valore per il fattore di scala\n");
			return false;
		}else {
			
			try {
				double numero = Double.parseDouble(k);
				if(numero>1 || numero<=0) {
					this.txtResult.setText("ERRORE:inserire un numero compreso tra 1 e 0");
					return false;
				}
			}catch(NumberFormatException nfe) {
				this.txtResult.setText("ERRORE:inserire un numero nel campo del fattore");
				return false;
			}
		}
		return true;
	}


	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().setAll(model.getAllRivers());
    }
}
