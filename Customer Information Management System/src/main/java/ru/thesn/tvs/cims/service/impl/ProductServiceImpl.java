package ru.thesn.tvs.cims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.cims.exception.EntityNotFoundException;
import ru.thesn.tvs.cims.model.Product;
import ru.thesn.tvs.cims.repository.ProductRepository;
import ru.thesn.tvs.cims.service.ProductService;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public Product delete(long id) throws EntityNotFoundException {
        Product deletedProduct = repository.findOne(id);

        if (deletedProduct == null)
            throw new EntityNotFoundException();

        repository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        for(Product product: repository.findAll())
            list.add(product);
        return list;
    }

    @Override
    public Product update(Product product) throws EntityNotFoundException {
        Product cur = repository.findOne(product.getOfferingID());
        if (cur == null)
            throw new EntityNotFoundException();
        cur.setAccount(product.getAccount());

        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return repository.findOne(id);
    }
}
