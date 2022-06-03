package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class DB {

    public Connection con;
    public Statement stmt;
    public String query;

    public DB() {

        con = null;
        stmt = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/abu_hussain", "root", "root21485");

            if (con != null) {
                // System.out.println("Connected");
                stmt = con.createStatement();
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }

    ////  1 - insert Eggs For the consumer
    public void insert_Eggs(String name, String size, String color, Double qty, String mobile, LocalDate date, Double price, String user) {
        try {
            query = "insert into add_eggs (name,size,color,qty,mobile,date,price,user) values ('" + name + "','" + size + "','" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "' , '" + user + "')";
            stmt.executeUpdate(query);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("رساله");
            a.setContentText("تم الحفظ وحذف الكميه المباعه من المشتريات");
            a.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /*
    public void insertPurchasesOfEggs(String name, String size, String color, Double qty, String mobile, LocalDate date, Double price) {
        try {
            query = "insert into purchases_of_eggs (name,size,color,qty,mobile,date,price) values ('" + name + "','" + size + "','" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
            stmt.executeUpdate(query);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("رساله");
            a.setContentText("تمت اضافة مشتريات جديده من البيض");
            a.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     */
    public void check_Before_Insert(String name, String size, String color, Double qty, String mobile, LocalDate date, Double price, String user) {

        double sum = 0;
        try {
            ResultSet r2 = con.createStatement().executeQuery("SELECT size,color,qty FROM eggs_qty WHERE color = '" + color + "' and size = '" + size + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");

                if (sum >= qty) {
                    insert_Eggs(name, size, color, qty, mobile, date, price, user);

                    //  query= "SELECT size,color,SUM(qty) AS Total FROM purchases_of_eggs WHERE color = '" + color + "' and size = '" + size + "' (UPDATE purchases_of_eggs SET Total = Total - '"+qty+"' )";
                    query = "UPDATE eggs_qty SET qty =  qty - '" + qty + "' WHERE color = '" + color + "' and size = '" + size + "'";

                    stmt.executeUpdate(query);

                } else {
                    new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n  الكميه المتاحه حاليا " + String.valueOf(sum)).show();

                    r2.close();

                }

                r2.close();
            } else {

                r2.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void insertPurchasesOfEggs2(String name, String size, String color, Double qty, String mobile, LocalDate date, Double price, String user) {
        double full = 0;

        try {
            try {
                query = "insert into purchases_of_eggs (name,size,color,qty,mobile,date,price,user) values ('" + name + "','" + size + "','" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "' , '" + user + "')";
                stmt.executeUpdate(query);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("رساله");
                a.setContentText("تمت اضافة مشتريات جديده من البيض");
                a.showAndWait();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            ///
            ResultSet r2 = con.createStatement().executeQuery("SELECT size,color,qty from eggs_qty where size ='" + size + "' and color = '" + color + "' ");
            if (!r2.next()) {
                query = "insert eggs_qty (size,color,qty) values ('" + size + "','" + color + "','" + qty + "')";
                stmt.executeUpdate(query);

            } else {
                query = "UPDATE eggs_qty SET qty = qty + '" + qty + "'  WHERE color = '" + color + "' and size = '" + size + "'";
                stmt.executeUpdate(query);
            }

            ////
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //// display purshases QTY
    public void displayPurchasesLBL() {

    }

    /////////////////////////////////////
    ///////////////////اضافة مشتريات ورق ابيض////////////////
    /////////////////////////////////////
    ///////////////////اضافة مشتريات ورق ابيض////////////////
    public void insertPurchasesOfWhitePapers(String color, double qty, String mobile, LocalDate date, double price) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");
            if (!r2.next()) {
                query = "insert into purchases_white_papers (color,qty,mobile,date,price) values ('" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق الابيض").showAndWait();

            } else {
                query = "UPDATE purchases_white_papers SET qty = qty + '" + qty + "'  WHERE color = '" + color + "'";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق الابيض").showAndWait();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /////////////////////// salles white papers 
    public void insertSalesOfPapers(String color, Double qty, String name, double newQty, String mobile, LocalDate date, double price) {

        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_white_papers (name,color,qty,mobile,date,price) values ('" + name + "','" + color + "','" + newQty + "','" + mobile + "','" + date + "','" + price + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchases_white_papers SET qty = qty - '" + qty + "'  WHERE color = '" + color + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /////////////////////// بيع كميه من الورق المستورد العادى
    public void insertSalesOfNormalImportedPapers(String color, Double qty, String name, double newQty, String mobile, LocalDate date, double price) {

        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_white_papers (name,color,qty,mobile,date,price) values ('" + name + "','" + color + "','" + newQty + "','" + mobile + "','" + date + "','" + price + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchases_white_papers SET qty = qty - '" + qty + "'  WHERE color = '" + color + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ////////////////////////////
    /////////////////////// بيع كميه من الورق المحلى العادى
    public void insertSalesOfNormalLocalPapers(String color, Double qty, String name, double newQty, String mobile, LocalDate date, double price) {

        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_white_papers (name,color,qty,mobile,date,price) values ('" + name + "','" + color + "','" + newQty + "','" + mobile + "','" + date + "','" + price + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchases_white_papers SET qty = qty - '" + qty + "'  WHERE color = '" + color + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ///////////  بيع كميه من ورق الجورنال المستورد
    /////////////////////// بيع كميه من الورق المحلى العادى
    public void insertSalesOfImportedJornalPapers(String color, Double qty, String name, double newQty, String mobile, LocalDate date, double price) {

        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_white_papers (name,color,qty,mobile,date,price) values ('" + name + "','" + color + "','" + newQty + "','" + mobile + "','" + date + "','" + price + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchases_white_papers SET qty = qty - '" + qty + "'  WHERE color = '" + color + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ////////////////////
    ////  بيع ورق جورنال محلى 
    public void insertSalesOfLocalJornalPapers(String color, Double qty, String name, double newQty, String mobile, LocalDate date, double price) {

        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_white_papers (name,color,qty,mobile,date,price) values ('" + name + "','" + color + "','" + newQty + "','" + mobile + "','" + date + "','" + price + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchases_white_papers SET qty = qty - '" + qty + "'  WHERE color = '" + color + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ////////////  الورق المستورد
    public void PurchasesNormalImportedPapers(String color, double qty, String mobile, LocalDate date, double price) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");
            if (!r2.next()) {
                query = "insert into purchases_white_papers (color,qty,mobile,date,price) values ('" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق المستورد العادى").showAndWait();

            } else {
                query = "UPDATE purchases_white_papers SET qty = qty + '" + qty + "'  WHERE color = '" + color + "'";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق المستورد العادى").showAndWait();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    ////////////////// الورق المحلى العادى
    public void PurchasesNormalLocalPapers(String color, double qty, String mobile, LocalDate date, double price) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");
            if (!r2.next()) {
                query = "insert into purchases_white_papers (color,qty,mobile,date,price) values ('" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق المستورد العادى").showAndWait();

            } else {
                query = "UPDATE purchases_white_papers SET qty = qty + '" + qty + "'  WHERE color = '" + color + "'";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده للورق المحلى العادى").showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /////////  ورق جورنال مستورد
    public void PurchasesJornalImportedPapers(String color, double qty, String mobile, LocalDate date, double price) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");
            if (!r2.next()) {
                query = "insert into purchases_white_papers (color,qty,mobile,date,price) values ('" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده لورق الجورنال المستورد").showAndWait();

            } else {
                query = "UPDATE purchases_white_papers SET qty = qty + '" + qty + "'  WHERE color = '" + color + "'";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده لورق الجورنال المستورد").showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /////////////////////   ورق جورنال محلى
    public void PurchasesLocalJornalPapers(String color, double qty, String mobile, LocalDate date, double price) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT color,qty from purchases_white_papers where color ='" + color + "'");
            if (!r2.next()) {
                query = "insert into purchases_white_papers (color,qty,mobile,date,price) values ('" + color + "','" + qty + "','" + mobile + "','" + date + "','" + price + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده لورق الجورنال المحلى").showAndWait();

            } else {
                query = "UPDATE purchases_white_papers SET qty = qty + '" + qty + "'  WHERE color = '" + color + "'";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء جديده لورق الجورنال المحلى").showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /////// GIFT ITEMS ////////////////
    public void addNewGiftItem(String name, Double Qty) {
        try {
            ResultSet r = con.createStatement().executeQuery("select name from new_gift where name = '" + name + "'");
            if (!r.next()) {
                query = "insert into new_gift (name) values ('" + name + "')";

                stmt.executeUpdate(query);

                new Alert(Alert.AlertType.ERROR, "تم اضافة منتج جديد").showAndWait();
                String query2 = "insert into purchase_gift_fake (item,qty) values ('" + name + "','" + Qty + "')";
                stmt.executeUpdate(query2);

            } else {
                new Alert(Alert.AlertType.ERROR, "اسم المنتج مسجل مسبقا").showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ////////////// INSERT PURCHASES OF GIFTTTTT ////////////
    public void insertPurchasesOfGift(String name, String item, Double qty, LocalDate date, Double price, String user) {
        double sum = 0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT qty from purchase_gift_fake where item ='" + item + "'");
            if (r2.next()) {
                sum = r2.getDouble("qty");

            }
            query = "UPDATE purchase_gift_fake SET qty = qty + '" + qty + "'  WHERE item = '" + item + "'";
            stmt.executeUpdate(query);

            query = "insert into purchase_gift (name,item,qty,date,price,user) values ('" + name + "','" + item + "','" + qty + "','" + date + "','" + price + "','" + user + "')";
            stmt.executeUpdate(query);
            new Alert(Alert.AlertType.INFORMATION, "تم اضافة عملية شراء").showAndWait();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //////////////// Insert Sales Of Gifttttttttt ///////////////
    public void insertSalesOfGift(String name, Double qty, String item, LocalDate date, double price, String user) {
        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT item,qty from purchase_gift_fake where item ='" + item + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into sales_gift (name,item,qty,date,price,user) values ('" + name + "','" + item + "','" + qty + "','" + date + "','" + price + "','" + user + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE purchase_gift_fake SET qty = qty - '" + qty + "'  WHERE item = '" + item + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /////////////////////////////////////////////////
    ///  الجدول الحديث لمشتريات الورق
    public void insertNewPurchasesOfPapers(String name, String item, String size, Double qty, String mobile, LocalDate date, Double price, String user) {
        double full = 0;

        try {
            try {
                query = "insert into purchase_fake_paper (name,item,size,qty,mobile,date,price,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + mobile + "','" + date + "','" + price + "','" + user + "')";
                stmt.executeUpdate(query);
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("رساله");
                a.setContentText("تمت اضافة مشتريات جديده من الورق");
                a.showAndWait();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            ///
            ResultSet r2 = con.createStatement().executeQuery("SELECT item,size,qty from paperqty where item ='" + item + "' and size = '" + size + "'");
            if (!r2.next()) {
                query = "insert into paperqty (item,size,qty) values ('" + item + "','" + size + "','" + qty + "')";
                stmt.executeUpdate(query);

            } else {
                query = "UPDATE paperqty SET qty = qty + '" + qty + "'  WHERE item = '" + item + "' and size = '" + size + "'";
                stmt.executeUpdate(query);
            }

            ////
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    ///////  الجدول الحديث لممبيعات الورق
    public void SalesOfNewPapers(String name, String item, String size, Double qty, String mobile, LocalDate date, double price, String user) {
        double sum = 0.0;
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT item,size,qty from paperqty where item ='" + item + "' and size = '" + size + "'");

            if (r2.next()) {
                sum += r2.getDouble("qty");
                if (qty <= sum) {
                    String q = "insert into salesnewpapers (name,item,size,qty,mobile,date,price,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + mobile + "','" + date + "','" + price + "','" + user + "')";
                    stmt.executeUpdate(q);
                    query = "UPDATE paperqty SET qty = qty - '" + qty + "'  WHERE item = '" + item + "' and size = '" + size + "'";
                    stmt.executeUpdate(query);

                    new Alert(Alert.AlertType.INFORMATION, "عملية البيع تمت بنجاح وتم خصم الكميه من المشتريات").showAndWait();

                }
            }

            if (qty > sum) {
                new Alert(Alert.AlertType.ERROR, "الكميه المطلوبه غير متاحه \n الكميه المتوفره حاليا هى : " + sum).showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ///////////////////////////////////////
    //////////////////////////////////////
    //////////////// Sales Deleted Of Eggs
    public void EggssalesDeleted(int id, String name, String item, String size, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into seles_deleted (name,item,size,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + price + "','" + date + "','" + oldDate + "','" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EggssPurchaseDeleted(int id, String name, String item, String size, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into purchase_deleted (name,item,size,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + price + "' , '" + date + "','" + oldDate + "', '" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ///////////////////////////////////////
    //////////////////////////////////////
    //////////////// sales Deleted Of papers
    public void PapersalesDeleted(int id, String name, String item, String size, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into sales_deleted_papers (name,item,size,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + price + "','" + date + "','" + oldDate + "','" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void PapersPurchaseDeleted(int id, String name, String item, String size, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into purchase_deleted_papers (name,item,size,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + size + "','" + qty + "','" + price + "' , '" + date + "','" + oldDate + "', '" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /////////// deleted gift 
    public void GiftsalesDeleted(int id, String name, String item, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into sales_gift_deleted (name,item,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + qty + "','" + price + "','" + date + "','" + oldDate + "','" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void GiftPurchaseDeleted(int id, String name, String item, double qty, double price, LocalDate date, String oldDate, String user) {
        try {
            String q = "insert into purchase_gift_deleted (name,item,qty,price,date,oldDate,user) values ('" + name + "','" + item + "','" + qty + "','" + price + "' , '" + date + "','" + oldDate + "','" + user + "')";
            stmt.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //////  Create New User
    public void createUser(String name, String password, String mobile, String address, String idNumber, double sallary, String gender, LocalDate date) {
        try {

            ResultSet r2 = con.createStatement().executeQuery("SELECT name,idNumber from users where name ='" + name + "' and idNumber = '" + idNumber + "'");
            if (!r2.next()) {
                query = "insert into users (name,password,mobile,address,idNumber,sallary,gender,date) values ('" + name + "','" + password + "','" + mobile + "','" + address + "','" + idNumber + "' , '" + sallary + "','" + gender + "','" + date + "')";
                stmt.executeUpdate(query);
                new Alert(Alert.AlertType.INFORMATION, "تم اضافة مستخدم جديد").showAndWait();

            } else {
                new Alert(Alert.AlertType.ERROR, "اسم المستخدم موجود بالفعل").showAndWait();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Deleted Users
    /*
       public void deletedUsers(int id , String name , String mobile ,String address,String idNumber, double sallary ,String gender , LocalDate date , String oldDate){
           
       }
     */
    ///// Total Bank
    public void insertTotal(double total, LocalDate date) {
        try {

            query = "insert into total_bank (total,date) values ('" + total + "','" + date + "')";
            stmt.executeUpdate(query);
            new Alert(Alert.AlertType.INFORMATION, "تم اضافة المبلغ الاجمالى").showAndWait();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /// Deposit Price
    public void insertDeposit(double total, LocalDate date) {
        try {

            query = "insert into deposit (deposit_price,date) values ('" + total + "','" + date + "')";
            stmt.executeUpdate(query);

            query = "UPDATE total_bank SET total = total + '" + total + "'";
            stmt.executeUpdate(query);
            new Alert(Alert.AlertType.INFORMATION, "تم ايداع مبلغ جديد").showAndWait();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
