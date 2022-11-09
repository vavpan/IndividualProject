package com.travelcompany.eshop;

import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import com.travelcompany.eshop.service.TravelServiceImpl;
import com.travelcompany.eshop.service.TravelService;
import com.travelcompany.eshop.util.DataImport;

public class Main {
    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        ItineraryRepository itineraryRepository = new ItineraryRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();

        TravelService travelService = new TravelServiceImpl(customerRepository,itineraryRepository, ticketRepository);
        DataImport dataImport = new DataImport(customerRepository,itineraryRepository,ticketRepository);

        dataImport.insertCustomer();
//        travelService.printCustomers();

        dataImport.insertItinerary();
//        travelService.printItineraries();

        dataImport.insertTickets();
        travelService.printTickets();


//        try{
//
//            Customer customer = new Customer();
//            travelService.register(customer);
//        }catch (CustomerException e){
//            System.out.println("Customer exception \n" + e.getMessage());
//        }


//        try{
//            Itinerary itinerary = new Itinerary(null,null,"22", Airline.SkyLines,300.00);
//            travelService.addItinerary(itinerary);
//        }catch (ItineraryException e){
//            System.out.println("Itinerary exception \n" + e.getMessage());
//        }


//        try {
//            Ticket ticket = new Ticket(4,15,PaymentMethod.CreditCard,200);
//            travelService.createTicket(ticket);
//        }catch (TicketException e){
//            System.out.println("Ticket exception \n" + e.getMessage());
//        }

//        String credit = "CreditCard";
//        System.out.println(PaymentMethod.CreditCard.toString() == credit);

//        travelService.notPurchasedTickets();


//        travelService.offeredItinerariesPerDestination();
    }
}

