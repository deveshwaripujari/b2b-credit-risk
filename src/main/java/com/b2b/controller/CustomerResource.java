package com.b2b.controller;

import com.b2b.dao.CustomerDAO;
import com.b2b.model.Customer;
import com.b2b.model.CreditScore;
import com.b2b.service.CreditScorerService;

import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
public class CustomerResource {

    @GET
    @Path("/{id}/limit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreditLimit(@PathParam("id") int customerId) {
        // 1. Fetch the customer from the DB
        Customer customer = new CustomerDAO().findById(customerId);

        if (customer == null) {
            return Response.status(404).entity("Customer not found").build();
        }

        // 2. Compute score using service logic
        CreditScore score = new CreditScorerService().computeCreditScore(customer);

        // 3. Return as JSON
        return Response.ok(score).build();
    }
}
