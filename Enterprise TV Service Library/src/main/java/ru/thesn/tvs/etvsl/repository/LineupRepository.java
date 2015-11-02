package ru.thesn.tvs.etvsl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.thesn.tvs.etvsl.model.Lineup;

public interface LineupRepository extends JpaRepository<Lineup, Long> {
}
