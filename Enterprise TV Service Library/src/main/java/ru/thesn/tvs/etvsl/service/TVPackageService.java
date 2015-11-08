package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.TVPackage;

import java.util.List;

public interface TVPackageService {
    TVPackage create(TVPackage tvPackage);
    TVPackage delete(long id) throws EntityNotFound;
    List<TVPackage> findAll();
    TVPackage update(TVPackage tvPackage) throws EntityNotFound;
    TVPackage findById(long id);
}
