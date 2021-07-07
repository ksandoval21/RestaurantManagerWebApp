package com.ksandoval21.RestaurantManagerWebApp;


import javax.persistence.*;

@Entity
@Table(name = "Prices")
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private double guest;

    @Column( nullable = false, length = 11)
    private double child;

    @Column (nullable = false, length = 11)
    private double drink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGuest() {
        return guest;
    }

    public void setGuest(double guest) {
        this.guest = guest;
    }

    public double getChild() {
        return child;
    }

    public void setChild(double child) {
        this.child = child;
    }

    public double getDrink() {
        return drink;
    }

    public void setDrink(double drink) {
        this.drink = drink;
    }
}
