package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.TVChannel;

import java.util.List;

public interface TVChannelService {
    TVChannel create(TVChannel channel);
    TVChannel delete(long id) throws EntityNotFound;
    List<TVChannel> findAll();
    TVChannel update(TVChannel channel) throws EntityNotFound;
    TVChannel findById(long id);
}
