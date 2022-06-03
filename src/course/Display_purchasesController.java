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

public class Display_purchasesController implements Initializable {

    public static String user ="";
    
    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    ///////////////////////////////
    @FXML
    private JFXComboBox<String> cbxType;

    @FXML
    void cbxTypeAction(ActionEvent event) {
        if (cbx.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر حجم المنتج").show();
        } else if (cbxType.getValue() != null && cbx.getValue() != null) {
            String size = cbx.getSelectionModel().getSelectedItem();
            String type = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchases_of_eggs where color = '" + type + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new Display_All_Purchases(r.getInt("id"), r.getString("name"), r.getString("size"), r.getString("color"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                coleSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);
                displayQTYOnlyAfterCbxSelected(type, size);
                displayPriceOnlyAfterCBXSelected(type, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // select from combo box
    @FXML
    private JFXComboBox<String> cbx;

    @FXML
    void cbxAction(ActionEvent event) {
        if (cbxType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر نوع المنتج").show();
        } else if (cbxType.getValue() != null && cbx.getValue() != null) {
            String size = cbx.getSelectionModel().getSelectedItem();
            String type = cbxType.getSelectionModel().getSelectedItem();

            try {
                myTable.getItems().clear();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchases_of_eggs where color = '" + type + "' and size = '" + size + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                     data.add(new Display_All_Purchases(r.getInt("id"), r.getString("name"), r.getString("size"), r.getString("color"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                coleSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("user"));

                myTable.setItems(data);

                displayQTYOnlyAfterCbxSelected(type, size);
                displayPriceOnlyAfterCBXSelected(type, size);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    /////////////////

    @FXML
    private TableView<Display_All_Purchases> myTable;

    @FXML
    private TableColumn<Display_All_Purchases, Integer> colID;

    @FXML
    private TableColumn<Display_All_Purchases, String> colName;

    @FXML
    private TableColumn<Display_All_Purchases, String> coleSize;

    @FXML
    private TableColumn<Display_All_Purchases, String> colColor;

    @FXML
    private TableColumn<String, Double> colQty;

    @FXML
    private TableColumn<Display_All_Purchases, String> colMobile;

    @FXML
    private TableColumn<Display_All_Purchases, Date> colDate;

    @FXML
    private TableColumn<Display_All_Purchases, Double> colPrice;
    
       @FXML
    private TableColumn<Display_All_Purchases, String> colUser;

    @FXML
    private AnchorPane root10;
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
    private JFXButton btnEdit;

    /// Edit Row
    @FXML
    void btnEditAction(ActionEvent event) {
        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لتعديله").show();

        } else {
            // Edit_purchasesController ep = new Edit_purchasesController();
            Edit_purchasesController.id = myTable.getSelectionModel().getSelectedItem().getId();
            Edit_purchasesController.name = myTable.getSelectionModel().getSelectedItem().getName();
            Edit_purchasesController.size = myTable.getSelectionModel().getSelectedItem().getSize();
            Edit_purchasesController.color = myTable.getSelectionModel().getSelectedItem().getColor();
            Edit_purchasesController.qty = myTable.getSelectionModel().getSelectedItem().getQty();
            Edit_purchasesController.mobile = myTable.getSelectionModel().getSelectedItem().getMobile();
            Edit_purchasesController.date = myTable.getSelectionModel().getSelectedItem().getDate();
            Edit_purchasesController.price = myTable.getSelectionModel().getSelectedItem().getPrice();

            try {
                Edit_purchasesController.user = user;
                Parent p = FXMLLoader.load(getClass().getResource("Edit_purchases.fxml"));
                root10.getChildren().setAll(p);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private JFXButton btnDelete;

    ///// delete row
    @FXML
    void btnDeleteAction(ActionEvent event) {
        LocalDate date = LocalDate.now();
        
        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لحذفه").show();

        } else {
            try {
                String name = myTable.getSelectionModel().getSelectedItem().getName();
                double price = myTable.getSelectionModel().getSelectedItem().getPrice();
                String oldDate = myTable.getSelectionModel().getSelectedItem().getDate().toString();
                 
                
                
                String size = myTable.getSelectionModel().getSelectedItem().getSize();
                String color = myTable.getSelectionModel().getSelectedItem().getColor();
                Double qty = myTable.getSelectionModel().getSelectedItem().getQty();
                int i = myTable.getSelectionModel().getSelectedItem().getId();
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("delete from purchases_of_eggs where id = '" + i + "'");
                pst.executeUpdate();
                String query2 = "UPDATE eggs_qty SET qty =  qty - '" + qty + "' WHERE color = '" + color + "' and size = '" + size + "'";
                d.stmt.executeUpdate(query2);
                myTable.getItems().remove(index);
                new Alert(Alert.AlertType.ERROR, "تم الحذف").showAndWait();
                
                d.EggssPurchaseDeleted(i,name,color, size, qty, price,date,oldDate,user);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
            
        }

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
                PreparedStatement pst = d.con.prepareStatement("select * from purchases_of_eggs where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new Display_All_Purchases(r.getInt("id"), r.getString("name"), r.getString("size"), r.getString("color"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                coleSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
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

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            EggsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Eggs.fxml"));
            root10.getChildren().setAll(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    final ObservableList<Display_All_Purchases> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colID.setReorderable(false);
        colName.setReorderable(false);
        coleSize.setReorderable(false);
        colColor.setReorderable(false);
        colQty.setReorderable(false);
        colMobile.setReorderable(false);
        colDate.setReorderable(false);
        colPrice.setReorderable(false);
        colUser.setReorderable(false);
        displayPurchases();
        displayQTYOnly();
        displayPriceOnly();

        cbxType.getItems().addAll("أحمر", "أبيض", "بلدى");
        cbx.getItems().addAll("كبير", "متوسط", "صغير", "كيلو 300", "كيلو وربع", "كيلو 200", "كيلو 150", "كيلو 100");

        System.out.println(user);
        
        // desplayIntoCBX();
    }

    public void displayPurchases() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from purchases_of_eggs");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
               data.add(new Display_All_Purchases(r.getInt("id"), r.getString("name"), r.getString("size"), r.getString("color"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                coleSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchases_of_eggs");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchases_of_eggs");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void desplayIntoCBX() {

        //  cbx.getItems().addAll("ورق ابيض","ورق مستورد عادى","ورق محلى عادى","ورق جورنال مستورد","ورق جورنال محلى ");
        /*
        
        ArrayList<String> a = new ArrayList<>();
         try {
             DB d = new DB();
             PreparedStatement pst = d.con.prepareStatement("select color from purchases_of_eggs");
             ResultSet r = pst.executeQuery();
             while(r.next()){
                 a.add(r.getString("color"));
                 
             }
             cbx.getItems().addAll(a);
         } catch (SQLException ex) {
             Logger.getLogger(Display_purchasesController.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    }

    //////////////////////////////////
    public void displayQTYOnlyAfterCbxSelected(String type, String size) {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchases_of_eggs where color = '" + type + "' and size = '" + size + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchases_of_eggs where color = '" + type + "' and size = '" + size + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchases_of_eggs where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchases_of_eggs where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
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

        } else if (txtName.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم المستخدم حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from purchases_of_eggs where name = '" + txtName.getText() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add(new Display_All_Purchases(r.getInt("id"), r.getString("name"), r.getString("size"), r.getString("color"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price"),r.getString("user")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                coleSize.setCellValueFactory(new PropertyValueFactory<>("size"));
                colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM purchases_of_eggs where name = '" + txtName.getText() + "'");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM purchases_of_eggs where name = '" + txtName.getText() + "'");
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
