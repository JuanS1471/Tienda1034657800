/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.controlador;

import com.FE.controlador.util.CRUD;
import com.FE.controlador.util.Msg;
import com.FE.modelo.Empleados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class EmpleadosC extends Empleados{
    
    private List<Empleados> empleados;
            
    @PostConstruct
    public void init(){
       llenarTabla();
    }
    public void llenarTabla(){
        empleados =new ArrayList<>();
        
        String sql = "SELECT * FROM Empleados";
        
        ResultSet r = CRUD.select(sql);
        try {
            while (r.next()) {
                Empleados p=new Empleados(r.getInt(1), r.getString(2));
                empleados.add(p);
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
    public void consultaPrimaria() {
        String sql = "SELECT * FROM Empleados WHERE documento='" + getDocumento() + "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setClave(r.getString(2));
                setDocumento(r.getInt(3));
            }else{
                Msg.ad("El usuario no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public void consultaClave() {
        String sql = "SELECT * FROM Empleados WHERE clave='" + getClave()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setClave(r.getString(2));
                setDocumento(r.getInt(3));
            }else{
                Msg.ad("El usuario no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
    public List<String> listaDocumento(String dato){
        List<String> listaDocumento=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT documento FROM Empleados WHERE documento like '"+dato+"%'");
        try {
            while(r.next()){
                listaDocumento.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDocumento;
    }
    public List<String> listaClave(String dato){
        List<String> listaClave=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT clave FROM Empleados WHERE clave like '"+dato+"%'");
        try {
            while(r.next()){
                listaClave.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClave;
    }
    
    
    public void eliminar() {
        String sql = "DELETE FROM Empleados WHERE documento='"+getDocumento()+"'";
        String m = "Se ha eliminado el Empleados";
        Msg.msgDB(m, CRUD.DML(sql, m));
    }

    public void insertar(){
        CRUD.insert(creaO());
    }
    public void actualizar(){
        CRUD.update(creaO(),"documento='"+getDocumento()+"'");
    }
    private ArrayList<Object> creaO (){
        ArrayList<Object> o =new ArrayList<>();
        o.add(this);
        return o;
    }

    public List<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }
    
    
}
