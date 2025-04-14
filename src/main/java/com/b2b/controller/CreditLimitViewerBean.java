package com.b2b.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

@ManagedBean(name = "creditLimitViewerBean")
@RequestScoped
public class CreditLimitViewerBean implements Serializable {

    private int customerId;
    private String limitJson;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLimitJson() {
        return limitJson;
    }

    public void setLimitJson(String limitJson) {
        this.limitJson = limitJson;
    }

    public String fetchLimit() {
        try {
            URL url = new URL("http://localhost:8080/b2b/api/customer/" + customerId + "/limit");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line).append("\n");
                }
                limitJson = result.toString();
            }
        } catch (Exception e) {
            limitJson = "Error fetching credit limit: " + e.getMessage();
            e.printStackTrace();
        }
        return null;
    }
}
