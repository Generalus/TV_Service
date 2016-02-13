package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.Lineup;

import java.util.List;

public interface LineupService {
    Lineup create(Lineup lineup);
    Lineup delete(long id) throws EntityNotFoundException;
    List<Lineup> findAll();
    Lineup update(Lineup lineup) throws EntityNotFoundException;
    Lineup findById(long id);
}
