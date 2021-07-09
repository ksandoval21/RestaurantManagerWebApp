package com.ksandoval21.RestaurantManagerWebApp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
interface PricesRepository extends JpaRepository<Prices, Long>{
    public Optional<Prices> findById(Long id);
}
