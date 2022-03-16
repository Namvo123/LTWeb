package vn.edu.hcmuaf.fit.webdt.db;

import java.io.IOException;
import java.util.Properties;

/*
 * LỚP NÀY ĐỂ LẤY CÁC GIÁ TRỊ(VALUE) TỪ DB.PROPERTIES Ở RESOURCES
 */
public class DBProperties {
    private static Properties prop = new Properties();

    static{
        try{
            prop.load(DBProperties.class.getClassLoader().getResourceAsStream("db.properties"));//đi đến db.properties để lấy dữ liệu
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //lấy dữ liệu từ db.properties và chuyền nó thành string bằng toString()
    public static String getDbHost(){
        return prop.get("db.host").toString();//lấy value của db.host từ db.properties
    }
    public static String getDbPort(){
        return prop.get("db.port").toString();//lấy value của db.port từ db.properties
    }
    public static String getDbName(){
        return prop.get("db.databaseName").toString();//lấy value của db.databaseName từ db.properties
    }
    public static String getUsername(){
        return prop.get("db.username").toString();//lấy value của db.username từ db.properties
    }
    public static String getPassword(){
        return prop.get("db.password").toString();//lấy value của db.password từ db.properties
    }

    //
}
