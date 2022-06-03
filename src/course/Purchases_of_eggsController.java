package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static course.Add_EggsController.color;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Purchases_of_eggsController implements Initializable {

public static String user ="";

////////////////////// Calculator///////////////////

    double num1;
    double num2;
    double result;
    char ope;
    
    
    
    @FXML
    private Label lblResult;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnSeven;
    @FXML
    private JFXButton btnEight;

    @FXML
    private JFXButton btnNine;

    @FXML
    private JFXButton btnPlus;

    @FXML
    private JFXButton btnFour;

      @FXML
    private JFXButton btnFive;
      
        @FXML
    private JFXButton btnSix;
        
    @FXML
    void btnClearAction(ActionEvent event) {
        lblResult.setText("");
    }
    @FXML
    private JFXButton btnPlusdivminus;

    @FXML
    void btnPlusdivminusAction(ActionEvent event) {
        double num = Double.parseDouble(lblResult.getText());
        num = num * (-1);
        lblResult.setText(String.valueOf(num));
    
    
    }

    @FXML
    void btnSevenAction(ActionEvent event) {
         String s = lblResult.getText() + "7";
        lblResult.setText(s);
    }

    @FXML
    void btnEightAction(ActionEvent event) {
         String s = lblResult.getText() + "8";
        lblResult.setText(s);
    }

    @FXML
    void btnNineActiion(ActionEvent event) {
         String s = lblResult.getText() + "9";
        lblResult.setText(s);
    }

    @FXML
    void btnPlusAction(ActionEvent event) {
        num1 = Double.parseDouble(lblResult.getText());
        lblResult.setText("");
        ope = '+';
        
        
    }

    @FXML
    void btnFourAction(ActionEvent event) {
         String s = lblResult.getText() + "4";
        lblResult.setText(s);
    }
    
      @FXML
    void btnFiveAction(ActionEvent event) {
         String s = lblResult.getText() + "5";
        lblResult.setText(s);
    }
     @FXML
    void btnSixAction(ActionEvent event) {
         String s = lblResult.getText() + "6";
        lblResult.setText(s);
    }
    
      @FXML
    private JFXButton btnMinus;

      
       @FXML
    void btnMinusAction(ActionEvent event) {
         num1 = Double.parseDouble(lblResult.getText());
        lblResult.setText("");
        ope = '-';
    }
    
    @FXML
    private JFXButton btnOne;
    
     
     @FXML
    void btnOneAction(ActionEvent event) {
        String s = lblResult.getText() + "1";
        lblResult.setText(s);
    }
    
      @FXML
    private JFXButton btnTwo;
      
       @FXML
    void btnTwoAction(ActionEvent event) {
         String s = lblResult.getText() + "2";
        lblResult.setText(s);
    }
    
     @FXML
    private JFXButton btnThree;
     
        @FXML
    void btnThreeAction(ActionEvent event) {
         String s = lblResult.getText() + "3";
        lblResult.setText(s);
    }
     @FXML
    private JFXButton btnMultiaplication;
     
       @FXML
    void btnMultiaplicationAction(ActionEvent event) {
         num1 = Double.parseDouble(lblResult.getText());
        lblResult.setText("");
        ope = '*';
    }
    
    @FXML
    private JFXButton btnEqual;
    
     @FXML
    void btnEqualAction(ActionEvent event) {

        num2 = Double.parseDouble(lblResult.getText());
        switch(ope){
            case '+' : result = num1 + num2; break;
             case '-' : result = num1 - num2; break;
              case '*' : result = num1 * num2; break;
               case '/' : result = num1 / num2; break;
            
        }
        lblResult.setText(String.valueOf(result));
        
    }
    
      @FXML
    private JFXButton btnZero;
      
       @FXML
    void btnZeroAction(ActionEvent event) {
         String s = lblResult.getText() + "0";
        lblResult.setText(s);
    }
    
      @FXML
    private JFXButton btnPoint;
      
        @FXML
    void btnPointAction(ActionEvent event) {
         String s = lblResult.getText() + ".";
        lblResult.setText(s);
    }
    
     @FXML
    private JFXButton btnDiv;
     
       @FXML
    void btnDivAction(ActionEvent event) {
         num1 = Double.parseDouble(lblResult.getText());
        lblResult.setText("");
        ope = '/';
    }
    
    
    

    //////////////////End Calc///////////////////
    
    
    public static String color = "";
    String size = null;

    @FXML
    private AnchorPane root6;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXCheckBox cbxBig;

    @FXML
    private JFXCheckBox cbxMid;

    @FXML
    private JFXCheckBox cbxSmall;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            EggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Eggs.fxml"));
            root6.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        try {

            if (txtName.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل اسم المستهلك");
                a.show();
            } else if (txtName.getText().matches("[0-9|*|.|%|$|#|!|@|^|)|(|<|>|}|{|////|\\\\|\'|\"| |]+")) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل اسم المستهلك حروف فقط!!!");
                a.show();
            } else if (size == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك اختر الحجم");
                a.show();
            } else if (txtQty.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل الكميه");
                a.show();
            } else if (!txtQty.getText().matches("[0-9]+")) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل الكميه ارقام فقط!!!");
                a.show();

            } else if (txtMobile.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل رقم الهاتف");
                a.show();
            } else if (!txtMobile.getText().matches("[0-9]+")) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل رقم الهاتف ارقام فقط!!!");
                a.show();

            } else if (txtPrice.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل السعر");
                a.show();
            } else if (!txtPrice.getText().matches("[0-9]+")) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("رساله");
                a.setContentText("من فضلك أدخل السعر ارقام فقط!!!");
                a.show();
            } else {
                Double qty = Double.parseDouble(txtQty.getText());
                Double price = Double.parseDouble(txtPrice.getText());

                DB d = new DB();
                d.insertPurchasesOfEggs2(txtName.getText(), size, color, qty, txtMobile.getText(), txtDate.getValue(), price,user);
                txtName.clear();
                cbxBig.setSelected(false);
                cbxMid.setSelected(false);
                cbxSmall.setSelected(false);
                txtQty.clear();
                txtMobile.clear();
                txtPrice.clear();
                root6.requestLayout();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cbxBigAction(ActionEvent event) {
        if (cbxBig.isSelected()) {
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
            size = "كبير";
        } else {
            cbxBig.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxMidAction(ActionEvent event) {
        if (cbxMid.isSelected()) {
            cbxBig.setSelected(false);
            cbxSmall.setSelected(false);
            size = "متوسط";
        } else {
            cbxMid.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxSmallAction(ActionEvent event) {
        if (cbxSmall.isSelected()) {
            cbxBig.setSelected(false);
            cbxMid.setSelected(false);
            size = "صغير";
        } else {
            cbxSmall.setSelected(false);
            size = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDate.setValue(LocalDate.now());
        System.out.println(user);
        
    }

}
