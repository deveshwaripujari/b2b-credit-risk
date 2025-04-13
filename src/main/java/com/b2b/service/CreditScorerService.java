package com.b2b.service;

import com.b2b.model.Customer;
import com.b2b.model.CreditScore;

public class CreditScorerService {

    public CreditScore computeCreditScore(Customer customer) {
        CreditScore score = new CreditScore();

        double baseScore = 300;

        if ("Manufacturing".equalsIgnoreCase(customer.getIndustry())) baseScore += 200;
        else if ("Retail".equalsIgnoreCase(customer.getIndustry())) baseScore += 100;

        if (customer.getRevenue() > 1_000_000) baseScore += 100;
        else if (customer.getRevenue() > 500_000) baseScore += 50;

        double limit = baseScore * 10;

        score.setCustomer(customer);
        score.setScore(baseScore);
        score.setRecommendedLimit(limit);
        return score;
    }
}
