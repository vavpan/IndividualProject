package com.travelcompany.eshop.service.impl;

import com.travelcompany.eshop.enums.AirportCode;
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
import com.travelcompany.eshop.service.TravelService;
import java.util.*;
import java.util.stream.Collectors;


/**
 *  Methods implemented to provide details for customers,itineraries,tickets
 */

public class TravelServiceImpl implements TravelService {

    private final CustomerRepository customerRepository;

    private final ItineraryRepository itineraryRepository;

    private final TicketRepository ticketRepository;

    public TravelServiceImpl(CustomerRepository customerRepository, ItineraryRepository itineraryRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.itineraryRepository = itineraryRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * Method to check customer exceptions
     * @param customer
     * @throws CustomerException
     */
    @Override
    public void register(Customer customer) throws CustomerException {

        if (customer == null) {
            throw new CustomerException(ExceptionCodes.CUSTOMER_IS_NULL);
        }
        if (customer.getEmail() == null) {

            throw new CustomerException(ExceptionCodes.CUSTOMER_IS_NULL);
        }
        if (customer.getEmail().contains("travelcompany")) {
            throw new CustomerException(ExceptionCodes.CUSTOMER_EMAIL_INVALID);

        }

        customerRepository.create(customer);

    }


    /**
     * Method to check itinerary exceptions
     * @param itinerary
     * @throws ItineraryException
     */
    @Override
    public void addItinerary(Itinerary itinerary) throws ItineraryException {

        if ((itinerary.getDepartureAirportCode() == null) && (itinerary.getDestinationAirportCode() == null)) {
            throw new ItineraryException(ExceptionCodes.AIRPORT_CODE_NOT_EXISTS);
        }
    }



    /**
     * Method to check Ticket exceptions
     * @param ticket
     * @throws TicketException
     */
    @Override
    public void createTicket(Ticket ticket) throws TicketException {
        List<Ticket> tickets = ticketRepository.read();
        List<Customer> customers = customerRepository.read();
        if (ticket.getItineraryId() > tickets.size() || ticket.getItineraryId() < tickets.size()) {
            throw new TicketException(ExceptionCodes.ITINERARY_OR_CUSTOMER_NOT_EXISTS);
        }
        if (ticket.getPassengerId() > customers.size() || ticket.getPassengerId() < customers.size()) {
            throw new TicketException(ExceptionCodes.ITINERARY_OR_CUSTOMER_NOT_EXISTS);

        }
        createTicket(ticket);

    }




    /**
     * Calculate total cost of tickets with discount
     * @return
     */
    @Override
    public double totalCostOfTickets() {
        List<Ticket> tickets = ticketRepository.read();
        List<Customer> customers = customerRepository.read();
        double sum = 0;

        for (Ticket ticket : tickets) {
            for (Customer customer : customers) {
                if (ticket.getPassengerId() == customer.getId()) {
                    sum = sum + ticketDiscount(ticket.getPaymentAmount(), ticket, customer);
                }
            }
        }
        return sum;
    }

    /**
     * Calculate total cost of tickets without discount
     * @return
     */
    @Override
    public double totalCostOfTicketsNoDiscount() {
        List<Ticket> tickets = ticketRepository.read();
        double sum = 0;

        for (Ticket ticket : tickets) {
            sum = sum + ticket.getPaymentAmount();
        }
        return sum;
    }

    /**
     * Calculate total number of tickets
     * @return
     */
    @Override
    public int totalNumberOfTickets() {
        List<Ticket> tickets = ticketRepository.read();
        int count = 0;
        for (int i = 0; i < tickets.size(); i++) {
            count++;
        }
        return count;
    }

    /**
     * We take a hashmap of customers who purchased tickets
     * @return
     */
    @Override
    public HashMap<Integer, Integer> purchasedTicketsByCustomers() {
        List<Ticket> tickets = ticketRepository.read();
        int value;

        // We use Hashmap to save the id of the customer and how many tickets has purchased
        // This method is called later by 2 other methods to help us calculate the customers who didn't purchase tickets
        // and the customer who purchased the most tickets
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Ticket ticket : tickets) {
            if (map.containsKey(ticket.getPassengerId())) {
                value = map.get(ticket.getPassengerId());
                map.put(ticket.getPassengerId(), value + 1);
            } else {
                map.put(ticket.getPassengerId(), 1);
            }
        }
        return map;
    }

    /**
     * Calculate customers who didn't purchase tickets
     */
    @Override
    public void noPurchasedTickets() {
        List<Customer> customers = customerRepository.read();

        HashMap<Integer, Integer> hashMap = purchasedTicketsByCustomers(); // We save in a hashmap customers who purchased tickets (calculated by other method)
        for (Customer customer : customers)

            if (!hashMap.containsKey(customer.getId())) { // Here we calculate to find customers who didn't purchase any ticket
                System.out.println(customer.getId() + "  " + customer.getName() + "  " + customer.getEmail() + "  " + customer.getNationality() + "  " + customer.getCategory()); // print customer info

            }
    }



    /**
     * Methods for page count&size
     * @param pageCount
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> findCustomers(int pageCount, int pageSize) {
        List<Customer> customers = customerRepository.read();
        List<Customer> returnCustomers = new ArrayList<>();
        int counter = -1;
        for (Customer customer : customers) {
            counter++;
            if (counter > (pageCount - 1) * pageSize - 1 && counter < (pageCount) * pageSize)
                returnCustomers.add(customer);
        }
        return returnCustomers;
    }

    /**
     * Methods for page count&size
     * @param pageCount
     * @param pageSize
     * @return
     */
    @Override
    public List<Itinerary> findItineraries(int pageCount, int pageSize) {
        List<Itinerary> itineraries = itineraryRepository.read();
        List<Itinerary> returnItineraries = new ArrayList<>();
        int counter = -1;
        for (Itinerary itinerary : itineraries) {
            counter++;
            if (counter > (pageCount - 1) * pageSize - 1 && counter < (pageCount) * pageSize)
                returnItineraries.add(itinerary);
        }
        return returnItineraries;
    }

    /**
     * Methods for page count&size
     * @param pageCount
     * @param pageSize
     * @return
     */
    @Override
    public List<Ticket> findTickets(int pageCount, int pageSize) {
        List<Ticket> tickets = ticketRepository.read();
        List<Ticket> returnTickets = new ArrayList<>();
        int counter = -1;
        for (Ticket ticket : tickets) {
            counter++;
            if (counter > (pageCount - 1) * pageSize - 1 && counter < (pageCount) * pageSize)
                returnTickets.add(ticket);
        }
        return returnTickets;
    }

    /**
     * Method to print itineraries and their details
     */
    @Override
    public void printItineraries() {
        List<Itinerary> itineraries = itineraryRepository.read();
        for (Itinerary itinerary : itineraries) {

            System.out.println(itinerary.getId() + "  " + itinerary.getDepartureAirportCode() + "  " + itinerary.getDestinationAirportCode() + "  " + itinerary.getAirline() + "  " + itinerary.getDepartureDate() + "  " + itinerary.getBasicPrice());
        }
    }


    /**
     * Method to print tickets and their details
     */
    @Override
    public void printTickets() {
        List<Ticket> tickets = ticketRepository.read();
        List<Customer> customers = customerRepository.read();

        for (Ticket ticket : tickets) {
            for (Customer customer : customers) {
                if (ticket.getPassengerId() == customer.getId()) {
                    System.out.println(ticket.getId() + "  " + ticket.getPassengerId() + "  " + ticket.getItineraryId() + "  " + ticket.getPaymentMethod() + "  " + ticketDiscount(ticket.getPaymentAmount(), ticket, customer));

                }
            }
        }
    }


    /**
     * Method to print customers and their details
     */
    @Override
    public void printCustomers() {
        List<Customer> customers = customerRepository.read();

        for (Customer customer : customers) {
            System.out.println(customer.getId() + "  " + customer.getName() + "  " + customer.getEmail() + "  " + customer.getNationality() + "  " + customer.getCategory());
        }
    }

    /**
     * Calculate discount for each category and payment method
     * @param amount
     * @param ticket
     * @param customer
     * @return
     */
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

    /**
     * Calculate maximum ticket purchased by customer
     * @return
     */
    public int mostTicketsPurchasedByCust() {
        List<Customer> customers = customerRepository.read();
        int maxValue;

        HashMap<Integer, Integer> hashtable = purchasedTicketsByCustomers(); // We take the map which contains the id of customers who purchased tickets

        maxValue = (Collections.max(hashtable.values()));
        for (Map.Entry<Integer, Integer> i : hashtable.entrySet()) {
            if (i.getValue() == maxValue)
                for (Customer customer : customers) {
                    if (customer.getId() == i.getKey()) {
                        return customer.getId(); // Customer id with the most purchases will be returned and we will use it to retrieve customer data
                    }
                }
        }
        return 0;
    }

    /**
     * Calculate the customer who purchased the most tickets
     */
    @Override
    public void totalAmountOfTicketsPurchased() {
        List<Ticket> tickets = ticketRepository.read();
        List<Customer> customers = customerRepository.read();

        double sum = 0;
        int id = mostTicketsPurchasedByCust();

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                System.out.println("(Id: " + customer.getId()  + ") " + "Customer -> " + customer.getName() + " has purchased most tickets");
            }
        }    for (Ticket ticket : tickets) {
            if (ticket.getPassengerId() == id) {
                sum = sum + ticket.getPaymentAmount();
            }
        }

        System.out.println("Total cost of his payments is: " + sum);
    }


    /**
     * Calculate offered itineraries per destination and per departure
     */
    @Override
    public void offeredItineraries(){
        List<Itinerary> itineraries = itineraryRepository.read();

        // We use groupingBy and Collectors.counting to count how many times a value appears in our List
        Map<AirportCode, Long> departureMap = itineraries.stream().collect(Collectors.groupingBy(e -> e.getDepartureAirportCode(),Collectors.counting()));
        Map<AirportCode, Long> destinationMap = itineraries.stream().collect(Collectors.groupingBy(e -> e.getDestinationAirportCode(),Collectors.counting()));

        System.out.println("Offered itineraries per departure " + departureMap);
        System.out.println("Offered itineraries per destination " + destinationMap);

    }

}







