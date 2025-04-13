package com.b2b.dao;

import com.b2b.model.Invoice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class InvoiceDAO {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("❌ Failed to create SessionFactory: " + ex);
        }
    }

    public void saveInvoice(Invoice invoice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(invoice);
            transaction.commit();
            System.out.println("✅ Invoice saved to DB successfully");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Invoice getInvoiceById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Invoice.class, id);
        }
    }

    public static void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
