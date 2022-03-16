package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ĐÂY LÀ LỚP JAVA LƯU TRỮ CÁC SẢN PHẨM CÓ TRONG CART BẰNG MAP
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Cart instance;
    private Map<Integer ,Product> productList;

    //constructor
    public Cart(){
        productList = new HashMap<>();
    }


    public static Cart getInstance(){
        return new Cart();//cái này để khi copy đường link vào tab ẩn danh thì nó sẽ không hiện bất kì sản phẩm nào mà mình trước đó được lưu

    }

    //put product to cart
    public void put(Product product){
        if(productList.containsKey(product.getId())){
            upQuantity(product.getId());
        }else
        productList.put(product.getId(),product);
    }

    private void upQuantity(int id) {
        Product product1 = productList.get(id);
        product1.setQualitySold(product1.getQualitySold() + 1);
    }

    //get product from cart by id
    public Product get(int id){
        return productList.get(id);
    }

    //update quantiy of product from cart by id
    public void updateQuantity(int id , int quantity){
        Product product = get(id);
        product.setQualitySold(quantity);
    }

    //remove product from cart by id
    public void remove(int id){
        productList.remove(id);
    }

    //get total price of all product in cart
    public double getTotalPrice(){
        double totalPrice = 0;
        for(Product product:productList.values()){
            totalPrice += product.getTotalMoney();
        }
        return totalPrice;
    }

    //lay tong so san pham mua duoc
    public int getTotalQuantity(){
        int totalQuantity = 0;
        for(Product product: productList.values()){
            totalQuantity += product.getQualitySold();
        }
        return totalQuantity;
    }

    //lay ra danh sach san pham trong cart, dung collection de no tu sap xep
    public Collection<Product> getProductList(){
        return productList.values();
    }


    /**
     *
     * trả lại số sản phẩm cần tăng nếu tăng vượt quá số sản phẩm trong shop thì nó
     * sẽ trả lại tất cả sản phẩm còn lại trong shop
     */
    public int updateQuantitySold(int id, int quantity) {
        Product product = get(id);
        if(quantity <1 || quantity > product.getQuantity()){
            return product.getQuantity();
        }
        product.setQualitySold(quantity);
        return product.getQualitySold();
    }
}
