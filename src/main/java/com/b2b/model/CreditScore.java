package com.b2b.model;

import javax.persistence.*;

@Entity
public class CreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double score;
    private double recommendedLimit;

    @OneToOne
    private Customer customer;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRecommendedLimit() {
        return recommendedLimit;
    }

    public void setRecommendedLimit(double recommendedLimit) {
        this.recommendedLimit = recommendedLimit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

