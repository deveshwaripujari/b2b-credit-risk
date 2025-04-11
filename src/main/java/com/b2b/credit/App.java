package com.b2b.credit;

import com.b2b.dao.InvoiceDAO;
import com.b2b.dao.InvoiceImporter;
import com.b2b.model.Customer;
import com.b2b.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        //Initialize Hibernate SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        //Step 1: Create and save a Customer
        Customer customer = new Customer();
        customer.setName("Tesla Inc.");
        customer.setIndustry("Automotive");
        customer.setRevenue(500.0);  // 500 Million

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        session.close();

        System.out.println("✅ Customer saved with ID: " + customer.getId());

        //Step 2: Create an invoice linked to this customer
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV-2025-001");
        invoice.setAmount(12000.0);
        invoice.setCurrency("USD");
        invoice.setCustomer(customer);  // FK relation

        //Step 3: Save invoice using DAO
        InvoiceDAO dao = new InvoiceDAO();
        dao.saveInvoice(invoice);

        //Step 4: Retrieve invoice to verify
        Invoice fetched = dao.getInvoiceById(invoice.getId());
        System.out.println("Fetched Invoice → " + fetched.getInvoiceNumber() + " | Customer: " + fetched.getCustomer().getName());

        //Clean shutdown
        sessionFactory.close();

        // From XML
        Invoice xmlInvoice = InvoiceImporter.parseXMLInvoice("invoice_sample.xml");
        new InvoiceDAO().saveInvoice(xmlInvoice);

        // From JSON
        Invoice jsonInvoice = InvoiceImporter.parseJSONInvoice("invoice_sample.json");
        new InvoiceDAO().saveInvoice(jsonInvoice);

    }
}
