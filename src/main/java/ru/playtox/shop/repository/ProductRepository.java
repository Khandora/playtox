package ru.playtox.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.playtox.shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
