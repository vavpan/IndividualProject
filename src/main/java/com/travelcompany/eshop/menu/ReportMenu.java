package com.travelcompany.eshop.menu;


import com.google.gson.GsonBuilder;
import com.travelcompany.eshop.enums.Airline;
import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;
import com.travelcompany.eshop.repository.impl.CustomerRepositoryImpl;
import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.repository.impl.TicketRepositoryImpl;
import com.travelcompany.eshop.service.IoServices;
import com.travelcompany.eshop.service.TravelService;
import com.travelcompany.eshop.service.impl.IoServiceImpl;
import com.travelcompany.eshop.service.impl.TravelServiceImpl;
import com.travelcompany.eshop.util.DataImport;

import java.util.List;
import java.util.Scanner;

/**
 * Main menu when application starts
 */

public class ReportMenu {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    ItineraryRepository itineraryRepository = new ItineraryRepositoryImpl();
    TicketRepository ticketRepository = new TicketRepositoryImpl();
    TravelService travelService = new TravelServiceImpl(customerRepository,itineraryRepository, ticketRepository);
    DataImport dataImport = new DataImport(customerRepository,itineraryRepository,ticketRepository);

    public  void menu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an option {0-7}");
        System.out.println("0: Print all(3) tables (Customers - Itineraries - Tickets");
        System.out.println("1: List of the total number and list of the cost of tickets for all customers");
        System.out.println("2: List of the total offered itineraries per destination and offered itineraries per departure");
        System.out.println("3: List of the customers with the most tickets and with the largest cost of purchases");
        System.out.println("4: List of the customers who have not purchased any tickets");
        System.out.println("5: View custom exceptions");
        System.out.println("EXTRAS:");
        System.out.println("6: View JSON format and Paging");
        System.out.println("7: Save collections to CSV file");

        int choice = scanner.nextInt();

        switch (choice){

            case 0:

                dataImport.insertCustomer();
                travelService.printCustomers();
                System.out.println("\n");
                dataImport.insertItinerary();
                travelService.printItineraries();
                System.out.println("\n");
                dataImport.insertTickets();
                travelService.printTickets();
                System.out.println("\n");



                break;

            case 1:

                dataImport.insertCustomer();
                dataImport.insertTickets();
                System.out.println("Total number of tickets is: " + travelService.totalNumberOfTickets());
                System.out.println("Total cost of tickets (Discounts and surcharges are included depends on the customer): " + travelService.totalCostOfTickets());
                System.out.println("Total cost of tickets (No discounts or surcharges included): " + travelService.totalCostOfTicketsNoDiscount());
                System.out.println("\n");


                break;

            case 2:

                dataImport.insertItinerary();
                travelService.offeredItineraries();


                break;


            case 3:
                dataImport.insertCustomer();
                dataImport.insertTickets();
                travelService.totalAmountOfTicketsPurchased();
                break;

            case 4:
                dataImport.insertCustomer();
                dataImport.insertTickets();
                travelService.noPurchasedTickets();

                break;

            case 5:

                customExceptions();


                break;

            case 6:

                jsonFormatMenu();
                break;

            case 7:
                dataImport.insertCustomer();
                dataImport.insertItinerary();
                dataImport.insertTickets();

                IoServices ioServices = new IoServiceImpl(customerRepository, itineraryRepository, ticketRepository);
                try {
                    ioServices.saveCustomerToCsv("Customer.csv");
                    ioServices.saveItineraryToCsv("Itinerary.csv");
                    ioServices.saveTicketToCsv("Ticket.csv");
                }catch (CustomerException ex){
                } catch (ItineraryException e) {
                    throw new RuntimeException(e);
                } catch (TicketException e) {
                    throw new RuntimeException(e);
                }

                break;


        }
    }

    /**
     * Custom exceptions according to the instructions
     */
    public  void customExceptions(){

        try{

            Customer customer = new Customer();
            travelService.register(customer);
        }catch (CustomerException e){
            System.out.println("Customer exception \n" + e.getMessage());
            System.out.println("\n");
        }

        try {
            Ticket ticket = new Ticket(4,15, PaymentMethod.CreditCard,200);
            travelService.createTicket(ticket);
        }catch (TicketException e){
            System.out.println("Ticket exception \n" + e.getMessage());
            System.out.println("\n");

        }

        try{
            Itinerary itinerary = new Itinerary(null,null,"22", Airline.SkyLines,300.00);
            travelService.addItinerary(itinerary);
        }catch (ItineraryException e){
            System.out.println("Itinerary exception \n" + e.getMessage());
            System.out.println("\n");

        }




    }

    /**
     * Json and Paging format
     */
    public void jsonFormatMenu(){
        int pageCount = 1;
        int pageSize = 9;
        Scanner scanner = new Scanner(System.in);
        System.out.println("JSON FORMAT FOR CUSTOMERS-ITINERARIES-TICKETS");
        System.out.println("1: Print customers (JSON format)");
        System.out.println("2: Print itineraries (JSON format)");
        System.out.println("3: Print tickets (JSON format)");
        int choice = scanner.nextInt();

        switch (choice){

            case 1:
                dataImport.insertCustomer();
                List<Customer> customers = travelService.findCustomers(pageCount,pageSize);
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(customers));

                break;

            case 2:
                dataImport.insertItinerary();
                List<Itinerary> itineraries = travelService.findItineraries(pageCount,pageSize);
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(itineraries));


                break;

            case 3:
                dataImport.insertTickets();
                List<Ticket> tickets = travelService.findTickets(pageCount,pageSize);
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(tickets));

                break;
        }

    }



}
