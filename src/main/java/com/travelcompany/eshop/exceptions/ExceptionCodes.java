package com.travelcompany.eshop.exceptions;

/**
 * Printed messages to handle customer,itineraries and tickets exceptions
 *
 */
public class ExceptionCodes {

    public final static String CUSTOMER_EMAIL_INVALID = "Invalid customer email";
    public final static String CUSTOMER_IS_NULL = "Customer is null";

    public final static String CUSTOMER_MISSING_DATA = "Not all data are given to create a customer";
    public final static String AIRPORT_CODE_NOT_EXISTS ="Airport code is missing";
    public final static String ITINERARY_OR_CUSTOMER_NOT_EXISTS = "Itinerary or customer code does not exist";

    public final static String CUSTOMER_FILE_ERROR = "The customer file cannot be created";

    public final static String ITINERARY_FILE_ERROR = "The itinerary file cannot be created";

    public final static String TICKET_FILE_ERROR = "The ticket file cannot be created";

}
