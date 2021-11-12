/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.controlador;

import com.FE.controlador.util.CRUD;
import com.FE.controlador.util.Msg;
import com.FE.modelo.Productos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductosC extends Productos{
     private List<Productos> productos;
            
    @PostConstruct
    public void init(){
        productos=new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        
        ResultSet r = CRUD.select(sql);
        try {
            while (r.next()) {
                Productos p=new Productos(r.getString(1), r.getString(2), r.getString(3));
                productos.add(p);
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
    
  public void consultaPrimaria() {
      String sql = "SELECT * FROM Productos WHERE cb='" + getCb() + "'";
        System.err.println(sql);
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setCb(r.getString(1));
                setNombre(r.getString(2));
                setDescripcion(r.getString(3));
            }else{
                Msg.ad("El producto no se encuentra registrad.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
    }
       public void consultaNombre() {
        String sql = "SELECT * FROM Productos WHERE Nombre='" + getNombre()+ "'";
        
        ResultSet r = CRUD.select(sql);
        try {
            if (r.next()) {
                setCb(r.getString(1));
                setNombre(r.getString(2));
                setDescripcion(r.getString(3));
            }else{
                Msg.ad("El producto no se encuentra registrado.");
            }
        } catch (SQLException ex) {
            Msg.error(ex.getMessage());
        }
        
    }
       
       public List<String> listaCB(String dato){
        List<String> listaCB=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT cb FROM Productos WHERE cb like '"+dato+"%'");
        try {
            while(r.next()){
                listaCB.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCB;
    }
    public List<String> listaNombre(String dato){
        List<String> listaNombre=new ArrayList<>();
        ResultSet r=CRUD.select("SELECT nombre FROM Facturas WHERE nombre like '"+dato+"%'");
        try {
            while(r.next()){
                listaNombre.add(r.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonasC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaNombre;
    }
    
    public void eliminar() {
        String sql = "DELETE FROM Productos WHERE cb='"+getCb()+"'";
        String m = "Se ha eliminado el Producto";
        Msg.msgDB(m, CRUD.DML(sql, m));
    }

     public void insertar(){
        CRUD.insert(creaO());
    }
    public void actualizar(){
        CRUD.update(creaO(),"cb='"+getCb()+"'");
    }
    private ArrayList<Object> creaO (){
        ArrayList<Object> o =new ArrayList<>();
        o.add(this);
        return o;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
}
