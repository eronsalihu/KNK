package Controllers;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.ImageView;

public class Products {
    private String name,category,invertory;
    private double price;
    private ImageView image;
    private String dateAndTime;
    
    Products(String name,double price,String invertory,String category){
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;

    }
    
    Products(String name,double price,String invertory,String category,String dateAndTime){
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;
       	this.dateAndTime=dateAndTime;
       	
    }

    Products(ImageView image,String name,double price,String invertory,String category){
        this.image=image;
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public String getInvertory() {
        return invertory;
    }
    
    public String getDateAndTime(){
    	return dateAndTime;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInvertory(String invertory) {
        this.invertory = invertory;
    }
    public void setDateAndTime(String dateAndTime)
    {
    	this.dateAndTime=dateAndTime;
    }
    public String thedate() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    	   LocalDateTime now = LocalDateTime.now();
    	   
    	return dtf.format(now);
    }
}
