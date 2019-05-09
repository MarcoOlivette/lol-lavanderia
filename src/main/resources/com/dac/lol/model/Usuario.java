/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.lol.model;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marco
 */
@Entity
@Named(value = "tb_usuario")
public class Usuario implements Serializable{
    private long usuario_id; 
    private String usuario_nome;
    private String usuario_email;
    private String usuario_senha ;
    private char usuario_tipo;
    private Funcionario usuario_funcionario = new Funcionario();

    public Usuario() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_senha() {
        return usuario_senha;
    }

    public void setUsuario_senha(String usuario_senha) {
        this.usuario_senha = usuario_senha;
    }

    public char getUsuario_tipo() {
        return usuario_tipo;
    }

    public void setUsuario_tipo(char usuario_tipo) {
        this.usuario_tipo = usuario_tipo;
    }
    
}