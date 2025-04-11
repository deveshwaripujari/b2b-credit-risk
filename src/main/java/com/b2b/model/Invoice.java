package com.b2b.model;

import javax.persistence.*;
import javax.persistence.CascadeType;


@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String invoiceNumber;
    private double amount;
    private String currency;

    // Many invoices can belong to one customer
    @ManyToOne(cascade = CascadeType.ALL) // creates foreign key to customer
    private Customer customer;

    // --- Getters and Setters ---

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getInvoiceNumber(){
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber){
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
