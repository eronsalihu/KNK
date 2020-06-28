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


}
