package com.travelcompany.eshop.repository.impl;

import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl extends RepositoryImpl<Ticket> implements TicketRepository {


    @Override
    public List<Ticket> read(String ticketName) {

        List<Ticket> returnTickets = new ArrayList<>();

        return returnTickets;
    }

}
