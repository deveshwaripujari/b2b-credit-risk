package com.b2b.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(unique = true)
    private String name;
    private String industry;
    private double revenue;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getIndustry(){
        return industry;
    }

    public void setIndustry(String industry){
        this.industry= industry;
    }

    public double getRevenue(){
        return revenue;
    }

    public void setRevenue(double revenue){
        this.revenue=revenue;
    }

}
