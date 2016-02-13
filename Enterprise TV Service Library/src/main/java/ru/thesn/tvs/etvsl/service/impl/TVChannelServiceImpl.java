package ru.thesn.tvs.etvsl.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.repository.TVChannelRepository;
import ru.thesn.tvs.etvsl.service.TVChannelService;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public TVChannel delete(long id) throws EntityNotFoundException {
        TVChannel deletedTVChannel = repository.findOne(id);

        if (deletedTVChannel == null)
            throw new EntityNotFoundException();

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
    public TVChannel update(TVChannel channel) throws EntityNotFoundException {
        TVChannel cur = repository.findOne(channel.getSourceID());
        if (cur == null)
            throw new EntityNotFoundException();
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
