package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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

public class CreateUserController implements Initializable {

    public static String Usergender = null;

    
    @FXML
    private JFXButton btnBank;
    
    
       @FXML
    void btnBankAction(ActionEvent event) {

         try {
            Parent p = FXMLLoader.load(getClass().getResource("Bank.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
         
    }
    
    
    @FXML
    private AnchorPane root;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXCheckBox cbxMale;

    @FXML
    private JFXCheckBox cbxFemale;

    @FXML
    private JFXTextField txtIdNumber;

    @FXML
    private JFXTextField txtName;
    
      @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtSallary;

    @FXML
    private TableView<displayUsers> myTable;

    @FXML
    private TableColumn<displayUsers, Integer> colID;

    @FXML
    private TableColumn<displayUsers, String> colName;

    @FXML
    private TableColumn<displayUsers, String> colMobile;

    @FXML
    private TableColumn<displayUsers, String> colAddress;

    @FXML
    private TableColumn<displayUsers, String> colIDNumber;

    @FXML
    private TableColumn<displayUsers, Double> colSallary;

    @FXML
    private TableColumn<displayUsers, String> colGender;

    @FXML
    private TableColumn<displayUsers, Date> colDate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;

    @FXML
    void btnBackAction(ActionEvent event) {

        try {
            Parent p = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        try {
            LocalDate date = LocalDate.now();
            int index = myTable.getSelectionModel().getSelectedIndex();

            if (index < 0) {
                new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لحذفه").showAndWait();

            } else {

                int i = myTable.getSelectionModel().getSelectedItem().getId();
                String name = myTable.getSelectionModel().getSelectedItem().getName();
                String mobile = myTable.getSelectionModel().getSelectedItem().getMobile();
                String address = myTable.getSelectionModel().getSelectedItem().getAddress();
                String idNumber = myTable.getSelectionModel().getSelectedItem().getIdNumber();
                Double sallary = myTable.getSelectionModel().getSelectedItem().getSallary();
                String gender = myTable.getSelectionModel().getSelectedItem().getGender();

                String oldDate = myTable.getSelectionModel().getSelectedItem().getDate().toString();

                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("delete from users where id = '" + i + "'");
                pst.executeUpdate();
                String q = "insert into deletedusers (name,mobile,address,idNumber,sallary,gender,date,oldDate) values ('" + name + "','" + mobile + "','" + address + "','" + idNumber + "','" + sallary + "','" + gender + "' , '" + date + "','" + oldDate + "')";

                d.stmt.executeUpdate(q);
                myTable.getItems().remove(index);
                new Alert(Alert.AlertType.INFORMATION, "تم حذف المستخدم").showAndWait();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void cbxFemaleAction(ActionEvent event) {
        if (cbxFemale.isSelected()) {
            cbxMale.setSelected(false);
            Usergender = "انثى";
        } else {
            cbxFemale.setSelected(false);
            Usergender = null;
        }
    }

    @FXML
    void cbxMaleAction(ActionEvent event) {
        if (cbxMale.isSelected()) {
            cbxFemale.setSelected(false);
            Usergender = "ذكر";
        } else {
            cbxMale.setSelected(false);
            Usergender = null;
        }
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        try {

            int index = myTable.getSelectionModel().getSelectedIndex();

            if (index < 0) {
                new Alert(Alert.AlertType.ERROR, "من فضلك اختر بيان لتعديله").showAndWait();

            } else {

                int id = myTable.getSelectionModel().getSelectedItem().getId();
                String gender = myTable.getSelectionModel().getSelectedItem().getGender();

                txtName.setText(myTable.getSelectionModel().getSelectedItem().getName());
                txtMobile.setText(myTable.getSelectionModel().getSelectedItem().getMobile());

                txtAddress.setText(myTable.getSelectionModel().getSelectedItem().getAddress());
                txtIdNumber.setText(myTable.getSelectionModel().getSelectedItem().getIdNumber());
                int s = (int) myTable.getSelectionModel().getSelectedItem().getSallary();
                txtSallary.setText(String.valueOf(s));

                txtName.setText(myTable.getSelectionModel().getSelectedItem().getName());
                txtName.setText(myTable.getSelectionModel().getSelectedItem().getName());

                btnSaveEDit.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    ////////////////////////////////////////////////
    @FXML
    void btnSaveAction(ActionEvent event) {

        
        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال اسم الموظف / الموظفه").showAndWait();

        } else if (txtName.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال اسم الموظف حروف فقط").showAndWait();
        } else if (txtMobile.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال  رقم الهاتف").showAndWait();

        } else if (!txtMobile.getText().trim().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال  رقم الهاتف ارقام فقط").showAndWait();

        } else if (txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال العنوان").showAndWait();

        } else if (txtIdNumber.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال رقم البطاقه الشخصيه").showAndWait();

        } else if (!txtIdNumber.getText().trim().matches("\\d{14}")) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال رقم البطاقه الشخصيه ارقام فقط \n مكونه من 14 رقم").showAndWait();

        } else if (txtSallary.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال قيمة الراتب").showAndWait();

        } else if (!txtSallary.getText().trim().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "الرجاء ادخال قيمة الراتب ارقام فقط").showAndWait();

        } else if (Usergender == null) {
            new Alert(Alert.AlertType.ERROR, "من فضلك اختر النوع").showAndWait();

        } else {
            Double sallary = Double.parseDouble(txtSallary.getText().trim());
            DB d = new DB();
            d.createUser(txtName.getText().trim(), "111111", txtMobile.getText().trim(), txtAddress.getText().trim(), txtIdNumber.getText().trim(), sallary, Usergender, txtDate.getValue());
            myTable.getItems().clear();
            displayUserData();

            txtName.clear();
            txtMobile.clear();
            txtAddress.clear();
            txtIdNumber.clear();
            txtSallary.clear();
            cbxMale.setSelected(false);
            cbxFemale.setSelected(false);

        }

    }

    @FXML
    private JFXButton btnSaveEDit;

    @FXML
    void btnSaveEDitAction(ActionEvent event) {
        try {
            int id = myTable.getSelectionModel().getSelectedItem().getId();

            if (txtName.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال اسم المستخدم").showAndWait();

            } else if (txtName.getText().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال اسم المستخدم حروف فقط").showAndWait();
            } else if (txtMobile.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال  رقم الهاتف").showAndWait();

            } else if (!txtMobile.getText().trim().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال  رقم الهاتف ارقام فقط").showAndWait();

            } else if (txtAddress.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال العنوان").showAndWait();

            } else if (txtIdNumber.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال رقم البطاقه الشخصيه").showAndWait();

            } else if (!txtIdNumber.getText().trim().matches("\\d{14}")) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال رقم البطاقه الشخصيه ارقام فقط \n مكونه من 14 رقم").showAndWait();

            } else if (txtSallary.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال قيمة الراتب").showAndWait();

            } else if (!txtSallary.getText().trim().matches("[0-9]+")) {
                new Alert(Alert.AlertType.ERROR, "الرجاء ادخال قيمة الراتب ارقام فقط").showAndWait();

            } else if (Usergender == null) {
                new Alert(Alert.AlertType.ERROR, "اختر النوع").showAndWait();
            } else {
                Double sallary = Double.parseDouble(txtSallary.getText().trim());

                DB d = new DB();

                String query = "UPDATE users SET name = '" + txtName.getText().trim() + "' , mobile = '" + txtMobile.getText().trim() + "' , address = '" + txtAddress.getText().trim() + "' , idNumber = '" + txtIdNumber.getText().trim() + "' , sallary = '" + txtSallary.getText().trim() + "' , gender = '" + Usergender + "' , date = '" + txtDate.getValue() + "' where id = '" + id + "'";
                d.stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم تحديث بيانات الموظف / الموظفه").showAndWait();
                myTable.getItems().clear();
                displayUserData();

                txtName.clear();
                txtMobile.clear();
                txtAddress.clear();
                txtIdNumber.clear();
                txtSallary.clear();
                cbxMale.setSelected(false);
                cbxFemale.setSelected(false);

                btnSaveEDit.setVisible(false);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    ////////////////////////////////////////////////
   

   

    //// Display Deleted Users
    @FXML
    private JFXButton btnDisplayDeletedUsers;

    @FXML
    void btnDisplayDeletedUsersAction(ActionEvent event) {

        try {
            Parent p = FXMLLoader.load(getClass().getResource("DeletedUsers.fxml"));
            root.getChildren().setAll(p);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    final ObservableList<displayUsers> data = FXCollections.observableArrayList();

    @FXML
    private JFXDatePicker toDate;

    @FXML
    private JFXDatePicker fromDate;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private Label lblQty;

    @FXML
    private JFXButton btnSearchByName;

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
                PreparedStatement pst = d.con.prepareStatement("select * from users where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add(new displayUsers(r.getInt("id"), r.getString("name"), r.getString("mobile"), r.getString("address"), r.getString("idNumber"), r.getDouble("sallary"), r.getString("gender"), r.getDate("date")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colIDNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
                colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

                myTable.setItems(data);

                SearchSallary();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void btnSearchByNameAction(ActionEvent event) {
        searchByName();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayUserData();
        txtDate.setValue(LocalDate.now());

        btnSaveEDit.setVisible(false);

    }

    public void SearchSallary() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM users where date between '" + fromDate.getValue() + "' and '" + toDate.getValue() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    
     public void setQtyForSearchName() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM users where name = '" + txtSearch.getText().trim() + "'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

     
      public void displayQTYOnly() {
        double sum = 0;
        try {

            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("SELECT SUM(sallary) AS Total FROM users");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                sum += r.getDouble("Total");

            }
            lblQty.setText(String.valueOf(sum));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    ////////////////////////////
    public void displayUserData() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from users");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                data.add(new displayUsers(r.getInt("id"), r.getString("name"), r.getString("mobile"), r.getString("address"), r.getString("idNumber"), r.getDouble("sallary"), r.getString("gender"), r.getDate("date")));

            }

            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colIDNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
            colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            myTable.setItems(data);
            displayQTYOnly();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void searchByName() {

        if (txtSearch.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم الموظف").show();

        } else if (txtSearch.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "من فضلك ادخل اسم الموظف حروف فقط").show();

        } else {
            myTable.getItems().clear();
            try {
                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("select * from users where name = '" + txtSearch.getText().trim() + "'");
                ResultSet r = pst.executeQuery();
                while (r.next()) {

                    data.add(new displayUsers(r.getInt("id"), r.getString("name"), r.getString("mobile"), r.getString("address"), r.getString("idNumber"), r.getDouble("sallary"), r.getString("gender"), r.getDate("date")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colIDNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
                colSallary.setCellValueFactory(new PropertyValueFactory<>("sallary"));
                colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

                myTable.setItems(data);

                //SearchSallary();
                setQtyForSearchName();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    
    
    ///// Display Users Information 
    public class displayUsers {

        int id;
        String name;
        String mobile;
        String address;
        String idNumber;
        double sallary;
        String gender;
        Date date;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public displayUsers(int id, String name, String mobile, String address, String idNumber, double sallary, String gender, Date date) {
            this.id = id;
            this.name = name;
            this.mobile = mobile;
            this.address = address;
            this.idNumber = idNumber;
            this.sallary = sallary;
            this.gender = gender;
            this.date = date;
        }

    }

}
