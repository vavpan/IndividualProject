package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Itinerary;
import java.util.List;

public interface ItineraryRepository extends Repository<Itinerary> {

    void update(int productId, double price);

    List<Itinerary> read(String itineraryName);

}
