package ru.thesn.tvs.etvsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thesn.tvs.etvsl.model.TVChannel;

public interface TVChannelRepository extends JpaRepository<TVChannel, Long> {
}
