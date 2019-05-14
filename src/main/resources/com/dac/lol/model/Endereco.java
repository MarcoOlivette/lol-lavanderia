/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.lol.model;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author marco
 */
@Entity
@Named(value="tb_endereco")
class Endereco implements Serializable{
    private long endereco_id;
    private int endereco_rua;
    private int endereco_numero;
    private String endereco_complemento;
    private String endereco_bairro;
    private Cidade endereco_cidade;
    private List<Cliente> clientes;

    public Endereco() {
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(long endereco_id) {
        this.endereco_id = endereco_id;
    }

    public int getEndereco_rua() {
        return endereco_rua;
    }

    public void setEndereco_rua(int endereco_rua) {
        this.endereco_rua = endereco_rua;
    }

    public int getEndereco_numero() {
        return endereco_numero;
    }

    public void setEndereco_numero(int endereco_numero) {
        this.endereco_numero = endereco_numero;
    }

    public String getEndereco_complemento() {
        return endereco_complemento;
    }

    public void setEndereco_complemento(String endereco_complemento) {
        this.endereco_complemento = endereco_complemento;
    }

    public String getEndereco_bairro() {
        return endereco_bairro;
    }

    public void setEndereco_bairro(String endereco_bairro) {
        this.endereco_bairro = endereco_bairro;
    }

    @ManyToOne
    @JoinColumn(name="endereco_cidade")
    public Cidade getEndereco_cidade() {
        return endereco_cidade;
    }

    public void setEndereco_cidade(Cidade endereco_cidade) {
        this.endereco_cidade = endereco_cidade;
    }

    @OneToMany(mappedBy = "cliente_endereco")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
