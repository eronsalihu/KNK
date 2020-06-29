package Controllers;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.ImageView;

public class Products {
    private String name;
    private String category;
    private int invertory;
    private double price;
    private ImageView image;
    private String dateAndTime;
    private String inventoryn;
    private int id;
    
    Products(String name,double price,int invertory,String category){
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;

    }
    Products(int id,String name,double price,String category){
        this.id=id;
        this.name=name;
        this.price=price;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Products(String name, double price, int invertory, String category, String dateAndTime){
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;
       	this.dateAndTime=dateAndTime;
       	
    }

    Products(ImageView image,String name,double price,int invertory,String category){
        this.image=image;
        this.name=name;
        this.price=price;
        this.invertory=invertory;
        this.category=category;

    }
    Products(ImageView image,String name,double price,String invertoryn,String category){
        this.image=image;
        this.name=name;
        this.price=price;
        this.inventoryn=invertoryn;
        this.category=category;

    }

    public Products(String name, double v, String inventory, String category, String dateAndTime) {

    }


    public String getInventoryn() {
        return inventoryn;
    }

    public void setInventoryn(String inventoryn) {
        this.inventoryn = inventoryn;
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

    public int getInvertory() {
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

    public void setInvertory(int invertory) {
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
    
    public String warrantyDate() {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    	   LocalDateTime now = LocalDateTime.now();
    	  LocalDateTime later = now.plusMonths(6);
    	return dtf.format(later);
    }
}
