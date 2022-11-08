package com.travelcompany.eshop.menu;


import com.travelcompany.eshop.repository.impl.ItineraryRepositoryImpl;
import com.travelcompany.eshop.service.TravelServiceImpl;

import java.util.Scanner;

public class ReportMenu {

    // This method should be run in main
    public void menu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("1: List of the total number and list of the cost of tickets for all customers");
        System.out.println("2: List of the total offered itineraries per destination and offered itineraries per departure");
        System.out.println("3: List of the customers with the most tickets and with the largest cost of purchases");
        System.out.println("4: List of the customers who have not purchased any tickets");

        int choice = scanner.nextInt();

        switch (choice){

            case 1:

                break;

            case 2:

                break;


            case 3:

                break;

            case 4:

                break;

            case 5:

                break;


            case 6:

                break;


        }

    }

}
