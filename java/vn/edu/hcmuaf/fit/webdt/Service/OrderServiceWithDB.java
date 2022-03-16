package vn.edu.hcmuaf.fit.webdt.Service;

import vn.edu.hcmuaf.fit.webdt.Dao.OrderDao;
import vn.edu.hcmuaf.fit.webdt.beans.Cart;
import vn.edu.hcmuaf.fit.webdt.beans.User;

public class OrderServiceWithDB {
    private static OrderServiceWithDB instance;

    //none constructor
    public OrderServiceWithDB(){

    }

    //lớp này là để chỉ gọi OrderServiceWithDB một lần thôi
    public static OrderServiceWithDB getInstance(){
        if(instance == null){
            instance = new OrderServiceWithDB();
        }
        return instance;
    }

    public boolean createOrder(User user, Cart cart){
        OrderDao orderDao =OrderDao.getInstance();
        return OrderDao.create(user, cart);
    }
}
