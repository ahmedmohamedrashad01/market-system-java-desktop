
package course;

import java.util.Date;


public class PurchasesGift {
    int id ;
    String name;
    String item;
    double qty;
    Date date;
    double price;
    String user;
    
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    

    public PurchasesGift(int id, String name, String item, double qty, Date date, double price,String user) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.qty = qty;
        this.date = date;
        this.price = price;
        this.user = user;
    }

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

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
