/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Mostra;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model m ;
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	int anno = boxAnno.getValue();
    	Mostra best = m.getMaxOpere(anno) ;
    	txtResult.clear();
    	txtResult.appendText("La mostra con il maggior numero di opere è: \n "+ best
    	+ "\n con "+best.getOpere().size());
    	txtResult.appendText("\n Il grafo èfortemente connesso?"+ m.isConnected());

    }

    @FXML
    void handleSimula(ActionEvent event) {
    	int studenti = Integer.parseInt(txtFieldStudenti.getText());
    	int anno = boxAnno.getValue();
    	List<Studente> classifica = m.creaSimulazione(studenti, anno);
    	for(Studente s : classifica)
    		txtResult.appendText(""+s.getOpereViste().size()+"\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.m = model ;
		boxAnno.getItems().addAll(m.getAnni()) ;	
		
	}
}
