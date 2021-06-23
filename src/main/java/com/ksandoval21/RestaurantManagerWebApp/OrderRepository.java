package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Orders, Long>{
    public List<Orders> findByTableNumber(int table);
}
