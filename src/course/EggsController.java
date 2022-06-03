package course;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class EggsController implements Initializable {

    
    public static String user ="";
    
    
      @FXML
    private JFXButton btnDispaySales;

      
        @FXML
    void btnDispaySalesAction(ActionEvent event) {
          try {
              Display_salesController.user = user;
              Parent p = FXMLLoader.load(getClass().getResource("Display_sales.fxml"));
              root2.getChildren().setAll(p);
          } catch (IOException ex) {
              Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
     @FXML
    private JFXButton btnDisplayPurchases;

      @FXML
    void btnDisplayPurchasesAction(ActionEvent event) {
        try{
            
            Display_purchasesController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Display_purchases.fxml"));
            root2.getChildren().setAll(p);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    @FXML
    private JFXButton btnPurchaseBaladiEggs;

    @FXML
    private JFXButton btnPurchaseRedEggs;
    @FXML
    private JFXButton btnPurchaseWhiteEggs;

    @FXML
    void btnPurchaseRedEggsAction(ActionEvent event) {
        try {
            Purchases_of_eggsController a = new Purchases_of_eggsController();
            a.color = "أحمر";
            
            Purchases_of_eggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Purchases_of_eggs.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnPurchaseWhiteEggsAction(ActionEvent event) {
        try {
            Purchases_of_eggsController a = new Purchases_of_eggsController();
            a.color = "أبيض";
            Purchases_of_eggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Purchases_of_eggs.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnPurchaseBaladiEggsAction(ActionEvent event) {
         try {
            Purchases_of_eggs_BaladiController a = new Purchases_of_eggs_BaladiController();
            a.color = "بلدى";
            
            Purchases_of_eggs_BaladiController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Purchases_of_eggs_Baladi.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnAddWhite;

    @FXML
    private JFXButton btnAddBaladi;

    @FXML
    void btnAddBaladiAction(ActionEvent event) {
        try {
            Add_Eggs_BaladiController a = new Add_Eggs_BaladiController();
            a.color = "بلدى";
            
            Add_Eggs_BaladiController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Add_Eggs_Baladi.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnAddWhite(ActionEvent event) {
        try {

            Add_EggsController a = new Add_EggsController();
            a.color = "أبيض";
            
            Add_EggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Add_Eggs.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private AnchorPane root2;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnBack;

    @FXML
    void btnAddAction(ActionEvent event) {

        try {

            Add_EggsController a = new Add_EggsController();
            a.color = "أحمر";
            
            Add_EggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Add_Eggs.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Select_itemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
            root2.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(EggsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(user);
    }

}
