package com.travelcompany.eshop.service;

import com.travelcompany.eshop.exceptions.CustomerException;
import com.travelcompany.eshop.exceptions.ItineraryException;
import com.travelcompany.eshop.exceptions.TicketException;

public interface IoServices {


    void saveCustomerToCsv(String filename) throws CustomerException;

    void saveItineraryToCsv(String filename) throws ItineraryException;

    void saveTicketToCsv(String filename) throws TicketException;
}
