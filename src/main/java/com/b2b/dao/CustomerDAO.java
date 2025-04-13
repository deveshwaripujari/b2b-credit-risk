package com.b2b.dao;

import com.b2b.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("b2bUnit");

    public Customer findById(int id) {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }
}
