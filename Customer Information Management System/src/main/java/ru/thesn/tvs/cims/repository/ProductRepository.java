package ru.thesn.tvs.cims.repository;

import org.springframework.data.repository.CrudRepository;
import ru.thesn.tvs.cims.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
