/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.controlador;

import com.FE.controlador.util.CRUD;
import com.FE.controlador.util.Msg;
import com.FE.modelo.Precios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jhon Mauricio Moreno
 */
@Named
@RequestScoped
public class PreciosC extends Precios {

   private List<Precios> precios;

        public void init(){
       llenarTabla();
    }
    public void llenarTabla(){
        precios = new ArrayList<>();
        
        String sql = "SELECT * FROM Personas";
        
        ResultSet r = CRUD.select(sql);
        try {
            while (r.next()) {
                Precios p = new Precios( r.getString(1), r.getInt(2), r.getInt(3), r.getDouble(4));
                precios.add(p);
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
    
    public void consultaPrimaria() {
        String sql = "SELECT * FROM Precios WHERE cb='" + getCb()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setCb(r.getString(2));
                setFechaIni(r.getString(sql));
            }else{
                Msg.ad("El usuario no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public void consultaPnombre() {
        String sql = "SELECT * FROM Personas WHERE pnombre='" + getPnombre()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setDocumento(r.getInt(1));
                setPnombre(r.getString(2));
                setSnombre(r.getString(3));
                setPapellido(r.getString(4));
                setSapellido(r.getString(5));
                setEmail(r.getString(6));
            }else{
                Msg.ad("El usuario no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public List<String> listaDocumento(String dato){
        List<String> listaDocumento=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT documento FROM Personas WHERE documento like '"+dato+"%'");
        try {
            while(r.next()){
                listaDocumento.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDocumento;
    }
    public List<String> listaPnombre(String dato){
        List<String> listaPnombre=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT pnombre FROM Personas WHERE pnombre like '"+dato+"%'");
        try {
            while(r.next()){
                listaPnombre.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPnombre;
    }

    public void eliminar() {
        String sql = "DELETE FROM Precios WHERE cb='" + getCb() + "' and fechaini='" + getFechaIni() + "'";
        String m = "Se ha eliminado el precio";
        Msg.msgDB(m, CRUD.DML(sql, m));
    }

    public void insertar() {
        //System.err.println(creaO());
        CRUD.insert(creaO());
    }

    public void actualizar() {
        CRUD.update(creaO(), "cb='" + getCb() + "' and fechaini='" + getFechaIni() + "'");
    }

    private ArrayList<Object> creaO() {
        ArrayList<Object> o = new ArrayList<>();
        o.add(this);
        return o;
    }

    public List<Precios> getPrecios() {
        return precios;
    }

    public void setPrecios(List<Precios> precios) {
        this.precios = precios;
    }
}
