package ru.thesn.tvs.cims.service;

import ru.thesn.tvs.cims.exception.EntityNotFound;
import ru.thesn.tvs.cims.model.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account delete(String id) throws EntityNotFound;
    List<Account> findAll();
    Account update(Account account) throws EntityNotFound;
    Account findById(String id);
}
