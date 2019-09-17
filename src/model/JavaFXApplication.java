package model;



import java.net.URL;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFXApplication extends  Application {

        
    private double dragOffsetX;
    private double dragOffsetY;
    public static void main(String[] args) {
        
        Application.launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
       
         URL fxmlUrl= getClass().getClassLoader().getResource("view/kupiPaket.fxml");
        GridPane root = FXMLLoader.<GridPane>load(fxmlUrl);
        //root.setGridLinesVisible(true);
        Image image = new Image("view/icon.png");
        stage.getIcons().add(image);
        
        Scene scene = new Scene(root, 400, 400);
        stage.initStyle(StageStyle.UNDECORATED);
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
}
