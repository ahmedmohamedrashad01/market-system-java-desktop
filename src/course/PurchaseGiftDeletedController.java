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

public class PurchaseGiftDeletedController implements Initializable {

    public static String user ="";
    
    
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
    private TableView<PurchaseGiftDeleted> myTable;

    @FXML
    private TableColumn<PurchaseGiftDeleted, Integer> colID;

    @FXML
    private TableColumn<PurchaseGiftDeleted, String> colName;

    @FXML
    private TableColumn<PurchaseGiftDeleted, String> colItem;

    @FXML
    private TableColumn<PurchaseGiftDeleted, Double> colQTY;

    @FXML
    private TableColumn<PurchaseGiftDeleted, Double> colPrice;

    @FXML
    private TableColumn<PurchaseGiftDeleted, Date> colDate;

    @FXML
    private TableColumn<PurchaseGiftDeleted, String> colOldDate;
    
     @FXML
    private TableColumn<PurchaseGiftDeleted, String> colUser;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXComboBox<String> cbxType;

    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;
    
   

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

   

    @FXML
    void cbxTypeAction(ActionEvent event) {

        if (cbxType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").show();
        } else {

            String type = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_gift_deleted where item = '" + type + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new PurchaseGiftDeleted(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
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
    void btnBackAction(ActionEvent event) {
        try {
            DeletedItemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("DeletedItems.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_gift_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new PurchaseGiftDeleted(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);
                SearchQTY();
                searchPrice();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    final ObservableList<PurchaseGiftDeleted> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colID.setReorderable(false);
        colName.setReorderable(false);

        colItem.setReorderable(false);
        colQTY.setReorderable(false);
        colDate.setReorderable(false);
        colPrice.setReorderable(false);
        colOldDate.setReorderable(false);

        displayPurchases();
        displayQTYOnly();
        displayPriceOnly();

        setItemsIntoCBX();

        System.out.println(user);
        
    }

    public void displayQTYOnlyAfterCBXsELECTED(String type) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_gift_deleted where item = '" + type + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_gift_deleted where item = '" + type + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT * FROM purchase_gift_deleted");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                a.add(r.getString("item"));

            }

            HashSet<String> set = new HashSet<>(a);
            a.clear();
            a.addAll(set);

            cbxType.getItems().addAll(a);

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void displayPurchases() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from purchase_gift_deleted");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                data.add(new PurchaseGiftDeleted(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_gift_deleted");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_gift_deleted");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /// عرض الكميه والسعر بعد اختيار التاريخ
    public void SearchQTY() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_gift_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_gift_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////
    public void searchByName() {

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم التاجر").show();

        } else if (txtName.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم التاجر حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_gift_deleted where name = '" + txtName.getText() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                   data.add(new PurchaseGiftDeleted(r.getInt("id"), r.getString("name"), r.getString("item"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_gift_deleted where name = '" + txtName.getText() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_gift_deleted where name = '" + txtName.getText() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ///////////////// class purchases gift deleted 
    public class PurchaseGiftDeleted {

        int id;
        String name;
        String item;
        double qty;
        double price;
        Date date;
        String oldDate;
        String user;
        
        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
        
        

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
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

        public String getOldDate() {
            return oldDate;
        }

        public void setOldDate(String oldDate) {
            this.oldDate = oldDate;
        }

        public PurchaseGiftDeleted(int id, String name, String item, double qty, double price, Date date, String oldDate, String user) {
            this.id = id;
            this.name = name;
            this.item = item;
            this.qty = qty;
            this.price = price;
            this.date = date;
            this.oldDate = oldDate;
            this.user = user;
        }

    }
}
