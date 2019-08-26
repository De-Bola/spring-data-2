package com.sda.springdata2.chapter2.repository;

import com.sda.springdata2.chapter2.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
