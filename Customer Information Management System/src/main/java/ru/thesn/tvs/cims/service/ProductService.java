package ru.thesn.tvs.cims.service;

import ru.thesn.tvs.cims.exception.EntityNotFound;
import ru.thesn.tvs.cims.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product delete(long id) throws EntityNotFound;
    List<Product> findAll();
    Product update(Product product) throws EntityNotFound;
    Product findById(long id);
}