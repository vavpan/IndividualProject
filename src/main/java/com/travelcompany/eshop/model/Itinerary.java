package com.travelcompany.eshop.model;



import com.travelcompany.eshop.enums.Airline;
import com.travelcompany.eshop.enums.AirportCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Itinerary extends PersistenceClass{

    private AirportCode departureAirportCode;
    private AirportCode destinationAirportCode;
    private String departureDate;
    private Airline airline;
    private double basicPrice;


    @Override
    public String toString() {
        return "Itinerary{" +
                "departureAirportCode=" + departureAirportCode +
                ", destinationAirportCode=" + destinationAirportCode +
                ", departureDate='" + departureDate + '\'' +
                ", airline=" + airline +
                ", basicPrice=" + basicPrice +
                '}';
    }
}
