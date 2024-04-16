package com.cowdungpaints.dto;

import com.cowdungpaints.controllers.PaymentRequest;
import com.cowdungpaints.entities.Order;


public class OrderPaymentRequestDTO {
    private Order order;
    private PaymentRequest paymentRequest;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }
}
