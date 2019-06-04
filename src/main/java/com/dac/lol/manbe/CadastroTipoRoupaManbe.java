/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.lol.manbe;

import com.dac.lol.facade.CadastroTipoRoupaFacade;
import com.dac.lol.model.Tipo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "cadastroTipoRoupa")
@RequestScoped
public class CadastroTipoRoupaManbe implements Serializable{
    private Tipo tipo;
    private String error;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    @PostConstruct
    public void init(){
        tipo = new Tipo();
    }
    
    
    public void cadastroTipo(){
        if(!CadastroTipoRoupaFacade.registerType(tipo)){
            error = "Roupa ja cadastrada no sistema";
        }
    }
}
