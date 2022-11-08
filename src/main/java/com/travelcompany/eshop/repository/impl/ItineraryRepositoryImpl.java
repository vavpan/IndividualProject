package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.repository.ItineraryRepository;

import java.util.ArrayList;
import java.util.List;

public class ItineraryRepositoryImpl extends RepositoryImpl<Itinerary> implements ItineraryRepository {



    @Override
    public void update(int itineraryId, double price) {
        Itinerary itinerary =  read(itineraryId);
        if(itinerary !=null){
            itinerary.setBasicPrice(price);
        }
    }

    @Override
    public List<Itinerary> read(String itineraryName) {
        List<Itinerary> returnItineraries =  new ArrayList<>();
//        for (Itinerary itinerary:read()){
//            if (itinerary.getName()!=null && itinerary.getName().contains(itinerary) )
//                returnProducts.add(itinerary) ;
//        }
        return returnItineraries;
    }
}
