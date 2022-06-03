
package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class EditPasswordController implements Initializable {

    
    public static String user ="";
    
    
    
    
    @FXML
    private AnchorPane root;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextField txtIdNumber;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtSallary;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnExit;

    @FXML
    void btnBackAction(ActionEvent event) {

        //  Select_items
        
        try{
            Select_itemsController.user = txtName.getText().trim();
             Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
                root.getChildren().setAll(p);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void btnSaveAction(ActionEvent event) {

        saveNewPassword();
    }

    @FXML
    void txtAddressAction(ActionEvent event) {

    }

    @FXML
    void txtIdNumberAction(ActionEvent event) {

    }

    @FXML
    void txtMobileAction(ActionEvent event) {

    }

    @FXML
    void txtNameAction(ActionEvent event) {

    }

    @FXML
    void txtSallaryAction(ActionEvent event) {

    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        getDataFromDB();
        System.out.println(user);
        
    }    
    
    public void getDataFromDB(){
        txtName.setText(user);
        
        try{
              DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("SELECT password,mobile,address,idNumber,sallary,date from users where name ='" + txtName.getText().trim() + "'");
                ResultSet r = pst.executeQuery();
                if(r.next()){
                    txtPassword.setText(r.getString("password"));
                    txtMobile.setText(r.getString("mobile"));
                    txtAddress.setText(r.getString("address"));
                    txtIdNumber.setText(r.getString("idNumber"));
                    txtSallary.setText(r.getString("sallary"));
                    txtDate.setValue(r.getDate("date").toLocalDate());
                    
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void saveNewPassword(){
     try{
            if(txtPassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"من فضلك ادخل كلمة المرور").showAndWait();
        }else{
            DB d = new DB();
             d.query = "UPDATE users SET password = '"+txtPassword.getText().trim()+"'  WHERE name = '" + txtName.getText().trim() + "' and idNumber = '"+txtIdNumber.getText()+"'";
                    d.stmt.executeUpdate(d.query);

                    new Alert(Alert.AlertType.INFORMATION, "تم تحديث كلمة المرور").showAndWait();

        }
         
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
    }
}
