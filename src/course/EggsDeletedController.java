package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class EggsDeletedController implements Initializable {
/// Deleted Purchases Of Eggs

    
    public static String user ="";
    
    
    
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<displayDeletedPurchases> myTable;

    @FXML
    private TableColumn<displayDeletedPurchases, Integer> colID;

    @FXML
    private TableColumn<displayDeletedPurchases, String> colName;

    @FXML
    private TableColumn<displayDeletedPurchases, String> colItem;

    @FXML
    private TableColumn<displayDeletedPurchases, String> colSize;

    @FXML
    private TableColumn<displayDeletedPurchases, Double> colQTY;

    @FXML
    private TableColumn<displayDeletedPurchases, Double> colPrice;

    @FXML
    private TableColumn<displayDeletedPurchases, Date> colDate;

    @FXML
    private TableColumn<displayDeletedPurchases, String> colOldDate;
    
      @FXML
    private TableColumn<displayDeletedPurchases, String> colUser;

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
    private Label lblQty;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXComboBox<String> cbx;

    @FXML
    private JFXComboBox<String> cbxType;

    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnBackAction(ActionEvent event) {
          try {
              DeletedItemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("DeletedItems.fxml"));
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
    void btnSearchAction(ActionEvent event) {
              if (fromDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ من").showAndWait();
        } else if (toDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ الى").showAndWait();
        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                     data.add(new displayDeletedPurchases(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
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

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    @FXML
    void cbxAction(ActionEvent event) {
         if (cbxType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").show();
        } else if (cbxType.getValue() != null && cbx.getValue() != null) {
            String size = cbx.getSelectionModel().getSelectedItem();
            String item = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_deleted where item = '" + item + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                   data.add(new displayDeletedPurchases(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);

                displayQTYOnlyAfterCbxSelected(item, size);
                displayPriceOnlyAfterCBXSelected(item, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void cbxTypeAction(ActionEvent event) {
        if (cbx.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر حجم المنتج").show();
        } else if (cbxType.getValue() != null && cbx.getValue() != null) {
            String size = cbx.getSelectionModel().getSelectedItem();
            String item = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_deleted where item = '" + item + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                   data.add(new displayDeletedPurchases(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);
                displayQTYOnlyAfterCbxSelected(item, size);
                displayPriceOnlyAfterCBXSelected(item, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    final ObservableList<displayDeletedPurchases> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setReorderable(false);
        colName.setReorderable(false);
        colItem.setReorderable(false);
        colSize.setReorderable(false);
        colQTY.setReorderable(false);
        colPrice.setReorderable(false);
        colDate.setReorderable(false);
        colOldDate.setReorderable(false);

        displayPurchases();
        displayQTYOnly();
        displayPriceOnly();

        cbxType.getItems().addAll("أحمر", "أبيض", "بلدى");
        cbx.getItems().addAll("كبير", "متوسط", "صغير", "كيلو 300", "كيلو وربع", "كيلو 200", "كيلو 150", "كيلو 100");

        System.out.println(user);
    }

    public void displayPurchases() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from purchase_deleted");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
               data.add(new displayDeletedPurchases(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_deleted");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_deleted");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////////////////
    public void displayQTYOnlyAfterCbxSelected(String type, String size) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_deleted where item = '" + type + "' and size = '" + size + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }

            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayPriceOnlyAfterCBXSelected(String type, String size) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_deleted where item = '" + type + "' and size = '" + size + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ////////////////////////////////////////////////
    /// عرض الكميه والسعر بعد اختيار التاريخ
    public void SearchQTY() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_deleted where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم التاجر او الشركه").show();

        } else if (txtName.getText().matches("[0-90]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم التاجر او الشركه حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchase_deleted where name = '" + txtName.getText() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                   data.add(new displayDeletedPurchases(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getDouble("price"), r.getDate("date"), r.getString("oldDate"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchase_deleted where name = '" + txtName.getText() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchase_deleted where name = '" + txtName.getText() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ///////////////////////////////
///////   class  /////////////
    public class displayDeletedPurchases {

        int id;
        String name;
        String item;
        String size;
        double qty;
        double price;
        Date date;
        String oldDate;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
        String user;

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

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
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

        public displayDeletedPurchases(int id, String name, String item, String size, double qty, double price, Date date, String oldDate , String user) {
            this.id = id;
            this.name = name;
            this.item = item;
            this.size = size;
            this.qty = qty;
            this.price = price;
            this.date = date;
            this.oldDate = oldDate;
            this.user = user;
        }

    }

}
