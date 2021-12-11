package com.FE.modelo;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.xml.registry.infomodel.User;
import org.primefaces.event.FlowEvent;

@Named
@ViewScoped
public class UserWizard implements Serializable {
    
    private Personas persona = new Personas();


    public Personas getPersonas() {
        return persona;
    }

    public void setUser(User user) {
        this.persona = persona;
    }
   
    public void save() {
        FacesMessage msg = new FacesMessage("Registrado correctamente", "Bienvenido: "+persona.getPnombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    

    public String onFlowProcess(FlowEvent event) {
        
            return event.getNewStep();
        }
    }