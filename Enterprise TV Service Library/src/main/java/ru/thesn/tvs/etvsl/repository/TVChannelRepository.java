package ru.thesn.tvs.etvsl.repository;

import org.springframework.data.repository.CrudRepository;
import ru.thesn.tvs.etvsl.model.TVChannel;

public interface TVChannelRepository extends CrudRepository<TVChannel, Long> {
}
