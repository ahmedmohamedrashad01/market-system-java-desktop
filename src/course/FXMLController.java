package course;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXMLController implements Initializable {

    @FXML
    private JFXButton btnCreateUser;

    @FXML
    void btnCreateUserAction(ActionEvent event) {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("تسجيل الدخول");
        dialog.setHeaderText("الرجاء ادخال كلمة المرور الخاصه بالمدير العام");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField password = new PasswordField();
        password.setPromptText("كلمة المرور");

        grid.add(new Label(": كلمة المرور"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                if (password.getText().trim().equalsIgnoreCase("123456")) {
                    try {
                        Parent p = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
                        root1.getChildren().setAll(p);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "كلمة المرور غير صحيحه!!!").showAndWait();

                }

            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println(", Password=" + password.getText());
        });

    }

    @FXML
    public AnchorPane root1;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) {

        try {

            if (txtName.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "يرجى ادخال اسم المستخدم").show();
            } else if (txtPassword.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "يرجى ادخال كلمة المرور").show();
            } else if (txtName.getText().trim().equalsIgnoreCase("ايمن حسين") && txtPassword.getText().trim().equalsIgnoreCase("123456")) {

                
                ////////////// 
                Select_itemsController.user = txtName.getText().trim();
                
                Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
                root1.getChildren().setAll(p);

            } else if (!(txtName.getText().trim().isEmpty()) && !(txtPassword.getText().trim().isEmpty())) {

                DB d = new DB();
                PreparedStatement pst = d.con.prepareStatement("SELECT name,password from users where name ='" + txtName.getText().trim() + "' and password = '" + txtPassword.getText().trim() + "'");
                ResultSet r = pst.executeQuery();
                if (!r.next()) {

                    new Alert(Alert.AlertType.ERROR, "اسم المستخدم او كلمة المرور غير صحيحه").showAndWait();
                    return;
                }else {
                    
                Select_itemsController.user = txtName.getText().trim();
                Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
                root1.getChildren().setAll(p);
            }

            } 

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }

    

}
