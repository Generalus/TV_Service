package ru.thesn.tvs.cims.service;

import ru.thesn.tvs.cims.exception.EntityNotFoundException;
import ru.thesn.tvs.cims.model.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account delete(String id) throws EntityNotFoundException;
    List<Account> findAll();
    Account update(Account account) throws EntityNotFoundException;
    Account findById(String id);
}
