package Controllers;

import java.util.Date;

public class FaturatHelper {

        private Integer id;
        private String person;
        private double totalCost;
        private Date time;
        private String product;
        private double cost;



    FaturatHelper(Integer id, String person, Date time, double totalCost){
        this.id=id;
        this.person=person;
        this.time=time;
        this.totalCost=totalCost;
    }

    FaturatHelper(Integer id, String product, double cost){
        this.id=id;
        this.product=product;
        this.cost=cost;
    }
    public Integer getId() {
        return id;
    }
    public String getPerson() {
        return person;
    }
    public Date getTime() {
        return time;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public String getProduct() { return product; }
    public double getCost() { return cost; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(double cost) {
        this.cost = cost;
    }

}
