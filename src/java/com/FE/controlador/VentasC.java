/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.controlador;

import com.FE.controlador.util.CRUD;
import com.FE.controlador.util.Msg;
import com.FE.modelo.Personas;
import com.FE.modelo.Ventas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class VentasC extends Ventas{
    private List<Ventas> ventas;
            
    @PostConstruct
    public void init(){
        ventas=new ArrayList<>();
        String sql = "SELECT * FROM Ventas";
        
        ResultSet r = CRUD.select(sql);
        try {
            while (r.next()) {
                Ventas p=new Ventas(r.getInt(1), r.getString(2), r.getInt(3));
               
                ventas.add(p);
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
    
    public void consultaPrimaria() {
         String sql = "SELECT * FROM Ventas WHERE numerof='" + getNumeroF()+ "'";
        System.err.println(sql);
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setNumeroF(r.getInt(1));
                setCb(r.getString(2));
                setCantidad(r.getInt(3));
            }else{
                Msg.ad("La factura no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }

    public void eliminar() {
        String sql = "DELETE FROM Ventas WHERE numerof='"+getNumeroF()+"' and cb='"+getCb()+"'";
        String m = "Se ha eliminado el Venta";
        Msg.msgDB(m, CRUD.DML(sql, m));
    }

     public void insertar(){
        CRUD.insert(creaO());
    }
    public void actualizar(){
        CRUD.update(creaO(),"numerof='"+getNumeroF()+"' and cb='"+getCb()+"'");
    }
    private ArrayList<Object> creaO (){
        ArrayList<Object> o =new ArrayList<>();
        o.add(this);
        return o;
    }

    public List<Ventas> getVentas() {
        return ventas;
    }

    public void setVentas(List<Ventas> ventas) {
        this.ventas = ventas;
    }
}
