package vn.edu.hcmuaf.fit.webdt.Service;

import vn.edu.hcmuaf.fit.webdt.Dao.ProductDao;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.Product;
import vn.edu.hcmuaf.fit.webdt.db.JDBIConnector;

import java.util.List;

public class ProductServiceWithDB {
    private static ProductServiceWithDB instance;

    //none constructor
    private ProductServiceWithDB(){

    }

    //lớp này là để chỉ gọi ProductServiceWithDB một lần thôi
    public static ProductServiceWithDB getInstance(){
        if(instance == null){
            instance = new ProductServiceWithDB();
        }
        return instance;
    }

    public List<Product> getAll(String searchName){
        return ProductDao.getInstance().getAll(searchName);
    }

    public Product getById(int id) {
        return ProductDao.getById(id);
    }

    public boolean updateQuantity(Cart cart) {
        return ProductDao.updateQuantity(cart);
    }

    public List<String> getImgById(int id) {
        return ProductDao.getImgById(id);
    }

    public String getNameCategoryByProduct(Product product) {
        return ProductDao.getNameCategoryByProduct(product);
    }

    public String getNameCategoryByID(int id){
        return ProductDao.getNameCategoryByID(id);
    }
    public List<Product> getProductListByIdCategory(int id_category) {
        return ProductDao.getProductByIdCategory(id_category);
    }
}
