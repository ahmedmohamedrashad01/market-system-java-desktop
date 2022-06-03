
package course;

import java.util.Date;

/// Display Purchases Of NormAL wHITE Papers /////


public class Edite_purchases_of_normalWhitePapers {
  int id ;
    String name;
    String item;
    String size;
    Double qty;
    String mobile;
    Date date;
    Double price;
     String user;
     
     
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Edite_purchases_of_normalWhitePapers(int id, String name, String item, String size, Double qty, String mobile, Date date, Double price, String user) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.size = size;
        this.qty = qty;
        this.mobile = mobile;
        this.date = date;
        this.price = price;
        this.user = user;
    }

  
}
