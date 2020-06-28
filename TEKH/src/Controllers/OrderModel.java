package Controllers;

import java.util.Date;

public class OrderModel {
    private String Name;
    private Date Date;
    private double Price, VAT, Total;

    public OrderModel(String Name, Date Date, double Price, double VAT, double Total){
        this.Name = Name;
        this.Date = Date;
        this.Price = Price;
        this.VAT = VAT;
        this.Total = Total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }


}
