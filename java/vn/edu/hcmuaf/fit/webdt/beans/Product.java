package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;

/*
 *  ĐÂY LÀ LỚP ĐỐI TƯỢNG Product
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double price;
    private double sale_price;
    private String description;
    private String img;
    private int qualitySold;//so san pham dang dat mua
    private int quantity;//so san pham con lai trong shop
    private int category_id;

    //none constructor
    public Product() {

    }

    //full constructor

    public Product(int id, String name, double price, double sale_price, String description, String img, int qualitySold, int quantity, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale_price = sale_price;
        this.description = description;
        this.img = img;
        this.qualitySold = qualitySold;
        this.quantity = quantity;
        this.category_id = category_id;
    }


    //GETTER AND SETTER

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQualitySold() {
        return qualitySold;
    }

    public void setQualitySold(int qualitySold) {
        this.qualitySold = qualitySold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    //get total money
    public double getTotalMoney(){
        return this.qualitySold * this.sale_price;
    }
}
