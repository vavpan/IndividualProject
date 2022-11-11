package com.travelcompany.eshop.service;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;

import java.util.HashMap;
import java.util.List;

/**
 * An interface to provide methods for customers,itineraries and tickets info
 */
public interface TravelService {

    void register(Customer customer) throws CustomerException;

    void addItinerary(Itinerary itinerary) throws ItineraryException;

    List<Customer> findCustomers(int pageCount, int pageSize);
    List<Itinerary> findItineraries(int pageCount, int pageSize);
    List<Ticket> findTickets(int pageCount, int pageSize);

    void printCustomers();

    void printItineraries();

    void printTickets();

    double ticketDiscount(double amount, Ticket ticket , Customer customer);

    void createTicket(Ticket ticket) throws TicketException;

    double totalCostOfTickets();

    double totalCostOfTicketsNoDiscount();

    int totalNumberOfTickets();

    HashMap<Integer, Integer> purchasedTicketsByCustomers();

    void noPurchasedTickets();

    int mostTicketsPurchasedByCust();

     void totalAmountOfTicketsPurchased();

     void offeredItineraries();
}
