package model;

public class Bike {
    private String bks;
    private int soghe;
    private String category;
    private int price;
    private int desposit;
    private String description;

    public Bike(String bks, int soghe, String category, int price, String description) {
        this.bks = bks;
        this.soghe = soghe;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public String getBks() {
        return bks;
    }

    public void setBks(String bks) {
        this.bks = bks;
    }

    public int getSoghe() {
        return soghe;
    }

    public void setSoghe(int soghe) {
        this.soghe = soghe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDesposit() {
        return this.price*0.4 ;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
