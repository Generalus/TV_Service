package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.Lineup;

import java.util.List;

public interface LineupService {
    Lineup create(Lineup lineup);
    Lineup delete(long id) throws EntityNotFound;
    List<Lineup> findAll();
    Lineup update(Lineup lineup) throws EntityNotFound;
    Lineup findById(long id);
}
