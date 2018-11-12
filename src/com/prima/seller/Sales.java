package com.prima.seller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for accepting the sales object
 */
public class Sales {
    private Date date;
    private String email;
    private PaymentMethodEnum paymentMethod;
    private Map<String, Integer> itemCounts = new HashMap<>();

    public Sales(Date date, String email, PaymentMethodEnum paymentMethod, Map<String, Integer>
        itemCounts) {
        this.date = date;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.itemCounts = itemCounts;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public Map<String, Integer> getItemCounts() {
        return itemCounts;
    }

    @Override
    public String toString() {
        return "Sales{" + "date=" + date + ", email='" + email + '\'' + ", paymentMethod=" +
            paymentMethod + ", itemCounts=" + itemCounts + '}';
    }
}
