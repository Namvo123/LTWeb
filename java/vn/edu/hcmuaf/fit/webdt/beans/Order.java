package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private List<Detail> details;
    private Date createdAt;
    private Date updatedAt;

    //none constructor
    public Order(){

    }

    //full constructor

    public Order(int id, User user, List<Detail> details, Date createdAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.details = details;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //GETTER AND SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
