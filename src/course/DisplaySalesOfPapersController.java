package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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

public class DisplaySalesOfPapersController implements Initializable {

    public static String user ="";
    
    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    //   salesnewpapers
    @FXML
    private JFXComboBox<String> cbxSize;

    @FXML
    void cbxAction(ActionEvent event) {

        if (cbxSize.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر مقاس المنتج").show();
        } else if (cbx.getValue() != null && cbxSize.getValue() != null) {
            String item = cbx.getSelectionModel().getSelectedItem();
            String size = cbxSize.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from salesnewpapers where item = '" + item + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new DisplaySalesAllPapers(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));

                coleItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);

                displayQTYOnlyAfterCbxSelected(item, size);
                displayPriceOnlyAfterCBXSelected(item, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /////////////////////////////////
    @FXML
    void cbxSizeAction(ActionEvent event) {
        if (cbx.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").show();
        } else if (cbx.getValue() != null && cbx.getValue() != null) {
            String item = cbx.getSelectionModel().getSelectedItem();
            String size = cbxSize.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from salesnewpapers where item = '" + item + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                     data.add(new DisplaySalesAllPapers(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));

                coleItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                 colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);

                displayQTYOnlyAfterCbxSelected(item, size);
                displayPriceOnlyAfterCBXSelected(item, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayQTYOnlyAfterCbxSelected(String item, String size) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM salesnewpapers where item = '" + item + "' and size = '" + size + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }

            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayPriceOnlyAfterCBXSelected(String item, String size) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM salesnewpapers where item = '" + item + "' and size = '" + size + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // DisplaySalesOfPapers
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
    private TableView<DisplaySalesAllPapers> myTable;

    @FXML
    private TableColumn<DisplaySalesAllPapers, Integer> colID;

    @FXML
    private TableColumn<DisplaySalesAllPapers, String> colName;

    @FXML
    private TableColumn<DisplaySalesAllPapers, String> coleItem;

    @FXML
    private TableColumn<DisplaySalesAllPapers, Double> colQty;

    @FXML
    private TableColumn<DisplaySalesAllPapers, String> colMobile;

    @FXML
    private TableColumn<DisplaySalesAllPapers, Date> colDate;

    @FXML
    private TableColumn<DisplaySalesAllPapers, Double> colPrice;

    @FXML
    private TableColumn<DisplaySalesAllPapers, String> colSize;
    
     @FXML
    private TableColumn<DisplaySalesAllPapers, String> colUser;

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
            PackagingController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Packaging.fxml"));
            root.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        LocalDate date = LocalDate.now();
        
        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لحذفه").show();

        } else {
            try {
                String name = myTable.getSelectionModel().getSelectedItem().getName();
                String size = myTable.getSelectionModel().getSelectedItem().getSize();
                String item = myTable.getSelectionModel().getSelectedItem().getItem();
                Double qty = myTable.getSelectionModel().getSelectedItem().getQty();
                Double price = myTable.getSelectionModel().getSelectedItem().getPrice();
                 String oldDate = myTable.getSelectionModel().getSelectedItem().getDate().toString();
                
                int i = myTable.getSelectionModel().getSelectedItem().getId();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("delete from salesnewpapers where id = '" + i + "'");
                pst.executeUpdate();
                String query2 = "UPDATE paperqty SET qty =  qty + '" + qty + "' WHERE item = '" + item + "' and size = '" + size + "'";
                d.stmt.executeUpdate(query2);
                myTable.getItems().remove(index);
                new Alert(Alert.AlertType.ERROR, "تم الحذف").showAndWait();
                d.PapersalesDeleted(i, name, item, size, qty, price, date,oldDate,user);
                
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

            Edit_sales_of_normal_white_papersController.id = myTable.getSelectionModel().getSelectedItem().getId();
            Edit_sales_of_normal_white_papersController.name = myTable.getSelectionModel().getSelectedItem().getName();
            Edit_sales_of_normal_white_papersController.item = myTable.getSelectionModel().getSelectedItem().getItem();

            Edit_sales_of_normal_white_papersController.size = myTable.getSelectionModel().getSelectedItem().getSize();
            Edit_sales_of_normal_white_papersController.qty = myTable.getSelectionModel().getSelectedItem().getQty();
            Edit_sales_of_normal_white_papersController.mobile = myTable.getSelectionModel().getSelectedItem().getMobile();
            Edit_sales_of_normal_white_papersController.date = myTable.getSelectionModel().getSelectedItem().getDate();
            Edit_sales_of_normal_white_papersController.price = myTable.getSelectionModel().getSelectedItem().getPrice();

            try {
                Edit_sales_of_normal_white_papersController.user = user;
                Parent p = FXMLLoader.load(getClass().getResource("Edit_sales_of_normal_white_papers.fxml"));
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
                PreparedStatement pst = d.con.prepareStatement("select * from salesnewpapers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new DisplaySalesAllPapers(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));

                coleItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
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

    final ObservableList<DisplaySalesAllPapers> data = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<String> cbx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayAllData();
        displayQTYOnly();
        displayPriceOnly();
        colID.setReorderable(false);
        colName.setReorderable(false);
        coleItem.setReorderable(false);
        colSize.setReorderable(false);
        colQty.setReorderable(false);
        colMobile.setReorderable(false);
        colDate.setReorderable(false);
        colPrice.setReorderable(false);
        colUser.setReorderable(false);

        setItemsIntoCBX();
        setSizeIntoCBX();
        
        System.out.println(user);

    }

    public void setItemsIntoCBX() {
        ArrayList<String> a = new ArrayList<>();
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT * FROM salesnewpapers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                a.add(r.getString("item"));

            }

            HashSet<String> set = new HashSet<>(a);
            a.clear();
            a.addAll(set);

            cbx.getItems().addAll(a);

        } catch (SQLException ex) {
            Logger.getLogger(DisplaySalesGiftController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSizeIntoCBX() {
        ArrayList<String> a = new ArrayList<>();
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT * FROM salesnewpapers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                a.add(r.getString("size"));

            }

            HashSet<String> set = new HashSet<>(a);
            a.clear();
            a.addAll(set);

            cbxSize.getItems().addAll(a);

        } catch (SQLException ex) {
            Logger.getLogger(DisplaySalesGiftController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //// display al items into cbx
    public void DisplayAllData() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from salesnewpapers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                 data.add(new DisplaySalesAllPapers(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));

                coleItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM salesnewpapers");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM salesnewpapers");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM salesnewpapers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM salesnewpapers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
                PreparedStatement pst = d.con.prepareStatement("select * from salesnewpapers where name = '" + txtName.getText() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add(new DisplaySalesAllPapers(r.getInt("id"), r.getString("name"), r.getString("item"), r.getString("size"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"), r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));

                coleItem.setCellValueFactory(new PropertyValueFactory<>("item"));
                colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM salesnewpapers where name = '" + txtName.getText() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM salesnewpapers where name = '" + txtName.getText() + "'");
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
