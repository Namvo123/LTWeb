package vn.edu.hcmuaf.fit.webdt.Service;

import vn.edu.hcmuaf.fit.webdt.Dao.UserDao;
import vn.edu.hcmuaf.fit.webdt.beans.User;

/*
    LỚP NÀY LÀ LỚP THỰC HIỆN KIỂM TRA USERNAME VÀ PASSWORD TRONG DB
 */
public class UserServiceWithDB {
    private static UserServiceWithDB instance;

    //none constructor
    public UserServiceWithDB(){

    }

    //lớp này là để chỉ gọi UserServiceWithDB một lần thôi
    public static UserServiceWithDB getInstance(){
        if(instance == null){
            instance = new UserServiceWithDB();
        }
        return instance;
    }

    //kiem tra xem coi co username va password trong CSDL nay khong neu co thi cho phep dang nhap(return true)
    public User checkLogin(String username, String password){
        return UserDao.checkLogin(username, password); //chuyen qua lop UserDao cho no lam
    }

    //kiểm tra xem coi có mail và password trong CSDL này không nếu không có thì thêm vào CSDL
    public boolean register(String username, String password, String email, String day_birth, String gender, String card_number){
        return UserDao.register(username, password , email, day_birth, gender, card_number); //chuyen qua lop UserDao cho no lam
    }

    public boolean updateUser(int id, String userName, String email, String phone_number, String address, String zip_code,String city, String state,String country, String password) {
        return UserDao.updateUser(id,userName,email,phone_number, address,zip_code,city,state, country,password);
    }

    public User getUserById(int id) {
        return UserDao.getUserById(id);
    }
}
