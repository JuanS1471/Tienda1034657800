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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class FacturasC extends Facturas{
    private List<Facturas> facturas;
    
    public void init(){
       llenarTabla();
    }
    public void llenarTabla(){
        facturas =new ArrayList<>();
        
        String sql = "SELECT * FROM Facturas";
        
        ResultSet r = CRUD.select(sql);
        try {
            while (r.next()) {
                Facturas p = new Facturas(r.getInt(1), r.getInt(2), r.getInt(3) r.getInt(4));
                facturas.add(p);
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
    public void consultaPrimaria() {
        String sql = "SELECT * FROM Facturas WHERE Personasdocumento='" + getPersonasdocumento()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setEmpleadodocumento(r.getInt(2));
                setFecha(r.getInt(3));
                setNumero(r.getInt(3));
                setPersonasdocumento(r.getInt(3));
            }else{
                Msg.ad("La factura no se encuentra registrada.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public void consultaEmpleadoD() {
        String sql = "SELECT * FROM Facturas WHERE empleadodocumento='" + getEmpleadodocumento()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setEmpleadodocumento(r.getInt(2));
                setFecha(r.getInt(3));
                setNumero(r.getInt(3));
                setPersonasdocumento(r.getInt(3));
            }else{
                Msg.ad("La factura no se encuentra registrada.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public List<String> listaDocumento(String dato){
        List<String> listaDocumento=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT personasdocumento FROM Facturas WHERE personasdocumento like '"+dato+"%'");
        try {
            while(r.next()){
                listaDocumento.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDocumento;
    }
    public List<String> listaEdocumento(String dato){
        List<String> listaEdocumento=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT empleadodocumento FROM Facturas WHERE empleadodocumento like '"+dato+"%'");
        try {
            while(r.next()){
                listaEdocumento.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEdocumento;
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
