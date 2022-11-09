package com.travelcompany.eshop.service;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface TravelService {

    void register(Customer customer) throws CustomerException;

    void addItinerary(Itinerary itinerary) throws ItineraryException;

    List<Itinerary> searchItinerary(String itineraryName);

    void printCustomers();

    void printItineraries();

    void printTickets();
    double ticketDiscount(double amount, Ticket ticket , Customer customer);

    List<Customer> notPurchasedTickets(Ticket ticket , Customer customer);



    void createTicket(Ticket ticket) throws TicketException;



}
