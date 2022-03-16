package vn.edu.hcmuaf.fit.webdt.Dao;

import vn.edu.hcmuaf.fit.webdt.db.DBConnection;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*'
    ĐÂY LÀ LỚP THƯC HIỆN CÂU LỆNH SQL
 */
public class  UserDao_sai {
    private static UserDao_sai instance;

    //none constructor
    public UserDao_sai(){

    }

    //lop de chi goi UserDao mot lan
    public static UserDao_sai getInstance(){
        if(instance == null){
            instance = new UserDao_sai();
        }
        return instance;
    }

    //kiem tra xem coi co username va password trong CSDL nay khong neu co thi cho phep dang nhap(return true)
    public static boolean checkLogin(String username, String password) {
        String sql ="SELECT * FROM user WHERE userName = ? AND password =?";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);
        try {
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    //kiểm tra xem coi có username và password trong CSDL này không nếu không có thì thêm vào CSDL
    public static boolean register(String username, String password , String email, String day_birth, String gender, String card_number) {
        String sql = "INSERT user(userName, password, email, day_birth, gender, card_number) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);

        try{
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, card_number);

            if(!checkUsername(username)){
                int n = preparedStatement.executeUpdate();
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    //kiem tra xem username nay co trong database chua neu da co tra ve true
    public static boolean checkUsername(String username){
        String sql = "SELECT * FROM user WHERE userName = ?";
        PreparedStatement preparedStatement = DBConnection.getInstance().get(sql);
        try {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            String Un = "";
            while(rs.next()){
                Un = rs.getString("userName");
                if(Un.equalsIgnoreCase(username)){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * login with JDBI (CSDL)
     * có mã hóa password với thuật toán sha-256(chỉ mã hóa một chiều không mã hóa trở lại được)
     *
     * Ví dụ : MessageDigest md = MessageDigest.getInstace(“MD5”);
     * Một số các phương thức  thông dụng khác của class MessageDigest:
     * + void update(byte input) :  tạo lại digest  bởi byte được chỉ ra.
     * + void update(byte[] input) : tạo lại digest bởi mảng byte chỉ ra.
     * + void update(byte[] input, int offset, int len) : tạo lại digest bởi sử dụng 1 phần chỉ ra của 1 mảng byte.
     * + byte[] digest() : trả về giá trị của digest dưới dạng 1 mảng byte.
     *
     * BigInteger:tương tự như kiểu dữ liệu nguyên thuỷ int, long nhưng cho phép lưu trữ giá trị kiểu số nguyên
     * cực lớn, lớn hơn rất nhiều so với giá trị cực đại của int và long cho phép.
     * signum trong BigInteger là :
     * + -1 là số âm
     * + 0 là số 0
     * + 1 là số dương
     */
    //mã hóa password với thuật toán sha-256
    public String hashPassword(String password){
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");//chọn thuật toán để mã hóa (ở đây chọn sha-256)
            byte[] hash =sha256.digest(password.getBytes(StandardCharsets.UTF_8));//trả về giá trị của digest dưới dạng 1 mảng byte.
            BigInteger number = new BigInteger(1,hash);//1 là signum(xem ở trên)
            return number.toString(16); //dạng 16 bit (hexa)
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
//    public static void main(String[] args) {
//        register("Najoo","123","vonam706@gmail.com", "1234", "male", "12345");
//
//    }
}

