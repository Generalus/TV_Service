package ru.thesn.tvs.cims.repository;

import org.springframework.data.repository.CrudRepository;
import ru.thesn.tvs.cims.model.Account;

public interface AccountRepository extends CrudRepository<Account, String> {
}