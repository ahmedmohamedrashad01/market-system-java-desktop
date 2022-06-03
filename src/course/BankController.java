package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class BankController implements Initializable {

    double total = 0.0;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtWithdrawal;

    @FXML
    private JFXDatePicker dateWithdrawal;

    @FXML
    private JFXButton btnWithdrawal;

    @FXML
    private JFXTextField txtDeposit;

    @FXML
    private JFXDatePicker dateDeposit;

    @FXML
    private JFXButton btnDeposit;

    @FXML
    private JFXButton btnDisplayWithdrawal;

    @FXML
    private JFXButton btnDisplayDeposit;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXButton btnTotal;
    
      @FXML
    private Label lblPrice;

    @FXML
    void btnBackAction(ActionEvent event) {
          try {
            
              Parent p = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnDepositAction(ActionEvent event) {
        try {
            if (txtDeposit.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل مبلغ الايداع").showAndWait();
            } else if (!txtDeposit.getText().trim().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل مبلغ الايداع ارقام فقط").showAndWait();

            } else if (dateDeposit.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اختر تاريخ الايداع").showAndWait();

            } else {
                Double depositPrice = Double.parseDouble(txtDeposit.getText().trim());
                DB d = new DB();
                d.insertDeposit(depositPrice, dateDeposit.getValue());
                txtDeposit.clear();
                dateDeposit.setValue(null);
                displayPriceOnly();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void btnDisplayDepositAction(ActionEvent event) {
        try {
            
            Parent p = FXMLLoader.load(getClass().getResource("Deposit.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnDisplayWithdrawalAction(ActionEvent event) {
         try {
            
            Parent p = FXMLLoader.load(getClass().getResource("Withdrawal.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnTotalAction(ActionEvent event) {
        if (txtTotal.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل المبلغ الاجمالى").showAndWait();
        } else if (!txtTotal.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل المبلغ الاجمالى ارقام فقط").showAndWait();

        } else {
            Double total = Double.parseDouble(txtTotal.getText().trim());
            LocalDate currentDate = LocalDate.now();
            DB d = new DB();
            d.insertTotal(total, currentDate);
        }
        txtTotal.clear();
    }

    @FXML
    void btnWithdrawalAction(ActionEvent event) {

        double sum = 0.0;
        try {
            if (txtWithdrawal.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل مبلغ السحب").showAndWait();
            } else if (!txtWithdrawal.getText().trim().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل مبلغ السحب ارقام فقط").showAndWait();
            } else if (dateWithdrawal.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "من فضلك اختر تاريخ السحب").showAndWait();
            } else {
                double inputPrice = Double.parseDouble(txtWithdrawal.getText().trim());
                DB d = new DB();
                ResultSet r2 = d.con.createStatement().executeQuery("SELECT total from total_bank");

                if (r2.next()) {
                    sum += r2.getDouble("total");
                    if (inputPrice <= sum) {
                        String q = "insert into withdrawal (Withdrawal_price,date) values ('" + inputPrice + "','" + dateWithdrawal.getValue() + "')";
                        d.stmt.executeUpdate(q);
                        d.query = "UPDATE total_bank SET total = total - '" + inputPrice + "'";
                        d.stmt.executeUpdate(d.query);
                        new Alert(Alert.AlertType.INFORMATION, "تم اضافة مبلغ السحب ").showAndWait();
                        txtWithdrawal.clear();
                        dateWithdrawal.setValue(null);
                        displayPriceOnly();
                    }
                }

                if (inputPrice > sum) {
                    new Alert(Alert.AlertType.ERROR, "لا يوجد رصيد كافى فى المبلغ الاجمالى \n الرصيد المتوفر حاليا هو : " + sum).showAndWait();
                    txtWithdrawal.clear();
                    dateWithdrawal.setValue(null);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayPriceOnly();
    }

    
    public void displayPriceOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(total) AS Total FROM total_bank");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    ///////////////////////////////////////////
    
    
    public double getTotalFromDB() {
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(total) AS Total FROM total_bank");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                total += r.getDouble("Total");

            }

            System.out.println(total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }

}
