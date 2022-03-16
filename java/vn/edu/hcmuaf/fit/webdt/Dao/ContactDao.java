package vn.edu.hcmuaf.fit.webdt.Dao;

import vn.edu.hcmuaf.fit.webdt.db.JDBIConnector;

public class ContactDao {
    private static ContactDao instance;

    //none constructor
    public ContactDao(){

    }

    public static ContactDao getInstance(){
        if(instance == null){
            instance = new ContactDao();
        }
            return instance;
    }

    public static boolean saveContact(String name, String email, String sub, String message) {
        int i =JDBIConnector.get().withHandle(handle ->
                handle.createUpdate("INSERT INTO Contact(name,email,subject,message) VALUES(?,?,?,?)")
                        .bind(0,name)
                        .bind(1,email)
                        .bind(2,sub)
                        .bind(3,message).execute()
        );
        return i ==1;
    }
}
