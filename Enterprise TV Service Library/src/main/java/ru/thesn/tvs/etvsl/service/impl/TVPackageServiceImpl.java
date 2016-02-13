package ru.thesn.tvs.etvsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.repository.TVPackageRepository;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TVPackageServiceImpl implements TVPackageService {

    @Autowired
    TVPackageRepository repository;

    @Override
    public TVPackage create(TVPackage tvPackage) {
        return repository.save(tvPackage);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public TVPackage delete(long id) throws EntityNotFoundException {
        TVPackage deletedTVPackage = repository.findOne(id);

        if (deletedTVPackage == null)
            throw new EntityNotFoundException();

        repository.delete(deletedTVPackage);
        return deletedTVPackage;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TVPackage> findAll() {
        List<TVPackage> list = new ArrayList<>();
        for(TVPackage tvPackage: repository.findAll())
            list.add(tvPackage);
        return list;
    }

    @Override
    public TVPackage update(TVPackage tvPackage) throws EntityNotFoundException {
        TVPackage cur = repository.findOne(tvPackage.getOfferingID());
        if (cur == null)
            throw new EntityNotFoundException();
        cur.setName(tvPackage.getName());
        cur.setChannels(tvPackage.getChannels());
        cur.setLineups(tvPackage.getLineups());
        cur.setStatus(tvPackage.getStatus());
        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public TVPackage findById(long id) {
        return repository.findOne(id);
    }
}
