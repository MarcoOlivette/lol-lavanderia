/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.lol.manbe;

import com.dac.lol.criptografia.MDFive;
import com.dac.lol.facade.CadastroFacade;
import com.dac.lol.model.Cidade;
import com.dac.lol.model.Cliente;
import com.dac.lol.model.Endereco;
import com.dac.lol.model.Estado;
import com.dac.lol.model.Funcionario;
import com.dac.lol.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "alteracaoManbeCliente")
@ViewScoped
public class AlteracaoManbeCliente implements Serializable {

    /**
     * Creates a new instance of AlteracaoManbe
     */
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Estado estadoSelecionado;
    private Cidade cidadeSelecionada;
    private Usuario usuario;
    private Cliente cliente;
    private String email;
    private String cpf;
    private Endereco endereco;
    private String error;

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public Cidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(Cidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Inject
    LoginManbe loginManbe;

    @PostConstruct
    public void init() {

        if (loginManbe.getUsuario().getTipo() != 'c') {
            NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().
                    getNavigationHandler();
            handler.handleNavigation(FacesContext.getCurrentInstance(), null, "funcionario?faces-redirect=true");
            // renderiza a tela
            FacesContext.getCurrentInstance().renderResponse();
            return;
        }

        usuario = new Usuario();
        endereco = new Endereco();

        usuario = loginManbe.getUsuario();
        email = usuario.getEmail();

        listaEstados = CadastroFacade.selectAllState();
        listaCidades = CadastroFacade.selectAllCity();
        cpf = usuario.getCliente().getCpf();
        cliente = usuario.getCliente();
        endereco = cliente.getEndereco();
        cidadeSelecionada = endereco.getCidade();
        estadoSelecionado = cidadeSelecionada.getEstado();

    }

    public void buscarCidades() {
        if (estadoSelecionado != null) {
            listaCidades = CadastroFacade.selectAllCityById(estadoSelecionado.getId());
        }
    }

    public String alterarCliente() {
        String newPass = MDFive.encripta(usuario.getSenha());
        usuario.setSenha(newPass);

        cidadeSelecionada.setEstado(estadoSelecionado);
        endereco.setCidade(cidadeSelecionada);
        cliente.setEndereco(endereco);
        this.error = CadastroFacade.updateClient(usuario, cliente, email, cpf);
        
        if (this.error == null) {
            return "cliente";
        } else {
            return "";
        }
    }
}
