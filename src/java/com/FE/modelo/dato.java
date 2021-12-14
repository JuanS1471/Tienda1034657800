/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.modelo;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named
@ViewScoped
public class dato implements Serializable {

    private List<Product> products;

    @Inject
    private Producto service;

    @PostConstruct
    public void init() {
        products = service.getProducts(100);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(Producto service) {
        this.service = service;
    }
}
