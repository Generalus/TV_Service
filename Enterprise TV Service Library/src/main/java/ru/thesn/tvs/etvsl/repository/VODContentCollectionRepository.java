package ru.thesn.tvs.etvsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thesn.tvs.etvsl.model.VODContentCollection;

public interface VODContentCollectionRepository extends JpaRepository<VODContentCollection, Long> {
}
