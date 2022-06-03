package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static course.Edit_salesController.id;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Edit_sales_of_normal_white_papersController implements Initializable {

    
    public static String user ="";
    
    
    // Edit_sales_of_normal_white_papers
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
    double fullQTY = 0;

    public static int id = 0;
    public static String name = "";
    public static String item = "";
    public static String size = "";
    public static double qty = 0.0;
    public static String mobile = "";
    public static Date date = null;
    public static double price = 0;
    
    

    @FXML
    private JFXTextField txtName;

    @FXML
    private AnchorPane root;

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
            PackagingController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Packaging.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException e) {
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
                PreparedStatement pst = d.con.prepareStatement("SELECT qty FROM salesnewpapers where id = '" + id + "'");
                ResultSet r = pst.executeQuery();
                if (r.next()) {
                    sqlQty = r.getDouble("qty");
                    if (sqlQty == qty) {
                        String query = "UPDATE salesnewpapers SET name = '" + txtName.getText() + "',item = '"+item+"',size = '"+size+"',qty = '" + qty + "', mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                        d.stmt.executeUpdate(query);
                        new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").showAndWait();
                        r.close();
                        pst.close();
                        try {
                                DisplaySalesOfPapersController.user = user;
                                Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesOfPapers.fxml"));
                                root.getChildren().setAll(p);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }

                    }

                    else if (qty < sqlQty) {
                        Double minus = sqlQty - qty;
                        //String query = "UPDATE salesnewpapers SET name = '" + txtName.getText() + "' ,qty = '" + qty + "' , mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";
                         String query = "UPDATE salesnewpapers SET name = '" + txtName.getText() + "',item = '"+item+"',size = '"+size+"',qty = '" + qty + "', mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                        d.stmt.executeUpdate(query);

                        String query2 = "UPDATE paperqty SET qty = qty + '" + minus + "' WHERE item = '" + item + "' and size = '"+size+"'";

                        d.stmt.executeUpdate(query2);
                        new Alert(AlertType.INFORMATION, "تم تحديث البيانات").showAndWait();
                        try {
                                DisplaySalesOfPapersController.user = user;
                                Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesOfPapers.fxml"));
                                root.getChildren().setAll(p);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }

                    }

                    else if (qty > sqlQty) {

                        Double check = qty - sqlQty;
                        if (check > fullQTY) {
                            txtQty.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

                            new Alert(AlertType.ERROR, "الكميه المطلوبه غير متاحه").showAndWait();

                            try {
                                DisplaySalesOfPapersController.user = user;
                                Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesOfPapers.fxml"));
                                root.getChildren().setAll(p);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }

                            return;

                        } else {
                           
                            String query = "UPDATE salesnewpapers SET name = '" + txtName.getText() + "',item = '"+item+"',size = '"+size+"',qty = '" + qty + "', mobile = '" + txtMobile.getText() + "' , date = '" + txtDate.getValue() + "' ,price = '" + price + "' where id = '" + id + "'";

                            d.stmt.executeUpdate(query);
                           
                            String query2 = "UPDATE paperqty SET qty = qty - '" + check + "' WHERE item = '" + item + "' and size = '"+size+"'";
                            d.stmt.executeUpdate(query2);
                            new Alert(AlertType.INFORMATION, "تم تحديث البيانات").showAndWait();
                            try {
                                DisplaySalesOfPapersController.user = user;
                                Parent p = FXMLLoader.load(getClass().getResource("DisplaySalesOfPapers.fxml"));
                                root.getChildren().setAll(p);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    }

                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        selctFullQTY();
        System.out.println(fullQTY);
        int convertQty = (int) qty;
        int convertPrice = (int) price;

        txtName.setText(name);
        System.out.println(size);
        txtQty.setText(String.valueOf(convertQty));
        txtPrice.setText(String.valueOf(convertPrice));

        txtMobile.setText(mobile);
        txtDate.setValue(LocalDate.now());
        
        
        System.out.println(user);
    }

    public void selctFullQTY() {
        try {
            DB d = new DB();
            ResultSet r2 = d.con.createStatement().executeQuery("SELECT qty FROM paperqty WHERE item = '" + item + "' and size = '"+size+"'");
            if (r2.next()) {
                fullQTY += r2.getDouble("qty");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
