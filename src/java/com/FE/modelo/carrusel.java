/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class carrusel implements Serializable {

    private List<Product> products;

    private List<Responsive> responsiveOptions;

    @Inject
    private Producto service;

    @PostConstruct
    public void init() {
        products = service.getProducts(9);
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new Responsive("1024px", 3, 3));
        responsiveOptions.add(new Responsive("768px", 2, 2));
        responsiveOptions.add(new Responsive("560px", 1, 1));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(Producto service) {
        this.service = service;
    }

    public List<Responsive> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<Responsive> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }
}