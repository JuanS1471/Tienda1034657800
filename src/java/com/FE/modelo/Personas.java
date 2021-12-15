/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.modelo;

import java.io.Serializable;


public class Personas implements Serializable {
      private int documento;
      private String pnombre;
      private String snombre;
      private String papellido;
      private String sapellido;
      private String email;
      private String direc;
      private String contra;
      private String user;

    public Personas() {
    }

    public Personas(int documento, String pnombre, String papellido, String direc) {
        this.documento = documento;
        this.pnombre = pnombre;
        this.papellido = papellido;
        this.direc = direc;
    }

    public Personas(int documento, String pnombre, String snombre, String papellido, String sapellido, String email, String direc, String contra, String user) {
        this.documento = documento;
        this.pnombre = pnombre;
        this.snombre = snombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.email = email;
        this.direc = direc;
        this.contra = contra;
        this.user = user;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getPnombre() {
        return pnombre;
    }

    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    

     
    @Override
    public String toString() {
        return "Personas set  documento='" + documento + "', pnombre='" + pnombre + "', snombre='" + snombre + "', papellido='" + papellido + "', sapellido='" + sapellido + "', email='" + email+"', direc='"+ direc+"', user='"+ user+"', contra='"+ contra+"'" ;
    }


   
    
}
