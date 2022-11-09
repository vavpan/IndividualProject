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

import java.util.ArrayList;
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
    public void offeredItineraries() {
       List<Itinerary> itineraries = itineraryRepository.read();
         Collections.sort(itineraries, Comparator.comparing(Itinerary::getBasicPrice));
         printItineraries();
    }

    @Override
    public double totalCostOfTickets() {
        List<Ticket> tickets = ticketRepository.read();
        List<Customer> customers = customerRepository.read();
        double sum = 0;

        for(Ticket ticket : tickets){
            for (Customer customer : customers){
                if (ticket.getPassengerId() == customer.getId()){
                   sum = sum + ticketDiscount(ticket.getPaymentAmount(), ticket , customer);
                }
            }
        }
        return sum;
    }

    @Override
    public double totalCostOfTicketsNoDiscount() {
        List<Ticket> tickets = ticketRepository.read();
        double sum = 0;

        for (Ticket ticket: tickets){
            sum = sum + ticket.getPaymentAmount();
        }
        return sum;
    }

    @Override
    public int totalNumberOfTickets() {
        List<Ticket> tickets = ticketRepository.read();
        int count = 0;
        for (int i = 0; i < tickets.size(); i++){
            count++;
        }
        return count;
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
        List<Customer> customers = customerRepository.read();

        for(Ticket ticket : tickets){
            for (Customer customer : customers){
                if (ticket.getPassengerId() == customer.getId()){
                    System.out.println(ticket.getId() + "  " + ticket.getPassengerId() + "  " + ticket.getItineraryId() + "  " + ticket.getPaymentMethod() + "  " + ticketDiscount(ticket.getPaymentAmount(), ticket, customer));

                }
            }
        }
    }

    @Override
    public void printCustomers() {
        List<Customer> customers = customerRepository.read();

        for (Customer customer : customers) {
            System.out.println(customer.getId() + "  " + customer.getName() + "  " + customer.getEmail() + "  " + customer.getNationality() + "  " + customer.getCategory());
        }
    }

    @Override
    public double ticketDiscount(double amount, Ticket ticket, Customer customer) {
        double discount;
        if (customer.getCategory().toString() == "Individual" && ticket.getPaymentMethod().toString() == "Cash") {
            discount = amount * 0.20;
            return amount + discount;
        } else if (customer.getCategory().toString() == "Business" && ticket.getPaymentMethod().toString() == "Cash") {
            discount = amount * 0.10;
            return amount - discount;
        } else if (customer.getCategory().toString() == "Individual" && ticket.getPaymentMethod().toString() == "CreditCard") {
            discount = amount * 0.10;
            return amount + discount;
        } else if (customer.getCategory().toString() == "Business" && ticket.getPaymentMethod().toString() == "CreditCard") {
            discount = amount * 0.20;
            return amount - discount;
        }
        return 0;
    }








    }







