package com.b2b.controller;

import com.b2b.dao.InvoiceDAO;
import com.b2b.model.Invoice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("invoice")  // 🔥 no leading slash for web.xml config
public class InvoiceResource {

    @POST
    @Path("/upload")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadInvoice(Invoice invoice) {
        try {
            if (invoice == null || invoice.getCustomer() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Invoice or Customer data missing\"}")
                        .build();
            }

            new InvoiceDAO().saveInvoice(invoice);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Invoice saved successfully\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvoiceById(@PathParam("id") int id) {
        try {
            Invoice invoice = new InvoiceDAO().getInvoiceById(id);
            if (invoice == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"Invoice not found\"}")
                        .build();
            }
            return Response.ok(invoice).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
