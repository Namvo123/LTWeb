package vn.edu.hcmuaf.fit.webdt.Dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.Product;
import vn.edu.hcmuaf.fit.webdt.db.DBConnection;
import vn.edu.hcmuaf.fit.webdt.db.JDBIConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {
    private static ProductDao instance;

    //none constructor
    private ProductDao(){

    }
    //lớp này là để chỉ gọi ProductDao một lần thôi
    public static ProductDao getInstance(){
        if(instance == null){
            instance = new ProductDao();
        }
        return instance;
    }

    //METHOD NÀY DÙNG JDBI
    //trong đây sử dụng cú pháp lambda expression(cung cấp cách để viết code ngắn gọn hơn) (không biết cần xem thêm)
    //lambda expression: (parameter1, parameter2) -> { code block }
    public List<Product> getAll(String searchName){
        if(searchName.equals("")){
            List<Product> show = new ArrayList<>();
            return show;
        }else {
            String tname = "%" + searchName + "%";
            return JDBIConnector.get().withHandle(handle -> {
                return handle.createQuery("SELECT * FROM products WHERE name like ?")
                        .bind(0, tname)
                        .mapToBean(Product.class).stream().collect(Collectors.toList());//quá không hiểu
                /**
                 * thầy giải thích:
                 *  -"select * from product" : thực thi cái câu sql "select * from product"
                 *  -.mapToBean(Product.class) : map(lấy) những cái thuộc tính trong class Product thuộc beans cũng là tên các cột trong sql
                 *  -collect(Collectors.toList()) :  chuyển nó thành một List (Collectors.toList())
                 */
            });
        }
    }
    public static Product getById(int id) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM products WHERE id = ?")
                    .bind(0,id)
                    .mapToBean(Product.class).first();
        });
    }

    public static boolean updateQuantity(Cart cart) {

            int result = JDBIConnector.get().withHandle(handle -> {
                int sum =0;
                int id =0;
                for(Product product :cart.getProductList()) {
                    id = product.getId();
                    sum += handle.createUpdate("UPDATE products SET quantity =? WHERE id =?")
                            .bind(0,product.getQuantity()-product.getQualitySold())
                            .bind(1,id).execute();
                }
                return sum;
            });

        return result == cart.getProductList().size();//nếu update được hết thì return true else return false
    }

    public static List<String> getImgById(int id) {
        List<String> result = new ArrayList<>();
        String sql ="SELECT * FROM product_images WHERE id_product =?";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);
        try {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                result.add(rs.getString("thumbnail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    //lay ten category bang product
    public static String getNameCategoryByProduct(Product product) {
        int id_category = product.getCategory_id();
        String result ="";
        String sql = "SELECT * FROM category WHERE id =?";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);
        try{
            preparedStatement.setInt(1, id_category);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                result = rs.getString("name_category");
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //lay ten category bang id
    public static String getNameCategoryByID(int id) {
        String result ="";
        String sql = "SELECT * FROM category WHERE id =?";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);
        try{
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                result = rs.getString("name_category");
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Product> getProductByIdCategory(int id_category) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("SELECT * FROM products WHERE category_id = ?")
                    .bind(0,id_category)
                    .mapToBean(Product.class).stream().collect(Collectors.toList());//quá không hiểu
            /**
             * thầy giải thích:
             *  -"select * from product" : thực thi cái câu sql "select * from product"
             *  -.mapToBean(Product.class) : map(lấy) những cái thuộc tính trong class Product thuộc beans cũng là tên các cột trong sql
             *  -collect(Collectors.toList()) :  chuyển nó thành một List (Collectors.toList())
             */
        });
    }
    //
}
