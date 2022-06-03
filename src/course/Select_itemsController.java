package course;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class Select_itemsController implements Initializable {

   public static String user = "";
    
    
    @FXML
    private JFXButton btnChangePassword;

    @FXML
    void btnChangePassword(ActionEvent event) {
        
        // EditPassword
        
         try {
             EditPasswordController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("EditPassword.fxml"));
            root2.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private JFXButton btnDeletedItems;

    @FXML
    void btnDeletedItemsAction(ActionEvent event) {
        try {
            DeletedItemsController.user=user;
            
            Parent p = FXMLLoader.load(getClass().getResource("DeletedItems.fxml"));
            root2.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private AnchorPane root2;
    @FXML
    private JFXButton btnEggs;

    @FXML
    private JFXButton btnPackaging;

    @FXML
    void btnPackagingAction(ActionEvent event) {
        try {
            PackagingController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Packaging.fxml"));
            root2.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnEggsAction(ActionEvent event) {
        try {
            EggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Eggs.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(Select_itemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private JFXButton btnGift;

    @FXML
    void btnGiftAction(ActionEvent event) {
        try {
            GiftController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Gift.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(Select_itemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private JFXButton btnCurrentQty;

    @FXML
    void btnCurrentQty(ActionEvent event) {
        try {
            CurrentQTYController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("CurrentQTY.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println(user);
        
    }
    
    

}
