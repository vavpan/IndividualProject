package com.travelcompany.eshop;


import com.travelcompany.eshop.menu.ReportMenu;


/**
 * Main class in which you start the app
 */
public class Main {
    public static void main(String[] args) {

        // We create a ReportMenu Object to call our main menu method
        ReportMenu reportMenu = new ReportMenu();
        reportMenu.menu(); // Main menu called

    }

}


