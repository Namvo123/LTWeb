package vn.edu.hcmuaf.fit.webdt.beans;

import java.io.Serializable;

/*
 *  ĐÂY LÀ LỚP ĐỐI TƯỢNG USER
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String userName;
    private String password;
    private String email;
    private String phone_number;
    private String address;
    private String day_birth;
    private String gender;
    private String card_number;
    private String zip_code;
    private int id_role;
    private String city;
    private String state;
    private String country;


    //none constructor
    public User() {

    }

    //full constructor

    public User(int id, String userName, String password, String email, String phone_number, String address, String day_birth, String gender, String card_number, String zip_code, int id_role, String city, String state, String country) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.day_birth = day_birth;
        this.gender = gender;
        this.card_number = card_number;
        this.zip_code = zip_code;
        this.id_role = id_role;
        this.city = city;
        this.state = state;
        this.country = country;
    }


    //GETTER AND SETTER


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDay_birth() {
        return day_birth;
    }

    public void setDay_birth(String day_birth) {
        this.day_birth = day_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
