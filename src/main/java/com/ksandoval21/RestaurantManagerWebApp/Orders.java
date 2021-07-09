package com.ksandoval21.RestaurantManagerWebApp;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guest",nullable = false, length = 100)
    private int guest;

    @Column(name= "kids", nullable = false, length = 100)
    private int kids;

    @Column(name = "tableNumber", nullable = false, length = 100)
    private int tableNumber;

    @Column(name = "drinks", nullable = false, length = 100)
    private int drinks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setDrinks(int drinks) {
        this.drinks = drinks;
    }

    public void setUserId(Long userId) {

    }

    public double getGuestPrice(Prices prices) {
        return getGuest() * prices.getGuest();
    }
}
