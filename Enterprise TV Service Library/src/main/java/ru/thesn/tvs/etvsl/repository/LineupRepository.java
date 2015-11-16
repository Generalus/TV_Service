package ru.thesn.tvs.etvsl.repository;


import org.springframework.data.repository.CrudRepository;
import ru.thesn.tvs.etvsl.model.Lineup;

public interface LineupRepository extends CrudRepository<Lineup, Long> {
}
