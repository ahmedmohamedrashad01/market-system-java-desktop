package course;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Course extends Application {
    public Stage myStage;
    ///Select_items.fxml
    @Override
    public void start(Stage primaryStage) {
        this.myStage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            
            
            /*
            Scene scene = new Scene(p);
            Screen screen = Screen.getPrimary();
            Rectangle2D r = screen.getBounds();
            primaryStage.setX(r.getMinX());
            primaryStage.setY(r.getMinY());
            primaryStage.setWidth(r.getWidth());
            primaryStage.setHeight(r.getHeight());
            primaryStage.setMaximized(true);
            primaryStage.setResizable(false);
            
            primaryStage.setTitle("ابو حسين للتجاره");
            primaryStage.setScene(scene);

*/
            
            
             Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("مؤسسة الرحمه للتجاره");
            primaryStage.getIcons().add(new Image("course/m.jpg"));
            
           
           // primaryStage.setMinHeight(700.0);
           // primaryStage.setMinWidth(650.0);
            
            primaryStage.getScene().getStylesheets().addAll(getClass().getResource("Style.css").toExternalForm());
           
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
