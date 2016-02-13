package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.TVPackage;

import java.util.List;

public interface TVPackageService {
    TVPackage create(TVPackage tvPackage);
    TVPackage delete(long id) throws EntityNotFoundException;
    List<TVPackage> findAll();
    TVPackage update(TVPackage tvPackage) throws EntityNotFoundException;
    TVPackage findById(long id);
}
