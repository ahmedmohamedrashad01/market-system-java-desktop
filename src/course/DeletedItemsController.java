package course;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class DeletedItemsController implements Initializable {

    public static String user ="";
    
      @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnPurchaseEggs;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnSalesEggs;

    @FXML
    private JFXButton btnSalesPapers;

    @FXML
    private JFXButton btnPurchasePapers;

    @FXML
    private JFXButton btnnPurchaseGift;

    @FXML
    private JFXButton btnSalesGift;

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Select_itemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnPurchaseEggsAction(ActionEvent event) {
         try {
             EggsDeletedController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("EggsDeleted.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnPurchasePapersAction(ActionEvent event) {
            //  PurchasesDeletedPapers
            
             try {
                 PurchasesDeletedPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("PurchasesDeletedPapers.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            
    }

    @FXML
    void btnSalesEggsAction(ActionEvent event) {
        //  EggsSalesDeleted
        
         try {
             EggsSalesDeletedController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("EggsSalesDeleted.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnSalesGiftAction(ActionEvent event) {
          try {
              SalesGiftDeletedController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("SalesGiftDeleted.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    @FXML
            
    void btnSalesPapersAction(ActionEvent event) {
          try {
              SalesDeletedPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("SalesDeletedPapers.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnnPurchaseGiftAction(ActionEvent event) {
           try {
               PurchaseGiftDeletedController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("PurchaseGiftDeleted.fxml"));
            root.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(user);
    }

}


/*


*/


/// back


/*

 
*/