package ru.thesn.tvs.cims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.thesn.tvs.cims.exception.EntityNotFound;
import ru.thesn.tvs.cims.exception.IllegalAccessException;
import ru.thesn.tvs.cims.model.Account;
import ru.thesn.tvs.cims.model.Product;
import ru.thesn.tvs.cims.model.Response;
import ru.thesn.tvs.cims.service.AccountService;
import ru.thesn.tvs.cims.service.ProductService;

import java.util.*;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "cim", method = RequestMethod.GET)
    public @ResponseBody Response getResponseInJSON(@RequestParam String login, @RequestParam String passwordHash){
        try {
            Response response = new Response("OK");
            Account account = checkAccount(login, passwordHash);
            List<Product> products = new ArrayList<>();
            products.addAll(account.getProducts());
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product c1, Product c2) {
                    return (int)(c1.getOfferingID() - c2.getOfferingID()); // сортировка в порядке возрастания ID
                }
            });
            response.setProducts(products);
            response.setAreaID(account.getAreaID());
            return response;
        }
        catch (EntityNotFound e){
            return new Response("WARN", "Уведомление: " + e.getMessage());
        }
        catch (IllegalAccessException e){
            return new Response("ERR", "Ошибка доступа: " + e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response("CRITICAL_ERROR", "Критическая ошибка системы: " + e.toString());
        }
    }

    public Account checkAccount(String login, String passHash) throws Exception{
        Account account = accountService.findById(login);
        if (account == null)
            throw new EntityNotFound("Заданный аккаунт не существует!");
        if (!passHash.equals(account.getPasswordHash()))
            throw new IllegalAccessException("Пароль неверен!");
        return account;
    }


}
