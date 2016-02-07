package ru.thesn.tvs.cas.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class CIMResponse implements Response {
    private String code;
    private String errorMessage;
    private Long areaID;

    @JsonDeserialize(as=ArrayList.class)
    private List<Product> products;

    public CIMResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public CIMResponse(String code) {
        this.code = code;
    }

    public CIMResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getAreaID() {
        return areaID;
    }

    public void setAreaID(Long areaID) {
        this.areaID = areaID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String[] getProductsStringArray() {
        String[] result = new String[products.size()];
        for(int i = 0; i < products.size(); i++)
            result[i] = products.get(i).getOfferingID().toString();
        return result;
    }
}
