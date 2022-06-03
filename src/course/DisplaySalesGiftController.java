package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DisplaySalesGiftController implements Initializable {

    public static String user ="";
    
    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    @FXML
    private JFXComboBox<String> cbxType;

    @FXML
    void cbxTypeAction(ActionEvent event) {

        if (cbxType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر حجم المنتج").show();
        } else {

            String type = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from sales_gift where item = '" + type + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new salesGift(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItemName.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);

                lblPrice.setText("");

                lblQty.setText("");

                displayQTYOnlyAfterCBXsELECTED(type);
                displayPRICEOnlyAfterCBXsELECTED(type);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    private AnchorPane root;

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
    private TableView<salesGift> myTable;

    @FXML
    private TableColumn<salesGift, Integer> colID;

    @FXML
    private TableColumn<salesGift, String> colName;

    @FXML
    private TableColumn<salesGift, String> colItemName;

    @FXML
    private TableColumn<salesGift, Double> colQty;

    @FXML
    private TableColumn<salesGift, Date> colDate;

    @FXML
    private TableColumn<salesGift, Double> colPrice;
    
     @FXML
    private TableColumn<salesGift, String> colUser;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            GiftController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Gift.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        
        LocalDate date = LocalDate.now();
        
        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لحذفه").showAndWait();

        } else {
            try {
                String name = myTable.getSelectionModel().getSelectedItem().getName();
                String item = myTable.getSelectionModel().getSelectedItem().getItem();
                Double qty = myTable.getSelectionModel().getSelectedItem().getQty();
                 Double price = myTable.getSelectionModel().getSelectedItem().getPrice();
                 String oldDate = myTable.getSelectionModel().getSelectedItem().getDate().toString();
                 
                
                int i = myTable.getSelectionModel().getSelectedItem().getId();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("delete from sales_gift where id = '" + i + "'");
                pst.executeUpdate();
                String query2 = "UPDATE purchase_gift_fake SET qty =  qty + '" + qty + "' WHERE item = '" + item + "'";
                d.stmt.executeUpdate(query2);
                myTable.getItems().remove(index);
                new Alert(Alert.AlertType.ERROR, "تم الحذف").showAndWait();
                
                d.GiftsalesDeleted(i, name, item, qty, price, date,oldDate,user);
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void btnEditAction(ActionEvent event) {

        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لتعديله").show();

        } else {
            // Edit_purchasesController ep = new Edit_purchasesController();
            EditSalesGiftController.id = myTable.getSelectionModel().getSelectedItem().getId();
            EditSalesGiftController.name = myTable.getSelectionModel().getSelectedItem().getName();

            EditSalesGiftController.item = myTable.getSelectionModel().getSelectedItem().getItem();
            EditSalesGiftController.qty = myTable.getSelectionModel().getSelectedItem().getQty();
            EditSalesGiftController.date = myTable.getSelectionModel().getSelectedItem().getDate();
            EditSalesGiftController.price = myTable.getSelectionModel().getSelectedItem().getPrice();

            try {
                EditSalesGiftController.user = user;
                Parent p = FXMLLoader.load(getClass().getResource("EditSalesGift.fxml"));
                root.getChildren().setAll(p);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

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
                PreparedStatement pst = d.con.prepareStatement("select * from sales_gift where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new salesGift(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItemName.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);
                SearchQTY();
                searchPrice();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    final ObservableList<salesGift> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        System.out.println(user);
        
        colID.setReorderable(false);
        colName.setReorderable(false);

        colItemName.setReorderable(false);
        colQty.setReorderable(false);
        colDate.setReorderable(false);
        colPrice.setReorderable(false);
        colUser.setReorderable(false);

        displayPurchases();
        displayQTYOnly();
        displayPriceOnly();

        setItemsIntoCBX();
        System.out.println(user);
    }

    public void displayPurchases() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from sales_gift");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                data.add(new salesGift(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItemName.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

            myTable.setItems(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayQTYOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM sales_gift");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayPriceOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM sales_gift");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    ////////////////////////////
    public void displayQTYOnlyAfterCBXsELECTED(String type) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM sales_gift where item = '" + type + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ///////////////////
    public void displayPRICEOnlyAfterCBXsELECTED(String type) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM sales_gift where item = '" + type + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setItemsIntoCBX() {
        ArrayList<String> a = new ArrayList<>();
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT * FROM sales_gift");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                a.add(r.getString("item"));

            }

            HashSet<String> set = new HashSet<>(a);
            a.clear();
            a.addAll(set);

            cbxType.getItems().addAll(a);

        } catch (SQLException ex) {
            Logger.getLogger(DisplaySalesGiftController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// عرض الكميه والسعر بعد اختيار التاريخ
    public void SearchQTY() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM sales_gift where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchPrice() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM sales_gift where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchByName() {

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم المستهلك").show();

        } else if (txtName.getText().matches("[0-90]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم المستخدم حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from sales_gift where name = '" + txtName.getText() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add(new salesGift(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItemName.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);
                SearchQTY();
                searchPrice();
                setQtyForSearchName();
                setPrice();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void setQtyForSearchName() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM sales_gift where name = '" + txtName.getText() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setPrice() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM sales_gift where name = '" + txtName.getText() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
