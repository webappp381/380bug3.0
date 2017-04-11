/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ouhk.comps380f.dao;

import edu.ouhk.comps380f.model.Ticket;
import java.util.List;

public interface TicketRepository {
    public void create(Ticket ticket);
    public List<Ticket> findAll();
    //public Ticket findByUsername(String username);
    public void deleteByTicketId(String ticketId);
}
