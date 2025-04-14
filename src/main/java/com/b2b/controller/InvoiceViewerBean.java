package com.b2b.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

@ManagedBean(name = "invoiceViewerBean")  
@RequestScoped
public class InvoiceViewerBean implements Serializable {

    private int invoiceId;
    private String invoiceJson;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceJson() {
        return invoiceJson;
    }

    public void setInvoiceJson(String invoiceJson) {
        this.invoiceJson = invoiceJson;
    }

    public String fetchInvoice() {
        try {
            URL url = new URL("http://localhost:8080/b2b/api/invoice/" + invoiceId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line).append("\n");
                }
                invoiceJson = result.toString();
            }
        } catch (Exception e) {
            invoiceJson = "Error fetching invoice: " + e.getMessage();
            e.printStackTrace();
        }
        return null;
    }
}
