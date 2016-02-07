package ru.thesn.tvs.etvsl.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.repository.TVChannelRepository;
import ru.thesn.tvs.etvsl.service.TVChannelService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TVChannelServiceImpl implements TVChannelService {

    @Autowired
    TVChannelRepository repository;

    @Override
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
    @Transactional(readOnly = true)
    public List<TVChannel> findAll() {
        List<TVChannel> list = new ArrayList<>();
        for(TVChannel tvChannel: repository.findAll())
            list.add(tvChannel);
        return list;
    }

    @Override
    public TVChannel update(TVChannel channel) throws EntityNotFound {
        TVChannel cur = repository.findOne(channel.getSourceID());
        if (cur == null)
            throw new EntityNotFound();
        cur.setContentID(channel.getContentID());
        cur.setLineups(channel.getLineups());
        cur.setName(channel.getName());
        cur.setPackages(channel.getPackages());
        cur.setStatus(channel.getStatus());
        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public TVChannel findById(long id) {
        return repository.findOne(id);
    }
}
