package ru.thesn.tvs.etvsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.repository.LineupRepository;
import ru.thesn.tvs.etvsl.service.LineupService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LineupServiceImpl implements LineupService {

    @Autowired
    LineupRepository repository;

    @Override
    public Lineup create(Lineup lineup) {
        return repository.save(lineup);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public Lineup delete(long id) throws EntityNotFoundException {
        Lineup deletedLineup = repository.findOne(id);

        if (deletedLineup == null)
            throw new EntityNotFoundException();

        repository.delete(deletedLineup);
        return deletedLineup;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lineup> findAll() {
        List<Lineup> list = new ArrayList<>();
        for(Lineup lineup: repository.findAll())
            list.add(lineup);
        return list;
    }

    @Override
    public Lineup update(Lineup lineup) throws EntityNotFoundException {
        Lineup cur = repository.findOne(lineup.getAreaID());
        if (cur == null)
            throw new EntityNotFoundException();
        cur.setName(lineup.getName());
        cur.setPackages(lineup.getPackages());
        cur.setChannels(lineup.getChannels());
        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public Lineup findById(long id) {
        return repository.findOne(id);
    }
}
