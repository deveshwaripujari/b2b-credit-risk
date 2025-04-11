package com.b2b.dao;

import com.b2b.model.Customer;
import com.b2b.model.Invoice;
import org.w3c.dom.*;
import org.json.JSONObject;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InvoiceImporter {

    public static Invoice parseXMLInvoice(String filePath) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File(filePath));

            doc.getDocumentElement().normalize();

            String invoiceNum = doc.getElementsByTagName("invoiceNumber").item(0).getTextContent();
            double amount = Double.parseDouble(doc.getElementsByTagName("amount").item(0).getTextContent());
            String currency = doc.getElementsByTagName("currency").item(0).getTextContent();

            Element customerNode = (Element) doc.getElementsByTagName("customer").item(0);
            String name = customerNode.getElementsByTagName("name").item(0).getTextContent();
            String industry = customerNode.getElementsByTagName("industry").item(0).getTextContent();
            double revenue = Double.parseDouble(customerNode.getElementsByTagName("revenue").item(0).getTextContent());

            Customer customer = new Customer();
            customer.setName(name);
            customer.setIndustry(industry);
            customer.setRevenue(revenue);

            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(invoiceNum);
            invoice.setAmount(amount);
            invoice.setCurrency(currency);
            invoice.setCustomer(customer);

            return invoice;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Invoice parseJSONInvoice(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);

            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(json.getString("invoiceNumber"));
            invoice.setAmount(json.getDouble("amount"));
            invoice.setCurrency(json.getString("currency"));

            JSONObject customerJson = json.getJSONObject("customer");
            Customer customer = new Customer();
            customer.setName(customerJson.getString("name"));
            customer.setIndustry(customerJson.getString("industry"));
            customer.setRevenue(customerJson.getDouble("revenue"));

            invoice.setCustomer(customer);
            return invoice;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
