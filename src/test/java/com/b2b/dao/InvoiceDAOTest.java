package com.b2b.dao;

import com.b2b.model.Invoice;
import org.junit.Test;
import static org.junit.Assert.*;


public class InvoiceDAOTest {

    @Test
    public void testSaveAndGetInvoice() {
        Invoice invoice = InvoiceImporter.parseJSONInvoice("invoice_sample.json");
        assertNotNull(invoice);

        InvoiceDAO dao = new InvoiceDAO();
        dao.saveInvoice(invoice);

        Invoice fetched = dao.getInvoiceById(invoice.getId());
        assertNotNull(fetched);
        assertEquals(invoice.getInvoiceNumber(), fetched.getInvoiceNumber());
    }
}
