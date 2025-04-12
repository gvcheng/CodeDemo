package com.gvc.domain;

import java.util.Date;

public class User {
    private Integer idabc;
    private String usernameabc;
    private Date birthdayabc;
    private String sexabc;
    private String addressabc;

    @Override
    public String toString() {
        return "User{" +
                "idabc=" + idabc +
                ", usernameabc='" + usernameabc + '\'' +
                ", birthdayabc=" + birthdayabc +
                ", sexabc='" + sexabc + '\'' +
                ", addressabc='" + addressabc + '\'' +
                '}';
    }

    public User() {

    }

    public User(Integer idabc, String usernameabc, Date birthdayabc, String sexabc, String addressabc) {
        this.idabc = idabc;
        this.usernameabc = usernameabc;
        this.birthdayabc = birthdayabc;
        this.sexabc = sexabc;
        this.addressabc = addressabc;
    }

    public Integer getIdabc() {
        return idabc;
    }

    public void setIdabc(Integer idabc) {
        this.idabc = idabc;
    }

    public String getUsernameabc() {
        return usernameabc;
    }

    public void setUsernameabc(String usernameabc) {
        this.usernameabc = usernameabc;
    }

    public Date getBirthdayabc() {
        return birthdayabc;
    }

    public void setBirthdayabc(Date birthdayabc) {
        this.birthdayabc = birthdayabc;
    }

    public String getSexabc() {
        return sexabc;
    }

    public void setSexabc(String sexabc) {
        this.sexabc = sexabc;
    }

    public String getAddressabc() {
        return addressabc;
    }

    public void setAddressabc(String addressabc) {
        this.addressabc = addressabc;
    }
}
