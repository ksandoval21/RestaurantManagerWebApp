package com.ksandoval21.RestaurantManagerWebApp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@EnableJpaRepositories
interface PricesRepository extends JpaRepository<Prices, Integer>{
    public List<Prices> findByPin(int pin);
}
