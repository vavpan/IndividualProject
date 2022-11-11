package com.travelcompany.eshop.util;

import com.travelcompany.eshop.enums.Airline;
import com.travelcompany.eshop.enums.AirportCode;
import com.travelcompany.eshop.enums.Category;
import com.travelcompany.eshop.enums.PaymentMethod;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.CustomerRepository;
import com.travelcompany.eshop.repository.ItineraryRepository;
import com.travelcompany.eshop.repository.TicketRepository;

/**
 * Customer,Itinerary and Ticket objects are initialized
 */

public class DataImport {


    private final CustomerRepository customerRepository;

    private final ItineraryRepository itineraryRepository;

    private final TicketRepository ticketRepository;


    /**
     * Constructor
     * @param customerRepository
     * @param itineraryRepository
     * @param ticketRepository
     */
    public DataImport(CustomerRepository customerRepository, ItineraryRepository itineraryRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.itineraryRepository = itineraryRepository;
        this.ticketRepository = ticketRepository;
    }


    /**
     * Data for customers
     */
    private final static String[] CUSTOMERS = {
            "Maria Iordanou, miordanou@mail.com , Athens , Greek , Individual",
            "Dimitriou Dimitrios , ddimitriou@mail.com , Athens , Greek , Individual",
            "Ioannis Ioannou, iioannou@mail.com , Athens , Greek , Business",
            "Antonio Molinari , amolinari@mail.com  , Milan , Greek , Individual",
            "Frederico Rossi  , frossi@mail.com  , Milan , Italian  , Individual",
            "Mario Conti , mconti@mail.com   , Rome , Italian , Business",
            "Nathan Martin , nmartin@mail.com   , Lyon, French , Business",
            "Enzo Collin  , ecollin@mail.com   , Lyon , French , Individual",
            "Frederic Michel  , fmichel@mail.com  , Athens, French , Individual",
    };

    /**
     * Data for itineraries
     */
    private final static String[] ITINERARIES = {

            "ATH, PAR, 22/02/2022 13:35 , SkyLines, 300",
            "ATH, LON, 22/02/2022 13:40 , SkyLines, 420",
            "ATH, AMS, 22/02/2022 13:45 , SkyLines, 280",
            "ATH, PAR, 22/02/2022 14:20 , SkyLines, 310",
            "ATH, DUB, 22/02/2022 14:35 , SkyLines, 880",
            "ATH, FRA, 22/02/2022 14:55 , SkyLines, 380",
            "ATH, FRA, 22/02/2022 15:35 , SkyLines, 350",
            "ATH, MEX, 22/02/2022 16:00 , SkyLines, 1020",
            "ATH, DUB, 22/02/2022 16:35 , SkyLines, 770",

    };

    /**
     * Data for tickets
     */
    private final static String[] TICKETS = {

            "1, 2, Cash, 420",
            "2, 3, Cash, 280",
            "3, 3, CreditCard, 280",
            "2, 4, CreditCard, 310",
            "3, 4, Cash, 310",
            "4, 7, CreditCard, 350",
            "5, 7, CreditCard, 350",
            "2, 10, Cash, 0",
            "1, 3, Cash, 280",

    };

    /**
     * Customer data inserted
     */
    public void insertCustomer(){

        for (String customerString : CUSTOMERS){
            String[] words = customerString.split(",");
            Customer customer = new Customer();
            customer.setName(words[0].trim());
            customer.setEmail(words[1].trim());
            customer.setAddress(words[2].trim());
            customer.setNationality(words[3].trim());
            customer.setCategory(Category.valueOf(words[4].trim()));
            customerRepository.create(customer);
        }
    }

    /**
     * Itinerary data inserted
     */
    public void insertItinerary(){

        for (String itineraryString : ITINERARIES){
            String[] words = itineraryString.split(",");
            Itinerary itinerary = new Itinerary();
            itinerary.setDepartureAirportCode(AirportCode.valueOf(words[0].trim()));
            itinerary.setDestinationAirportCode(AirportCode.valueOf(words[1].trim()));
            itinerary.setDepartureDate(words[2].trim());
            itinerary.setAirline(Airline.valueOf(words[3].trim()));
            itinerary.setBasicPrice(Double.parseDouble(words[4].trim()));
            itineraryRepository.create(itinerary);
        }
    }



    /**
     * Ticket data inserted
     */
    public void insertTickets(){

        for (String ticketString: TICKETS){
            String[] words = ticketString.split(",");
            Ticket ticket = new Ticket();
            ticket.setPassengerId(Integer.parseInt(words[0].trim()));
            ticket.setItineraryId(Integer.parseInt(words[1].trim()));
            ticket.setPaymentMethod(PaymentMethod.valueOf(words[2].trim()));
            ticket.setPaymentAmount(Double.parseDouble(words[3].trim()));
            ticketRepository.create(ticket);

        }
    }
}




