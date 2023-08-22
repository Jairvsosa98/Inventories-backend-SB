package jv.dev.Inventories.repository;

import jv.dev.Inventories.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
