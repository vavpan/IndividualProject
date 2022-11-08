package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.PaymentMethod;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends PersistenceClass{

    private int passengerId;
    private int itineraryId;
    private PaymentMethod paymentMethod;
    private double paymentAmount;


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
