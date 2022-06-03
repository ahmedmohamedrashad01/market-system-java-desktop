package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class Edit_purchasesController implements Initializable {

    
    public static String user ="";
    
    public static int id = 0;
    public static String name = "";
    public static String size = "";
    public static String color = "";
    public static double qty = 0;
    public static String mobile = "";
    public static Date date = null;
    public static double price = 0;

    @FXML
    private AnchorPane root11;

    @FXML
    private JFXTextField txtName;

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
    private JFXCheckBox cbxBig;

    @FXML
    private JFXCheckBox cbxKilo300;

    @FXML
    private JFXCheckBox cbxSmall;

    @FXML
    private JFXCheckBox cbxKiloWrob3;

    @FXML
    private JFXCheckBox cbxKilo200;

    @FXML
    private JFXCheckBox cbxKilo150;

    @FXML
    private JFXCheckBox cbxKilo100;

    @FXML
    private JFXCheckBox cbxMid;

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Display_purchasesController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Display_purchases.fxml"));
            root11.getChildren().setAll(p);

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
        double sqlQty = 0;
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
                PreparedStatement pst = d.con.prepareStatement("SELECT qty FROM purchases_of_eggs where id = '" + id + "'");
                ResultSet r = pst.executeQuery();
                if (r.next()) {
                    sqlQty = r.getDouble("qty");
                    if (sqlQty == qty) {
                        String query = "UPDATE purchases_of_eggs SET name = '" + txtName.getText() + "',size = '" + size + "',color = '" + color + "' ,qty = '" + qty + "' , mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                        d.stmt.executeUpdate(query);
                        new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();
                      r.close();
                      pst.close();
                    }

                    
                    
                    
                   
                    else if (qty > sqlQty) {
                        Double minus = qty - sqlQty;
                        String query = "UPDATE purchases_of_eggs SET name = '" + txtName.getText() + "',size = '" + size + "',color = '" + color + "' ,qty = '" + qty + "' , mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                        d.stmt.executeUpdate(query);
                        
                         String query2 = "UPDATE eggs_qty SET size = '" + size + "',color = '" + color + "' ,qty = qty + '" + minus + "' WHERE color = '" + color + "' and size = '" + size + "'";

                        d.stmt.executeUpdate(query2);
                        new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();

                    }
                        
                    
                    
                     else if (qty < sqlQty) {
                        Double minus = sqlQty - qty;
                        String query = "UPDATE purchases_of_eggs SET name = '" + txtName.getText() + "',size = '" + size + "',color = '" + color + "' ,qty = '" + qty + "' , mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                        d.stmt.executeUpdate(query);
                        
                         String query2 = "UPDATE eggs_qty SET size = '" + size + "',color = '" + color + "' ,qty = qty - '" + minus + "' WHERE color = '" + color + "' and size = '" + size + "'";

                        d.stmt.executeUpdate(query2);
                        new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();

                    }
                    
                    
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());


        }


                  
    }

    @FXML
    void cbxBigAction(ActionEvent event) {
        if (cbxBig.isSelected()) {
            size = "كبير";
            cbxKilo300.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxBig.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxKilo100Action(ActionEvent event) {
        if (cbxKilo100.isSelected()) {
            size = "كيلو 100";
            cbxBig.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxKilo100.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxKilo150Action(ActionEvent event) {
        if (cbxKilo150.isSelected()) {
            size = "كيلو 150";
            cbxBig.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxKilo150.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxKilo200Action(ActionEvent event) {
        if (cbxKilo200.isSelected()) {
            size = "كيلو 200";
            cbxBig.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxKilo200.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxKilo300Action(ActionEvent event) {
        if (cbxKilo300.isSelected()) {
            size = "كيلو 300";
            cbxBig.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxKilo300.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxKiloWrob3Action(ActionEvent event) {
        if (cbxKiloWrob3.isSelected()) {
            size = "كيلو وربع";
            cbxBig.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxMid.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxKiloWrob3.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxMidAction(ActionEvent event) {
        if (cbxMid.isSelected()) {
            size = "متوسط";
            cbxBig.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxSmall.setSelected(false);
        } else {
            cbxMid.setSelected(false);
            size = null;
        }
    }

    @FXML
    void cbxSmallAction(ActionEvent event) {
        if (cbxSmall.isSelected()) {
            size = "صغير";
            cbxBig.setSelected(false);
            cbxKilo300.setSelected(false);
            cbxKilo200.setSelected(false);
            cbxKilo150.setSelected(false);
            cbxKilo100.setSelected(false);
            cbxKiloWrob3.setSelected(false);
            cbxMid.setSelected(false);
        } else {
            cbxSmall.setSelected(false);
            size = null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int convertQty = (int)qty;
        int convertPrice = (int)price;
        
        txtName.setText(name);
        checkSize();
        checkColor();

        txtQty.setText(String.valueOf(convertQty));
        txtPrice.setText(String.valueOf(convertPrice));
       

        txtMobile.setText(mobile);
        String dateConverter = String.valueOf(date);
        txtDate.setValue(LocalDate.parse(dateConverter));

        System.out.println(user);

    }

    //check Size
    public void checkSize() {
        if (size.equalsIgnoreCase("كبير")) {
            cbxBig.setSelected(true);
        } else if (size.equalsIgnoreCase("كيلو 300")) {
            cbxKilo300.setSelected(true);
        } else if (size.equalsIgnoreCase("كيلو وربع")) {
            cbxKiloWrob3.setSelected(true);
        } else if (size.equalsIgnoreCase("كيلو200")) {
            cbxKilo200.setSelected(true);
        } else if (size.equalsIgnoreCase("كيلو 150")) {
            cbxKilo150.setSelected(true);
        } else if (size.equalsIgnoreCase("كيلو 100")) {
            cbxKilo100.setSelected(true);
        } else if (size.equalsIgnoreCase("متوسط")) {
            cbxMid.setSelected(true);
        } else if (size.equalsIgnoreCase("صغير")) {
            cbxSmall.setSelected(true);
        }

    }

    // check color
    public void checkColor() {
        if (color.equalsIgnoreCase("بلدى")) {
            cbxMid.setVisible(false);
        } else {
            cbxKilo300.setVisible(false);
            cbxKiloWrob3.setVisible(false);
            cbxKilo200.setVisible(false);
            cbxKilo150.setVisible(false);
            cbxKilo100.setVisible(false);

        }
    }
}
