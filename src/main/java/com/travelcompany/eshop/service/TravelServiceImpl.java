package com.travelcompany.eshop.service;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ExceptionCodes;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TravelServiceImpl implements TravelService {

    private final CustomerRepository customerRepository;

    private final ItineraryRepository itineraryRepository;

    private final TicketRepository ticketRepository;

    public TravelServiceImpl(CustomerRepository customerRepository, ItineraryRepository itineraryRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.itineraryRepository = itineraryRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void register(Customer customer) throws CustomerException {

        if (customer == null) {
            throw new CustomerException(ExceptionCodes.CUSTOMER_IS_NULL);
        }
        if (customer.getEmail() == null){

            throw new CustomerException(ExceptionCodes.CUSTOMER_IS_NULL);
        }
        if (customer.getEmail().contains("travelcompany")) {
            throw new CustomerException(ExceptionCodes.CUSTOMER_EMAIL_INVALID);

        }

        customerRepository.create(customer);

    }

    @Override
    public void createTicket(Ticket ticket) throws TicketException {

        if (  ticket.getItineraryId() > 9 || ticket.getItineraryId() < 0) {
            throw new TicketException(ExceptionCodes.ITINERARY_OR_CUSTOMER_NOT_EXISTS);
        }
        if (ticket.getPassengerId() > 9 || ticket.getPassengerId() < 0) {
            throw new TicketException(ExceptionCodes.ITINERARY_OR_CUSTOMER_NOT_EXISTS);

        }
        createTicket(ticket);

    }

    @Override
    public void addItinerary(Itinerary itinerary) throws ItineraryException {

        if (  ( itinerary.getDepartureAirportCode() == null) && ( itinerary.getDestinationAirportCode() == null)) {
            throw new ItineraryException(ExceptionCodes.AIRPORT_CODE_NOT_EXISTS);
        }
    }

    @Override
    public List<Itinerary> searchItinerary(String itineraryName) {
        return null;
    }



    @Override
    public void printItineraries() {
        List<Itinerary> itineraries = itineraryRepository.read();
        for (Itinerary itinerary : itineraries) {

            System.out.println(itinerary.getId() + "  " + itinerary.getDepartureAirportCode() + "  " + itinerary.getDestinationAirportCode() + "  " + itinerary.getAirline() + "  " + itinerary.getBasicPrice());
        }
    }

    @Override
    public void printTickets() {
        List<Ticket> tickets = ticketRepository.read();
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getId() + "  " + ticket.getPassengerId() + "  " + ticket.getItineraryId() + "  " + ticket.getPaymentMethod() + "  " + ticketDiscount(ticket.getPaymentAmount() , ticket));
        }
    }

    @Override
    public void mostTicketsPurchased() {

    }

    @Override
    public void offeredItinerariesPerDestination() {

    }



    @Override
    public void offeredItinerariesPerDeparture() {
        List<Itinerary> itineraries = itineraryRepository.read();

        Collections.sort(itineraries, Comparator.comparing(Itinerary::getBasicPrice));

        printItineraries();


    }

    @Override
    public List<Customer> notPurchasedTickets() {
        List<Customer> customers = customerRepository.read();
        List<Ticket> tickets = ticketRepository.read();


        return null;
    }



    @Override
    public void printCustomers() {
        List<Customer> customers = customerRepository.read();

        for (Customer customer : customers) {
            System.out.println(customer.getId() + "  " + customer.getName() + "  " + customer.getEmail() + "  " + customer.getNationality() + "  " + customer.getCategory());
        }
    }

    @Override
    public double ticketDiscount(double amount,Ticket ticket) {
        double discount;
        if ( (ticket.getPassengerId() == 3 || ticket.getPassengerId() == 6 || ticket.getPassengerId() == 7) && ticket.getPaymentMethod().toString() == "Cash") {
            discount = amount * 0.10;
            return amount - discount;
        }
        else if ( (ticket.getPassengerId() == 3 || ticket.getPassengerId() == 6 || ticket.getPassengerId() == 7) && ticket.getPaymentMethod().toString() == "CreditCard") {
            discount = amount * 0.20;
            return amount - discount;
        }
        if ( (ticket.getPassengerId() == 1 || ticket.getPassengerId() == 2 || ticket.getPassengerId() == 4 || ticket.getPassengerId() == 5 || ticket.getPassengerId() == 8 || ticket.getPassengerId() == 9) && ticket.getPaymentMethod().toString() == "Cash") {
            discount = amount * 0.20;
            return amount + discount;
        }
        else if ( (ticket.getPassengerId() == 1 || ticket.getPassengerId() == 2 || ticket.getPassengerId() == 4 || ticket.getPassengerId() == 5 || ticket.getPassengerId() == 8 || ticket.getPassengerId() == 9) && ticket.getPaymentMethod().toString() == "CreditCard") {
            discount = amount * 0.10;
            return amount + discount;
        }
        return 0;
    }










}
