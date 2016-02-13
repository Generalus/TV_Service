package ru.thesn.tvs.cims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thesn.tvs.cims.exception.EntityNotFoundException;
import ru.thesn.tvs.cims.model.Account;
import ru.thesn.tvs.cims.repository.AccountRepository;
import ru.thesn.tvs.cims.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public Account delete(String id) throws EntityNotFoundException {
        Account deletedAccount = repository.findOne(id);

        if (deletedAccount == null)
            throw new EntityNotFoundException();

        repository.delete(deletedAccount);
        return deletedAccount;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        for(Account account: repository.findAll())
            list.add(account);
        return list;
    }

    @Override
    public Account update(Account account) throws EntityNotFoundException {
        Account cur = repository.findOne(account.getLogin());
        if (cur == null)
            throw new EntityNotFoundException();
        cur.setFirstName(account.getFirstName());
        cur.setLastName(account.getLastName());
        cur.setAreaID(account.getAreaID());
        cur.setPasswordHash(account.getPasswordHash());
        cur.setProducts(account.getProducts());

        return cur;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(String id) {
        return repository.findOne(id);
    }

}
