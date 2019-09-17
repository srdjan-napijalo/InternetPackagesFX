
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.RegProdaje;

public class ListController  implements Initializable {

    
    @FXML
    private Button backBtn;
    @FXML
    private TableView<RegProdaje> tableView;
    @FXML
    private TableColumn<RegProdaje, String> idId;
    @FXML
    private TableColumn<RegProdaje, String> nameId;
    @FXML
    private TableColumn<RegProdaje, String> addressId;
    @FXML
    private TableColumn<RegProdaje, String> speedId;
    @FXML
    private TableColumn<RegProdaje, String> flowId;
    @FXML
    private TableColumn<RegProdaje, String> contractId;

    ObservableList <RegProdaje> oblist = FXCollections.observableArrayList();
    
    RegProdaje rp = new RegProdaje();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
          for(int i=0; i<RegProdajeController.lista2.size();i++)
              oblist.add(RegProdajeController.lista2.get(i));
        idId.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameId.setCellValueFactory(new PropertyValueFactory<>("ime"));
        addressId.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        speedId.setCellValueFactory(new PropertyValueFactory<>("brzina"));
        flowId.setCellValueFactory(new PropertyValueFactory<>("protok"));
        contractId.setCellValueFactory(new PropertyValueFactory<>("trajanjeUgovora"));
        tableView.setItems(oblist);            
    } 
    
    @FXML
    public void obrisi(){
        oblist.remove(tableView.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    public void idiNazad(){
    Stage stage = (Stage) backBtn.getScene().getWindow();
    stage.close();
    
}
    
}
