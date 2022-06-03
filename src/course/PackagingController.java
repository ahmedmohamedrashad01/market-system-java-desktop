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

public class PackagingController implements Initializable {

    public static String user ="";
    
    
     @FXML
    private JFXButton btnDisplaySalesNormalWhitePapers;
     
       @FXML
    void btnDisplaySalesNormalWhitePapersAction(ActionEvent event) {
        /*
         try {
             Display_sales_of_normal_white_papersController.color="ورق جورنال عادى";
             
             Parent p = FXMLLoader.load(getClass().getResource("Display_sales_of_normal_white_papers.fxml"));
             root5.getChildren().setAll(p);
         } catch (IOException ex) {
             Logger.getLogger(PackagingController.class.getName()).log(Level.SEVERE, null, ex);
         }
*/
    }
    
     @FXML
    private JFXButton btnDisplayNormalWhitePapers;

      @FXML
    void btnDisplayNormalWhitePapersAction(ActionEvent event) {
         try {
             Display_purchases_of_normal_white_papersController.user = user;
             Parent p = FXMLLoader.load(getClass().getResource("Display_purchases_of_normal_white_papers.fxml"));
             root5.getChildren().setAll(p);
         } catch (IOException ex) {
             Logger.getLogger(PackagingController.class.getName()).log(Level.SEVERE, null, ex);
         }
          
          
    }

    
    @FXML
    private JFXButton btnPurchasesWhitePapers;

    @FXML
    private JFXButton btnSalesWhitePapers;

      @FXML
    void btnSalesWhitePapersAction(ActionEvent event) {
         try {
             
            Sales_normal_white_papersController.color = "ورق جورنال عادى";
            
            Sales_normal_white_papersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Sales_normal_white_papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnPurchasesWhitePapersAction(ActionEvent event) {
        try {
            
            Purchases_normal_white_papersController.color = "ورق جورنال عادى";
            Purchases_normal_white_papersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Purchases_normal_white_papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private AnchorPane root5;

    @FXML
    private JFXButton btnBack;

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Select_itemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private JFXButton btnPurchasesNormalImportedPapers;
    
     @FXML
    void btnPurchasesNormalImportedPapersAction(ActionEvent event) {
         try {
             Purchases_Normal_Imported_PapersController.color="ورق مستورد عادى";
             
             Purchases_Normal_Imported_PapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("purchases_Normal_Imported_Papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
      @FXML
    private JFXButton btnPurchasesNormalLocalPapers;

        @FXML
    void btnPurchasesNormalLocalPapersAction(ActionEvent event) {
         try {
             Purchases_Normal_Local_PapersController.color="ورق محلى عادى";
             Purchases_Normal_Local_PapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("purchases_Normal_Local_Papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

     @FXML
    private JFXButton btnPurchasesJornalImportedPapers;
     
      @FXML
    void btnPurchasesJornalImportedPapersAction(ActionEvent event) {
         try {
             Purchases_Jornal_Imported_PapersController.color="ورق جورنال مستورد ";
             Purchases_Jornal_Imported_PapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("purchases_Jornal_Imported_Papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
      @FXML
    private JFXButton btnPurchasesJornalLocalPapers;
      
        @FXML
    void btnPurchasesJornalLocalPapersAction(ActionEvent event) {
         try {
             Purchases_Jornal_Local_PapersController.color="ورق جورنال محلى ";
             Purchases_Jornal_Local_PapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("purchases_Jornal_Local_Papers.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

      @FXML
    private JFXButton btnsalesNormalExportedPapers;
      
       @FXML
    void btnsalesNormalExportedPapersAction(ActionEvent event) {
        ///  Sales of NormalImportedPapers
         try {
             SalesOfNormalImportedPapersController.color="ورق مستورد عادى";
             SalesOfNormalImportedPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Sales of NormalImportedPapers.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    @FXML
    private JFXButton btnsalesOfNormalLocalPapers;
    
      @FXML
    void btnsalesOfNormalLocalPapersAction(ActionEvent event) {
          try {
             SalesLocalPapersController.color="ورق محلى عادى";
             SalesLocalPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("SalesLocalPapers.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @FXML
    private JFXButton btnSalesOfJImportedornal;
    
    @FXML
    void btnSalesOfJImportedornalAction(ActionEvent event) {
        try {
             SalesOfImportedJornalPapersController.color="ورق جورنال مستورد";
             SalesOfImportedJornalPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("SalesOfImportedJornalPapers.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
      @FXML
    private JFXButton btnLastSalesJornal;
        @FXML
    void btnLastSalesJornalAction(ActionEvent event) {
//  LastSales
        
         try {
             LastSalesController.color="ورق جورنال محلى ";
             LastSalesController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("LastSales.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /// Display All Sales
      @FXML
    private JFXButton btnDisplayS;
       @FXML
    void DisplaySAction(ActionEvent event) {
          try {
            DisplaySalesOfPapersController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesOfPapers.fxml"));
            root5.getChildren().setAll(p);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(user);
    }

}
