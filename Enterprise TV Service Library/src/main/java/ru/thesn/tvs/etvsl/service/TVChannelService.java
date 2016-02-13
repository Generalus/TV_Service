package ru.thesn.tvs.etvsl.service;

import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.TVChannel;

import java.util.List;

public interface TVChannelService {
    TVChannel create(TVChannel channel);
    TVChannel delete(long id) throws EntityNotFoundException;
    List<TVChannel> findAll();
    TVChannel update(TVChannel channel) throws EntityNotFoundException;
    TVChannel findById(long id);
}
