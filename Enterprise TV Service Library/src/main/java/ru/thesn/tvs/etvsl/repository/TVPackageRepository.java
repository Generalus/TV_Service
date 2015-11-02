package ru.thesn.tvs.etvsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thesn.tvs.etvsl.model.TVPackage;

public interface TVPackageRepository extends JpaRepository<TVPackage, Long> {
}
