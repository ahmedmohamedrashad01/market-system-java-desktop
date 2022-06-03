package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

// Display_sales_of_normal_white_papers
public class Display_sales_of_normal_white_papersController implements Initializable {

    /*
    public static String color = "";
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
    private TableView<Display_sales_of_normal_white_papers> myTable;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, Integer> colID;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, String> colName;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, Double> colQty;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, String> colMobile;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, Date> colDate;

    @FXML
    private TableColumn<Display_sales_of_normal_white_papers, Double> colPrice;

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
            Parent p = FXMLLoader.load(getClass().getResource("Packaging.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(Display_salesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        int index = myTable.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لحذفه").show();

        } else {
            try {
                Double qty = myTable.getSelectionModel().getSelectedItem().getQty();
                int i = myTable.getSelectionModel().getSelectedItem().getId();
                DB d = new DB();

                PreparedStatement pst = d.con.prepareStatement("delete from sales_white_papers where id = '" + i + "'");
                pst.executeUpdate();
                String query2 = "UPDATE purchases_white_papers SET qty =  qty + '" + qty + "' WHERE color = '" + color + "'";
                d.stmt.executeUpdate(query2);
                myTable.getItems().remove(index);
                new Alert(Alert.AlertType.ERROR, "تم الحذف").show();
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

            Edit_sales_of_normal_white_papersController.id = myTable.getSelectionModel().getSelectedItem().getId();
            Edit_sales_of_normal_white_papersController.name = myTable.getSelectionModel().getSelectedItem().getName();
            Edit_sales_of_normal_white_papersController.item = myTable.getSelectionModel().getSelectedItem().getItem();

            Edit_sales_of_normal_white_papersController.size = myTable.getSelectionModel().getSelectedItem().getSize();
            Edit_sales_of_normal_white_papersController.qty = myTable.getSelectionModel().getSelectedItem().getQty();
            Edit_sales_of_normal_white_papersController.mobile = myTable.getSelectionModel().getSelectedItem().getMobile();
            Edit_sales_of_normal_white_papersController.date = myTable.getSelectionModel().getSelectedItem().getDate();
            Edit_sales_of_normal_white_papersController.price = myTable.getSelectionModel().getSelectedItem().getPrice();

            try {
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
                PreparedStatement pst = d.con.prepareStatement("select * from sales_white_papers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {
                    data.add(new Display_sales_of_normal_white_papers(r.getInt("id"), r.getString("name"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price")));
                    lblPrice.setVisible(false);
                    lblQty.setVisible(false);
                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                myTable.setItems(data);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    final ObservableList<Display_sales_of_normal_white_papers> data = FXCollections.observableArrayList();

*/
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        /*
        
        selectFromDB();
        displayQTYOnly();
        displayPriceOnly();
        System.out.println(color);

    }

    /////////////// My Method ///////
    public void selectFromDB() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from sales_white_papers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                data.add(new Display_sales_of_normal_white_papers(r.getInt("id"), r.getString("name"), r.getDouble("qty"), r.getString("mobile"), r.getDate("date"), r.getDouble("price")));
            }
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            myTable.setItems(data);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void displayQTYOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(qty) AS Total FROM sales_white_papers");
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
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(price) AS Total FROM sales_white_papers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblPrice.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

*/
    }
}
