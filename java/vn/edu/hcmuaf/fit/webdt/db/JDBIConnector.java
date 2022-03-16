package vn.edu.hcmuaf.fit.webdt.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

import static vn.edu.hcmuaf.fit.webdt.db.DBProperties.*;//import nhung phuong thuc tu DBProperties

/**
 * LỚP NÀY LÀ DÙNG JDBI (KHÔNG DÙNG JDBC) ĐỂ KẾT NỐI XUỐNG CSDL
 */
public class JDBIConnector {
    private static Jdbi jdbi;

    private static void makeConnect(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + getDbHost() +":" +getDbPort() +"/" +getDbName());
        dataSource.setUser(getUsername());
        dataSource.setPassword(getPassword());
        try{//khong biet luon
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);//khong biet can xem lai
        }
        jdbi =Jdbi.create(dataSource);
    }

    //none constructor
    public JDBIConnector(){

    }
    //hàm get này giống getInstance() dùng để chỉ gọi Jdbi một lần không new cái khác nữa
    public static Jdbi get(){
        if(jdbi == null){
            makeConnect();
        }
        return jdbi;
    }

    //
}
