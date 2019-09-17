package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;
import model.RegProdaje;
import model.TrajanjeUgovora;

public class RegProdajeController implements Initializable {

    @FXML
    private Button exitBtn;
    @FXML
    private TextField ime;
    @FXML
    private TextField adresa;
    @FXML
    private TextField id;
    @FXML
    private ChoiceBox brzina;  
    @FXML
    private ChoiceBox protok;
    @FXML
    private ToggleGroup trajanjeUgovora;
    
    public static ArrayList<RegProdaje> lista2= new ArrayList();
    private double dragOffsetX;
    private double dragOffsetY;
    
    public static RegProdajeController reg;
    private final ObjectProperty<ArrayList<RegProdaje>> evidencija = new SimpleObjectProperty<>(this, "evidencija", new ArrayList<>());
    RegProdaje regProdaje;
    StringBuilder poruka;
       
  public ObjectProperty<ArrayList<RegProdaje>> evidencijaProperty() {
        return evidencija;
    }
     public RegProdajeController() {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  
       
        regProdaje = new RegProdaje();
        id.setDisable(true);
        ime.textProperty().bindBidirectional(regProdaje.imeProperty());
        adresa.textProperty().bindBidirectional(regProdaje.adresaProperty());
        id.textProperty().bindBidirectional(regProdaje.idProperty(), new NumberStringConverter());
        brzina.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {

                if (newValue != null) {
                 
                 switch(brzina.getSelectionModel().getSelectedItem().toString()){
                    case "2":
                        regProdaje.brzinaProperty().set(2);
                         break;
                    case "5":
                        regProdaje.brzinaProperty().set(5);
                        break;
                    case "10":
                        regProdaje.brzinaProperty().set(10);
                        break;
                    case "20":
                        regProdaje.brzinaProperty().set(20);
                        break;
                    case "50":
                        regProdaje.brzinaProperty().set(50);
                        break;
                    case "100":
                        regProdaje.brzinaProperty().set(100);
                        break;  
                    default: regProdaje.brzinaProperty().set(0);
                      
                 }
                }});
        
        protok.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
               
                if (newValue != null) {
                 
                 switch(protok.getSelectionModel().getSelectedItem().toString()){
                     case "1 GB":
                         regProdaje.protokProperty().set("1 GB");
                         break;
                    case "5 GB":
                        regProdaje.protokProperty().set("5 GB");
                        break;
                    case "10 GB":
                        regProdaje.protokProperty().set("10 GB");
                        break;
                    case "100 GB":
                        regProdaje.protokProperty().set("100 GB");
                        break;  
                    case "FLAT":
                        regProdaje.protokProperty().set("FLAT");
                        break;  
                    default: 
                      regProdaje.protokProperty().set("");
                 }
                }
        });

        trajanjeUgovora.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue != null) {
                
                ToggleButton selected = (ToggleButton) newValue;
                
                switch (selected.getId()) {
                    case "jednaGod":
                        regProdaje.trajanjeUgovoraProperty().set(TrajanjeUgovora.ONE_YEAR);
                        break;
                    case "dveGod":
                        regProdaje.trajanjeUgovoraProperty().set(TrajanjeUgovora.TWO_YEARS);
                        break;
                }
            }
        });
    
    }
    
    
        @FXML
    public void listaj(ActionEvent event) {
        try {
           URL fxmlUrl2= getClass().getClassLoader().getResource("view/listaPaketa.fxml");
           GridPane root2 = FXMLLoader.<GridPane>load(fxmlUrl2);
           Stage stage = new Stage();
           stage.setTitle("List of customers:");
           stage.initStyle(StageStyle.UNDECORATED);
           Scene scene = new Scene(root2, 500, 500);
           stage.setScene(scene);
           stage.show();
           scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragOffsetX = stage.getX() - event.getScreenX();
                dragOffsetY = stage.getY() - event.getScreenY();
            }
        });


    scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + dragOffsetX);
                stage.setY(event.getScreenY() + dragOffsetY);
            }
        });
           
        }
        catch (IOException e) {
          e.printStackTrace();
        }
    };
    
        @FXML
          public void unesi(){
        if(regProdaje.provera()){
         lista2.add(new RegProdaje(regProdaje.getId(), ime.getText(), adresa.getText(), regProdaje.getBrzina(), regProdaje.getProtok(), regProdaje.trajanjeUgovoraProperty().getValue()));
         regProdaje.increment();
         ocisti();
        } else  {
            poruka = new StringBuilder();
            ArrayList<String> listaGreski = regProdaje.greskeProperty().get();
            for (String err : listaGreski) {
            poruka.append(err);
            }
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeight(100);
            alert.setWidth(398);
            alert.initStyle(StageStyle.UNDECORATED);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/view/kupipaket.css").toExternalForm());
            alert.setHeaderText(null);
            alert.setContentText(poruka.toString());
            alert.showAndWait();
            listaGreski.clear();
        }
          }   
          
          @FXML
         public void ocisti(){
             regProdaje.imeProperty().set("");
             regProdaje.adresaProperty().set("");
             brzina.valueProperty().set(null);
             brzina.valueProperty().set(0);
             protok.valueProperty().set(null);
             protok.valueProperty().set("");
            if(trajanjeUgovora.getSelectedToggle() != null) {
            trajanjeUgovora.getSelectedToggle().setSelected(false);
            regProdaje.trajanjeUgovoraProperty().set(TrajanjeUgovora.UNDEFINED);
        } 
    }
         @FXML
         public void ugasi(){
             Stage stage = (Stage) exitBtn.getScene().getWindow();
             stage.close();
         }
}
        
