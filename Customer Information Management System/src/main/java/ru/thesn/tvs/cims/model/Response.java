package ru.thesn.tvs.cims.model;

import java.util.List;

public class Response {
    private String code;
    private String errorMessage;
    private List<Product> products;
    private Long areaID;

    public Response(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Response(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getAreaID() {
        return areaID;
    }

    public void setAreaID(Long areaID) {
        this.areaID = areaID;
    }

}
