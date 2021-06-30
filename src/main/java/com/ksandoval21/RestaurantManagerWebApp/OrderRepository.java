package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends CrudRepository<Orders, Long>{
    @Query("SELECT g FROM Orders g WHERE g.id = ?1")
    public Orders findById(String id);
}
