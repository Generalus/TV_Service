package ru.thesn.tvs.etvsl.service.impl;


import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.repository.TVChannelRepository;
import ru.thesn.tvs.etvsl.service.TVChannelService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class TVChannelServiceImpl implements TVChannelService {

    @Resource
    private TVChannelRepository repository;

    @Override
    @Transactional
    public TVChannel create(TVChannel channel) {
        return repository.save(channel);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFound.class)
    public TVChannel delete(long id) throws EntityNotFound {
        TVChannel deletedTVChannel = repository.findOne(id);

        if (deletedTVChannel == null)
            throw new EntityNotFound();

        repository.delete(deletedTVChannel);
        return deletedTVChannel;
    }

    @Override
    @Transactional
    public List<TVChannel> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public TVChannel update(TVChannel channel) throws EntityNotFound {
        return null;
    }

    @Override
    @Transactional
    public TVChannel findById(long id) {
        return repository.findOne(id);
    }
}
