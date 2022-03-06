package com.as2developers.myapplication.Modals;

public class UserModal {

    String Name , Address , Email, MobileNo;

    public UserModal(String name, String address, String email, String mobileNo) {
        Name = name;
        Address = address;
        Email = email;
        MobileNo = mobileNo;
    }

    public UserModal() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
