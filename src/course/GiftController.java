package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GiftController implements Initializable {

    
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
        switch (ope) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;

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
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnAddNewItem;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXTextField txtINewItem;

    @FXML
    private JFXComboBox<String> cbxPurchase;
    @FXML
    private Pane calc;

    @FXML
    private Pane paneNewItem;

    @FXML
    private JFXCheckBox cbxCalc;

    @FXML
    private JFXTextField txtNamePur;

    @FXML
    private JFXTextField txtPricePur;

    @FXML
    private JFXTextField txtQtyPur;

    @FXML
    private JFXDatePicker txtDatePur;

    @FXML
    private JFXButton btnSavePurchase;

    @FXML
    private JFXButton btnSaveSales;

    //////// SALES ///////////////
    @FXML
    private JFXTextField TXTSALESNAME;

    @FXML
    private JFXTextField TXTSALESQTY;

    @FXML
    private JFXTextField TXTSALESPRICE;

    @FXML
    private JFXComboBox<String> CBXSALES;

    @FXML
    private JFXDatePicker TXTSALESDATE;
     @FXML
    private JFXButton btnDisplaySalesOfGift;
     @FXML
    void btnDisplaySalesOfGiftAction(ActionEvent event) {
         try {
             DisplaySalesGiftController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesGift.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    void btnSaveSalesAction(ActionEvent event) {
        if (TXTSALESNAME.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم المستهلك ").showAndWait();
        } else if (TXTSALESNAME.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "اخل اسم المستهلك حروف فقط \nغير مسموح بكتابته ارقام").showAndWait();

        } else if (CBXSALES.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").showAndWait();

        } else if (TXTSALESQTY.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل الكميه").showAndWait();

        } else if (!TXTSALESQTY.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل الكميه ارقام فقط").showAndWait();

        } else if (TXTSALESPRICE.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل سعر البيع").showAndWait();

        } else if (!TXTSALESPRICE.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل السعر البيع ارقام فقط").showAndWait();

        } else {

            Double txtQty = Double.parseDouble(TXTSALESQTY.getText());
            Double txtPri = Double.parseDouble(TXTSALESPRICE.getText());

            DB d = new DB();
            d.insertSalesOfGift(TXTSALESNAME.getText(),txtQty, CBXSALES.getValue(), TXTSALESDATE.getValue(),txtPri,user);
            TXTSALESNAME .clear();
            TXTSALESQTY.clear();
            CBXSALES.setValue(null);
            TXTSALESPRICE.clear();
        }

    }

    //////////////// PURCHASES /////////////////
    @FXML
    void btnSavePurchaseAction(ActionEvent event) {
        if (txtNamePur.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم التاجر / الشركه").showAndWait();
        } else if (txtNamePur.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "اخل اسم التاجر حروف فقط \nغير مسموح بكتابته ارقام").showAndWait();

        } else if (cbxPurchase.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").showAndWait();

        } else if (txtQtyPur.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل الكميه").showAndWait();

        } else if (!txtQtyPur.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل الكميه ارقام فقط").showAndWait();

        } else if (txtPricePur.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل سعر الشراء").showAndWait();

        } else if (!txtPricePur.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل سعر الشراء ارقام فقط").showAndWait();

        } else {
            Double txtQty = Double.parseDouble(txtQtyPur.getText());
            Double txtPri = Double.parseDouble(txtPricePur.getText());

            DB d = new DB();
            d.insertPurchasesOfGift(txtNamePur.getText(), cbxPurchase.getValue(), txtQty, txtDatePur.getValue(), txtPri,user);
            txtNamePur.clear();
            txtQtyPur.clear();
            cbxPurchase.setValue(null);
            txtPricePur.clear();
        }
    }

    @FXML
    void cbxCalcAction(ActionEvent event) {
        if (cbxCalc.isSelected()) {
            paneNewItem.setVisible(false);
            calc.setVisible(true);

        } else if (!cbxCalc.isSelected()) {
            calc.setVisible(false);
            paneNewItem.setVisible(true);
        }
    }

    @FXML
    void cbxPurchaseAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewItemAction(ActionEvent event) {
        Double q = 0.0;
        if (txtINewItem.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضل ادخل اسم المنتج \n القيمه الحاليه فارغه").showAndWait();
        } else if (txtINewItem.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "ادخل اسم المنتج بطريقه صحيحه\n غير مسموح بكتابة الارقام").showAndWait();
        } else {
            DB d = new DB();
            d.addNewGiftItem(txtINewItem.getText(), q);
            txtINewItem.clear();
            cbxPurchase.getItems().clear();
            DisplayAllGiftIems();
        }

    }

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Select_itemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private JFXButton btnDisplayPurchases;

    @FXML
    void btnDisplayPurchasesAction(ActionEvent event) {

        // DisplayPurchseGift
        try {
            DisplayPurchseGiftController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("DisplayPurchseGift.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calc.setVisible(false);
        DisplayAllGiftIems();
        txtDatePur.setDisable(true);
        txtDatePur.setValue(LocalDate.now());
        TXTSALESDATE.setDisable(true);
        TXTSALESDATE.setValue(LocalDate.now());
        
        System.out.println(user);

    }

    public void DisplayAllGiftIems() {
        ObservableList o = FXCollections.observableArrayList();
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from new_gift");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                o.add(r.getString("name"));
            }
            cbxPurchase.getItems().addAll(o);
            CBXSALES.getItems().addAll(o);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
