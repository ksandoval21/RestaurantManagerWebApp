package com.ksandoval21.RestaurantManagerWebApp;


import javax.persistence.*;

@Entity
@Table(name = "Prices")
public class Prices {
    @Id
    @Column(nullable = false, unique = true, length = 4)
    private int pin;

    @Column(nullable = false, length = 11)
    private int guest;

    @Column( nullable = false, length = 11)
    private int child;

    @Column (nullable = false, length = 11)
    private int drink;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getDrink() {
        return drink;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }
}
