package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.model.Ticket;
import java.util.List;

public interface TicketRepository extends Repository<Ticket> {

    List<Ticket> read(String ticketName);


}
