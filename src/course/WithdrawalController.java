package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class WithdrawalController implements Initializable {

    double fullQTY = 0;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtDeposit;

    @FXML
    private JFXDatePicker dateDeposit;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<displayWithdrawal> myTable;

    @FXML
    private TableColumn<displayWithdrawal, Integer> colID;

    @FXML
    private TableColumn<displayWithdrawal, Double> colPrice;

    @FXML
    private TableColumn<displayWithdrawal, Date> colDate;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXDatePicker toDate;

    @FXML
    private JFXDatePicker fromDate;

    @FXML
    private JFXButton btnSearch;

    @FXML
    void btnSearchAction(ActionEvent event) {

        if (fromDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ من").showAndWait();
        } else if (toDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ الى").showAndWait();
        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from withdrawal where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new displayWithdrawal(r.getInt("id"), r.getDouble("Withdrawal_price"), r.getDate("date")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                myTable.setItems(data);
                searchPrice();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void btnBackAction(ActionEvent event) {
        try {

            Parent p = FXMLLoader.load(getClass().getResource("Bank.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnEditAction(ActionEvent event) {

        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لتعديله").show();

        } else {

            double price = myTable.getSelectionModel().getSelectedItem().getPrice();
            Date da = myTable.getSelectionModel().getSelectedItem().getDate();
            int convertPrice = (int) price;
            txtDeposit.setText(String.valueOf(convertPrice));
            dateDeposit.setValue(LocalDate.parse(String.valueOf(da)));

        }

    }

    @FXML
    void btnExitAction(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void btnSaveAction(ActionEvent event) {

        try {

            if (txtDeposit.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل المبلغ").showAndWait();
            } else if (!txtDeposit.getText().trim().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "من فضلك ادخل المبلغ ارقام فقط").showAndWait();
            } else if (dateDeposit.getValue() == null) {
                new Alert(Alert.AlertType.ERROR, "من فضلك اختر تاريخ الايداع").showAndWait();

            } else {
                double sqlQty = 0.0;
                int id = myTable.getSelectionModel().getSelectedItem().getId();
                double inputPrice = Double.parseDouble(txtDeposit.getText().trim());
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("SELECT Withdrawal_price FROM withdrawal where id = '" + id + "'");
                ResultSet r = pst.executeQuery();
                if (r.next()) {
                    sqlQty = r.getDouble("Withdrawal_price");

                }
                if (sqlQty == inputPrice) {
                    String query = "UPDATE withdrawal SET Withdrawal_price = '" + inputPrice + "', date = '" + dateDeposit.getValue() + "' where id = '" + id + "'";

                    d.stmt.executeUpdate(query);
                    new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();

                    txtDeposit.clear();
                    dateDeposit.setValue(null);
                    r.close();
                    pst.close();
                }

                if (inputPrice > sqlQty) {
                    Double minus = inputPrice - sqlQty;

                    String query = "UPDATE withdrawal SET Withdrawal_price = '" + inputPrice + "', date = '" + dateDeposit.getValue() + "' where id = '" + id + "'";

                    d.stmt.executeUpdate(query);

                    String query2 = "UPDATE total_bank SET total = total - '" + minus + "'";

                    d.stmt.executeUpdate(query2);
                    new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();
                    txtDeposit.clear();
                    dateDeposit.setValue(null);
                }

                if (inputPrice < sqlQty) {
                    Double minus = sqlQty - inputPrice;
                    String query = "UPDATE withdrawal SET Withdrawal_price = '" + inputPrice + "', date = '" + dateDeposit.getValue() + "' where id = '" + id + "'";

                    d.stmt.executeUpdate(query);

                    String query2 = "UPDATE total_bank SET total = total + '" + minus + "'";

                    d.stmt.executeUpdate(query2);
                    new Alert(Alert.AlertType.INFORMATION, "تم تحديث البيانات").show();
                    txtDeposit.clear();
                    dateDeposit.setValue(null);

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ObservableList<displayWithdrawal> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setReorderable(false);
        colPrice.setReorderable(false);
        colDate.setReorderable(false);
        displayData();
        displayPriceOnly();
    }

    public void displayPriceOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(Withdrawal_price) AS Total FROM withdrawal");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchPrice() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(Withdrawal_price) AS Total FROM withdrawal where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayData() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from withdrawal");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                data.add(new displayWithdrawal(r.getInt("id"), r.getDouble("Withdrawal_price"), r.getDate("date")));

            }

            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            myTable.setItems(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public class displayWithdrawal {

        int id;
        double price;
        Date date;

        public displayWithdrawal(int id, double price, Date date) {
            this.id = id;
            this.price = price;
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

    }

}
