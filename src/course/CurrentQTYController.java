package course;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CurrentQTYController implements Initializable {

    public static String user ="";
    
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnExit;

    /////////////////////////////
    @FXML
    private TableView<currentEggs> tableEGGS;

    @FXML
    private TableColumn<currentEggs, Integer> colIDEGGS;

    @FXML
    private TableColumn<currentEggs, String> colCOLOREGGS;

    @FXML
    private TableColumn<currentEggs, String> COLSIZEEGGS;

    @FXML
    private TableColumn<currentEggs, Double> COLQTYEGGS;

    //////////////////////////////////////////////////
    @FXML
    private TableView<Papers> tablePAPER;

    @FXML
    private TableColumn<Papers, Integer> colIDPAPER;

    @FXML
    private TableColumn<Papers, String> colITEMPAPER;

    @FXML
    private TableColumn<Papers, String> colSizePaper;

    @FXML
    private TableColumn<Papers, Double> colQTYPAPER;

    
    //////////////////////////////////////////
    
    
    @FXML
    private TableView<Other> tableOTHER;

    @FXML
    private TableColumn<Other, Integer> colIDOTHER;

    @FXML
    private TableColumn<Other, String> colITEMOTHER;

    @FXML
    private TableColumn<Other, Double> colQTYOTHER;

    
    
    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            Select_itemsController.user = user;
            Parent p = FXMLLoader.load(getClass().getResource("Select_items.fxml"));
            root.getChildren().setAll(p);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    void btnExitAction(ActionEvent event) {
        System.exit(0);
    }

    final ObservableList<currentEggs> Eggs = FXCollections.observableArrayList();
    final ObservableList<Papers> myPapers = FXCollections.observableArrayList();
    final ObservableList<Other> GIFT = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DisplayEGGS();
        DisplayPapers();
        DisplayOthers();
        System.out.println(user);
    }

    public void DisplayEGGS() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from eggs_qty");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                Eggs.add(new currentEggs(r.getInt("id"), r.getString("color"), r.getString("size"), r.getDouble("qty")));
            }
            colIDEGGS.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCOLOREGGS.setCellValueFactory(new PropertyValueFactory<>("color"));
            COLSIZEEGGS.setCellValueFactory(new PropertyValueFactory<>("size"));
            COLQTYEGGS.setCellValueFactory(new PropertyValueFactory<>("qty"));
            tableEGGS.setItems(Eggs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /////////////////
    public void DisplayPapers() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from paperqty");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                myPapers.add(new Papers(r.getInt("id"), r.getString("item"), r.getString("size"), r.getDouble("qty")));
            }
            colIDPAPER.setCellValueFactory(new PropertyValueFactory<>("id"));
            colITEMPAPER.setCellValueFactory(new PropertyValueFactory<>("item"));
            colSizePaper.setCellValueFactory(new PropertyValueFactory<>("size"));
            colQTYPAPER.setCellValueFactory(new PropertyValueFactory<>("qty"));
            tablePAPER.setItems(myPapers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /////////////////////////////
    public void DisplayOthers() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from purchase_gift_fake");
            ResultSet r = pst.executeQuery();
            while (r.next()) {              
                GIFT.add(new Other(r.getInt("id"),r.getString("item"), r.getDouble("qty")));
            }
            colIDOTHER.setCellValueFactory(new PropertyValueFactory<>("id"));
            colITEMOTHER.setCellValueFactory(new PropertyValueFactory<>("item"));
            colQTYOTHER.setCellValueFactory(new PropertyValueFactory<>("qty"));    
            tableOTHER.setItems(GIFT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /// class eggs
    public class currentEggs {

        int id;
        String color;
        String size;
        double qty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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

        public currentEggs(int id, String color, String size, double qty) {
            this.id = id;
            this.color = color;
            this.size = size;
            this.qty = qty;
        }

    }

    //// papers
    
    public class Papers {

        int id;
        String item;
        String size;
        double qty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Papers(int id, String item, String size, double qty) {
            this.id = id;
            this.item = item;
            this.size = size;
            this.qty = qty;
        }

    }
    
    // gift class
    
    public class Other{
        int id ;
        String item;
        double qty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Other(int id, String item, double qty) {
            this.id = id;
            this.item = item;
            this.qty = qty;
        }
        
    }
}
