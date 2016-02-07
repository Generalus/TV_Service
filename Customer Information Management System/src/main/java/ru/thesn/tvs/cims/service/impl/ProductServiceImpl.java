package ru.thesn.tvs.cims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.cims.exception.EntityNotFound;
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
    @Transactional(rollbackFor = EntityNotFound.class)
    public Product delete(long id) throws EntityNotFound {
        Product deletedProduct = repository.findOne(id);

        if (deletedProduct == null)
            throw new EntityNotFound();

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
    public Product update(Product product) throws EntityNotFound {
        Product cur = repository.findOne(product.getOfferingID());
        if (cur == null)
            throw new EntityNotFound();
        cur.setAccount(product.getAccount());

        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(long id) {
        return repository.findOne(id);
    }
}
