package com.travelcompany.eshop.menu;//package com.travelcompany.eshop.menu;
//
//import java.util.Scanner;
//
//public class ReportMenu {
//
//
//    public void menu(){
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("1: List of the total number and list of the cost of tickets for all customers");
//        System.out.println("2: List of the total offered itineraries per destination and offered itineraries per departure");
//        System.out.println("3: List of the customers with the most tickets and with the largest cost of purchases");
//        System.out.println("4: List of the customers who have not purchased any tickets");
////        System.out.println("5: Create new customer");
////        System.out.println("6: Create new ticket");
//        int choice = scanner.nextInt();
//
//        switch (choice){
//
//            case 1:
//
//
//                TravelServiceImpl customerService = new TravelServiceImpl();
//                customerService.printCustomers();
//                menu();
//
//                break;
//
//            case 2:
//                ItineraryImpl itinerary = new ItineraryImpl();
//                itinerary.printItineraries();
//
//                break;
//
//
//            case 3:
//
//
//                break;
//
//            case 4:
//
//                CustomerRepository customerRepository = new CustomerRepository();
//                customerRepository.checkNotPurchasedTickets();
//                menu();
//
//                break;
//
//            case 5:
//
//
//
//                break;
//
//
//            case 6:
//
//
//                break;
//
//
//
//
//        }
//
//
//
//
//    }
//
//}
