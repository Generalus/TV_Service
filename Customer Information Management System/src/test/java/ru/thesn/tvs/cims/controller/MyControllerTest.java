package ru.thesn.tvs.cims.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.thesn.tvs.cims.exception.EntityNotFoundException;
import ru.thesn.tvs.cims.exception.IllegalAccessException;
import ru.thesn.tvs.cims.model.Account;
import ru.thesn.tvs.cims.model.Product;
import ru.thesn.tvs.cims.service.AccountService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {
    @Mock
    private AccountService accountService;

    @InjectMocks
    private MyController controller;

    Account account = new Account();

    @Before
    public void init(){

        account.setLogin("admin");
        account.setPasswordHash("dg12a");

        Product p1 = new Product();
        p1.setOfferingID(1001L);
        p1.setId(1L);
        p1.setAccount(account);

        Product p2 = new Product();
        p2.setOfferingID(1002L);
        p2.setId(2L);
        p2.setAccount(account);

        Product p3 = new Product();
        p3.setOfferingID(1003L);
        p3.setId(3L);
        p3.setAccount(account);

        Set<Product> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        account.setProducts(set);

        when(accountService.findById("admin")).thenReturn(account);
        when(accountService.findById("ghost")).thenReturn(null);
    }

    @Test(expected = IllegalAccessException.class)
    public void testCheckAccount_shouldThrowIllegalAccessExceptionIfPasswordIsNotCorrect() throws Exception {
        controller.checkAccount("admin", "wrong_hash");
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCheckAccount_shouldThrowEntityNotFoundExceptionIfLoginIsNotReal() throws Exception {
        controller.checkAccount("ghost", "hash");
    }

    @Test
    public void testCheckAccount_shouldFindAccountWithCorrectLoginAndPassword() throws Exception {
        assertEquals(account, controller.checkAccount("admin", "dg12a"));
    }
}
