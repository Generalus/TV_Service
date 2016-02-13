package ru.thesn.tvs.cims.service;

import ru.thesn.tvs.cims.exception.EntityNotFoundException;
import ru.thesn.tvs.cims.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product delete(long id) throws EntityNotFoundException;
    List<Product> findAll();
    Product update(Product product) throws EntityNotFoundException;
    Product findById(long id);
}