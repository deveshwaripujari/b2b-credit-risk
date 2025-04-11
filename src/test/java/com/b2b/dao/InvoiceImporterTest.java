package com.b2b.dao;

import com.b2b.model.Invoice;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvoiceImporterTest {

    @Test
    public void testParseXMLInvoice() {
        Invoice invoice = InvoiceImporter.parseXMLInvoice("invoice_sample.xml");
        assertNotNull(invoice);
        assertEquals("INV-2025-002", invoice.getInvoiceNumber());
        assertEquals("USD", invoice.getCurrency());
        assertEquals("OpenAI Corp", invoice.getCustomer().getName());
    }

    @Test
    public void testParseJSONInvoice() {
        Invoice invoice = InvoiceImporter.parseJSONInvoice("invoice_sample.json");
        assertNotNull(invoice);
        assertEquals("INV-2025-003", invoice.getInvoiceNumber());
        assertEquals(22000.0, invoice.getAmount(), 0.01); 
        assertEquals("Amazon Inc", invoice.getCustomer().getName());
    }
}
