package com.as2developers.myapplication.Modals;

public class OrderModal {

    String items, Address , Date,PaymentMode,Latitude,Longitude;

    public OrderModal(String items, String address, String date, String paymentMode, String latitude, String longitude) {
        this.items = items;
        Address = address;
        Date = date;
        PaymentMode = paymentMode;
        Latitude = latitude;
        Longitude = longitude;
    }

    public OrderModal() {
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPaymentMode() {
        return PaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        PaymentMode = paymentMode;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
