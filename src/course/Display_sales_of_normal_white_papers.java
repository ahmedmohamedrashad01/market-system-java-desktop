
package course;

import java.util.Date;

public class Display_sales_of_normal_white_papers {
    
    ///    Display_sales_of_normal_white_papers
    
    int id;
    String name;
    double qty;
    String mobile;
    Date date;
    double price;

    public Display_sales_of_normal_white_papers(int id, String name, double qty, String mobile, Date date, double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.mobile = mobile;
        this.date = date;
        this.price = price;
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

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
