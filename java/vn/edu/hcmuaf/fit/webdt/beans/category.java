package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;

public class category implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name_category;

    //constructor
    public category(int id, String name_category) {
        this.id = id;
        this.name_category = name_category;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }
}
