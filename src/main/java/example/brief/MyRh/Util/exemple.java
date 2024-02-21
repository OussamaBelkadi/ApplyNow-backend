package example.brief.MyRh.Util;

public class exemple {


    public String nom;
    private String brand;
    private String color;
    private int price;


    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setPrice(int price){
        this.price = price;
    }


    public String getBrand(){
        return this.brand;
    }

    public int getPrice(){
        return this.price;
    }


    public String hello(String nom){
        return  "hello " + nom;

    }

    public static void main(String[] args) {

            exemple HI = new exemple();


    }


}
