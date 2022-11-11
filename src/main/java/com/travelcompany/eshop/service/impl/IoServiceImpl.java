package com.travelcompany.eshop.service.impl;

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
import com.travelcompany.eshop.service.IoServices;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class IoServiceImpl implements IoServices {

    private CustomerRepository customerRepository;
    private ItineraryRepository itineraryRepository;

    private TicketRepository ticketRepository;


    public IoServiceImpl(CustomerRepository customerRepository, ItineraryRepository itineraryRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.itineraryRepository = itineraryRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void saveCustomerToCsv(String filename) throws CustomerException {

        File file = new File(filename);
        List<Customer> customers = customerRepository.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("id,name,email,address,nationality,Category");
            for (Customer customer : customers) {
                pw.println(
                        customer.getId()
                                + "," + customer.getName()
                                + "," + customer.getName()
                                + "," + customer.getEmail()
                                + "," + customer.getAddress()
                                + "," + customer.getNationality()
                                + "," + customer.getCategory());
            }


        } catch (FileNotFoundException ex) {
            throw new CustomerException(ExceptionCodes.CUSTOMER_FILE_ERROR);
        }

    }

    @Override
    public void saveItineraryToCsv(String filename) throws ItineraryException {

        File file = new File(filename);
        List<Itinerary> itineraries = itineraryRepository.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("Id,Departure Code,Destination Code, Departure Date,Airline,Basic Price");
            for (Itinerary itinerary : itineraries) {
                pw.println(
                        itinerary.getId()
                                + "," + itinerary.getDepartureAirportCode()
                                + "," + itinerary.getDestinationAirportCode()
                                + "," + itinerary.getDepartureDate()
                                + "," + itinerary.getAirline()
                                + "," + itinerary.getBasicPrice());
            }


        } catch (FileNotFoundException ex) {
            throw new ItineraryException(ExceptionCodes.ITINERARY_FILE_ERROR);
        }

    }

    @Override
    public void saveTicketToCsv(String filename) throws TicketException {

        File file = new File(filename);
        List<Ticket> tickets = ticketRepository.read();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("Id,Passenger Id Itinerary Id,Payment Method, Payment Amount");
            for (Ticket ticket: tickets) {
                pw.println(
                        ticket.getId()
                                + "," + ticket.getPassengerId()
                                + "," + ticket.getItineraryId()
                                + "," + ticket.getPaymentMethod()
                                + "," + ticket.getPaymentAmount());
            }


        } catch (FileNotFoundException ex) {
            throw new TicketException(ExceptionCodes.TICKET_FILE_ERROR);
        }

    }

}
