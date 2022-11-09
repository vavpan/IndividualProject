package com.travelcompany.eshop.model;

import com.travelcompany.eshop.enums.Airline;
import com.travelcompany.eshop.enums.AirportCode;

/**
 * Main Itinerary class
 */
public class Itinerary extends PersistenceClass{

    private AirportCode departureAirportCode;
    private AirportCode destinationAirportCode;
    private String departureDate;
    private Airline airline;
    private double basicPrice;


    public Itinerary(){

    }

    public Itinerary(AirportCode departureAirportCode, AirportCode destinationAirportCode, String departureDate, Airline airline, double basicPrice) {
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.airline = airline;
        this.basicPrice = basicPrice;
    }

    public AirportCode getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(AirportCode departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public AirportCode getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(AirportCode destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

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
