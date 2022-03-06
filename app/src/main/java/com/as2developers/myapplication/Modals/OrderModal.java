package com.as2developers.myapplication.Modals;

public class OrderModal {

    String items, Date,PaymentMode;

    public OrderModal(String items, String date, String paymentMode) {
        this.items = items;
        Date = date;
        PaymentMode = paymentMode;
    }

    public OrderModal() {
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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
}
