package ru.thesn.tvs.etvsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.repository.TVPackageRepository;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TVPackageServiceImpl implements TVPackageService {

    @Autowired
    TVPackageRepository repository;

    @Override
    @Transactional
    public TVPackage create(TVPackage tvPackage) {
        return repository.save(tvPackage);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFound.class)
    public TVPackage delete(long id) throws EntityNotFound {
        TVPackage deletedTVPackage = repository.findOne(id);

        if (deletedTVPackage == null)
            throw new EntityNotFound();

        repository.delete(deletedTVPackage);
        return deletedTVPackage;
    }

    @Override
    @Transactional
    public List<TVPackage> findAll() {
        List<TVPackage> list = new ArrayList<>();
        for(TVPackage tvPackage: repository.findAll())
            list.add(tvPackage);
        return list;
    }

    @Override
    @Transactional
    public TVPackage update(TVPackage tvPackage) throws EntityNotFound {
        return null;
    }

    @Override
    @Transactional
    public TVPackage findById(long id) {
        return repository.findOne(id);
    }
}
