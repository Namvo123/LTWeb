package vn.edu.hcmuaf.fit.webdt.Service;

import vn.edu.hcmuaf.fit.webdt.Dao.ContactDao;

public class ContactServiceWithDB {
    private static ContactServiceWithDB instance;

    //none constructor
    public ContactServiceWithDB() {

    }

    public static ContactServiceWithDB getInstance() {
        if (instance == null) {
            instance = new ContactServiceWithDB();
        }
        return instance;
    }


    public boolean saveContact(String name, String email, String sub, String message) {
        return ContactDao.saveContact(name, email, sub, message);
    }
}
