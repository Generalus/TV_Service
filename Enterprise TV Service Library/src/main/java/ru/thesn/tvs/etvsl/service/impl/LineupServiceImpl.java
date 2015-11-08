package ru.thesn.tvs.etvsl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.etvsl.exception.EntityNotFound;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.repository.LineupRepository;
import ru.thesn.tvs.etvsl.service.LineupService;

import javax.annotation.Resource;
import java.util.List;

public class LineupServiceImpl implements LineupService {

    @Resource
    private LineupRepository repository;

    @Override
    @Transactional
    public Lineup create(Lineup lineup) {
        return repository.save(lineup);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFound.class)
    public Lineup delete(long id) throws EntityNotFound {
        Lineup deletedLineup = repository.findOne(id);

        if (deletedLineup == null)
            throw new EntityNotFound();

        repository.delete(deletedLineup);
        return deletedLineup;
    }

    @Override
    @Transactional
    public List<Lineup> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Lineup update(Lineup lineup) throws EntityNotFound {
        return null;
    }

    @Override
    @Transactional
    public Lineup findById(long id) {
        return repository.findOne(id);
    }
}
