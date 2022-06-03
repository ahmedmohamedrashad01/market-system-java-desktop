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

public class DeletedUsersController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblSallary;

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
    private TableView<Display> myTable;

    @FXML
    private TableColumn<Display, Integer> colID;

    @FXML
    private TableColumn<Display, String> colName;

    @FXML
    private TableColumn<Display, String> colMobile;

    @FXML
    private TableColumn<Display, String> colAddress;

    @FXML
    private TableColumn<Display, String> colIdNumber;

    @FXML
    private TableColumn<Display, Double> colSallary;

    @FXML
    private TableColumn<Display, Date> colDate;

    @FXML
    private TableColumn<Display, String> colOldDate;

    @FXML
    private JFXButton btnSearchByName;

    @FXML
    private JFXTextField txtName;

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
    void btnExitAction(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
       
        LocalDate newDate = LocalDate.now();
        try{
             if (fromDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ من").showAndWait();
        } else if (toDate.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر التاريخ الى").showAndWait();
        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from deletedusers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                   data.add((new Display(r.getInt("id"),r.getString("name"),r.getString("mobile"),r.getString("address"),r.getString("idNumber"),r.getDouble("sallary"),r.getDate("date"),r.getString("oldDate"))));
                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));

                myTable.setItems(data);

                SearchSallary();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    final ObservableList<Display> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayUserData();
        displayQTYOnly();
    }

    
    /////////////////////////
    
     public void SearchSallary() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM deletedusers where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblSallary.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    
     public void setQtyForSearchName() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM deletedusers where name = '" + txtName.getText().trim() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblSallary.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

     
      public void displayQTYOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM deletedusers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblSallary.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    ////////////////////////////
    public void displayUserData() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from deletedusers");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                 data.add((new Display(r.getInt("id"),r.getString("name"),r.getString("mobile"),r.getString("address"),r.getString("idNumber"),r.getDouble("sallary"),r.getDate("date"),r.getString("oldDate"))));
                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));

            myTable.setItems(data);
            displayQTYOnly();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void searchByName() {

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم الموظف").show();

        } else if (txtName.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم الموظف حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from deletedusers where name = '" + txtName.getText().trim() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add((new Display(r.getInt("id"),r.getString("name"),r.getString("mobile"),r.getString("address"),r.getString("idNumber"),r.getDouble("sallary"),r.getDate("date"),r.getString("oldDate"))));
                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colOldDate.setCellValueFactory(new PropertyValueFactory<>("oldDate"));

                myTable.setItems(data);

                //SearchSallary();
                setQtyForSearchName();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
    
    public class Display{
        int id;
        String name;
        String mobile;
        String address;
        String idNumber;
        double sallary;
        Date date;
        String oldDate;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public double getSallary() {
            return sallary;
        }

        public void setSallary(double sallary) {
            this.sallary = sallary;
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

        public Display(int id, String name, String mobile, String address, String idNumber, double sallary, Date date, String oldDate) {
            this.id = id;
            this.name = name;
            this.mobile = mobile;
            this.address = address;
            this.idNumber = idNumber;
            this.sallary = sallary;
            this.date = date;
            this.oldDate = oldDate;
        }
        
    }
}
