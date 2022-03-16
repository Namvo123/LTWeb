package vn.edu.hcmuaf.fit.webdt.db;

import java.sql.*;

/*
*   ĐÂY LÀ LỚP DÙNG ĐỂ KẾT NÓI TỚI DATABASE THÔI CHỨ CHƯA LÂY DỮ LIỆU GÌ CẢ
*/
public class DBConnection {
    private static DBConnection instance;
    private Connection connection; //connection dung java.spl chu khong dung java.mysql
    private static final String DB_URL ="jdbc:mysql://localhost:3306/test1";
    private static String USER ="root";
    private static String PASS ="";

    //none constructor
    public DBConnection(){

    }
    //method nay la de chi goi mot lan
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    //kết nôi tới database , đây là bước 1 và 2
    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //driver name cua mysql
        connection = DriverManager.getConnection(DB_URL,USER, PASS);
    }

    //tạo PreparedStatement, đây là bước 3
    public PreparedStatement get(String sql){
        try {
            connect();
            return connection.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //nếu không dùng preparedStatement(cái trên) ta mới dùng Statement(cái này)
    public Statement getS(){
        try {
            connect();
            return connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
