package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.PaymentMethod;

/**
 * Main Ticket class
 */
public class Ticket extends PersistenceClass{

    private int passengerId;
    private int itineraryId;
    private PaymentMethod paymentMethod;
    private double paymentAmount;

    // Default Constructor
    public Ticket(){

    }
    public Ticket(int passengerId, int itineraryId, PaymentMethod paymentMethod, double paymentAmount) {
        this.passengerId = passengerId;
        this.itineraryId = itineraryId;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    // GETTERS/SETTERS METHODS
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    // toString method to print our values
    @Override
    public String toString() {
        return "Ticket{" +
                "passengerId=" + passengerId +
                ", itineraryId=" + itineraryId +
                ", paymentMethod=" + paymentMethod +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}