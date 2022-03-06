package com.as2developers.myapplication.Modals;

public class OrderModal {

    String items,OrderId , Date,PaymentMode;

    public OrderModal(String items, String orderId, String date, String paymentMode) {
        this.items = items;
        OrderId = orderId;
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

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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
