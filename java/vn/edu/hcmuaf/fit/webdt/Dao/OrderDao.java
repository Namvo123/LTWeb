package vn.edu.hcmuaf.fit.webdt.Dao;

import org.jdbi.v3.core.result.ResultBearing;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.Product;
import vn.edu.hcmuaf.fit.webdt.beans.User;
import vn.edu.hcmuaf.fit.webdt.db.JDBIConnector;

public class OrderDao {
    private static OrderDao instance;

    //none cunstructor
    public OrderDao(){

    }

    public static OrderDao getInstance(){
        if(instance == null){
            instance = new OrderDao();
        }
        return instance;
    }

    public static boolean create(User user, Cart cart) {
        int orderId = JDBIConnector.get().withHandle(handle -> {
            ResultBearing resultBearing =handle.createUpdate("INSERT INTO orders (user_id,status) VALUE (?,?)")
                    .bind(0, user.getId())
                    .bind(1,0)
                    .executeAndReturnGeneratedKeys();
            return resultBearing.mapTo(Integer.class).findFirst().get();//lấy order id ra đề lát dùng cho bên dưới
        });
        int total = JDBIConnector.get().withHandle(handle -> {
            int sum =0;
            for(Product product :cart.getProductList()){
                sum+= handle.createUpdate("INSERT INTO order_details (order_id, product_id, price, quantity, note) VALUE (?,?,?,?,?)")
                        .bind(0, orderId)
                        .bind(1, product.getId())
                        .bind(2,product.getSale_price())
                        .bind(3, product.getQualitySold())
                        .bind(4, "").execute();
            }
            return sum;//nghia la total = sum;
        });
        return total == cart.getProductList().size();//nếu lưu trữ được hết thì return true else return false
    }
}
