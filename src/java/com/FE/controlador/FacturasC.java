/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.controlador;

import com.FE.controlador.util.CRUD;
import com.FE.controlador.util.Msg;
import com.FE.modelo.Facturas;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class FacturasC extends Facturas{
    private List<Facturas> facturas;
    public void consultaPrimaria() {
        String sql = "SELECT * FROM Facturas WHERE numero='" + getNumero() + "'";
        System.err.println(sql);
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setNumero(r.getInt(1));
                Date f=r.getTimestamp(2);
                
                LocalDateTime l=f.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                setFecha(l);
                setPersonasdocumento(r.getInt(3));
                setEmpleadodocumento(r.getInt(4));
               
                
            }else{
              
                Msg.ad("La factura no se encuentra registrado.");
            }
        } catch (SQLException ex) {
           
            Msg.error(ex.getMessage());
        }
    }

    public void eliminar() {
        String sql = "DELETE FROM Facturas WHERE numero='"+getNumero()+"'";
        String m = "Se ha eliminado el Factura";
        Msg.msgDB(m, CRUD.DML(sql, m));
    }

     public void insertar(){
        CRUD.insert(creaO());
    }
    public void actualizar(){
        CRUD.update(creaO(),"numero='"+getNumero()+"'");
    }
    private ArrayList<Object> creaO (){
        ArrayList<Object> o =new ArrayList<>();
        o.add(this);
        return o;
    }

    public List<Facturas> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Facturas> facturas) {
        this.facturas = facturas;
    }
}
