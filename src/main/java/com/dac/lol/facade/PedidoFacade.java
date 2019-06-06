/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dac.lol.facade;

import com.dac.lol.dao.CadastroPedidoDAO;
import com.dac.lol.dao.PedidoDAO;
import com.dac.lol.dao.RoupaDAO;
import com.dac.lol.dao.TipoDAO;
import com.dac.lol.dao.UsuarioDAO;
import com.dac.lol.model.Cliente;
import com.dac.lol.model.Pedido;
import com.dac.lol.model.Roupa;
import com.dac.lol.model.Tipo;
import com.dac.lol.model.Usuario;
import java.util.List;

/**
 *
 * @author marco
 */
public class PedidoFacade {

    public static Tipo selectTypeId(Long id) {
        TipoDAO tipoDAO = new TipoDAO();
        return tipoDAO.selectTipo(id);
    }

    public static List<Tipo> selectAllType() {
        TipoDAO tipoDAO = new TipoDAO();
        return tipoDAO.selectListTipo();
    }

    public static Usuario getUser(Long id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.selectUsuario(id);
    }

    public static void addOrder(Pedido pedido, List<Roupa> roupas) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        RoupaDAO roupaDAO = new RoupaDAO();
        CadastroPedidoDAO cadastroPedidoDAO  = new CadastroPedidoDAO();
        
        for(Roupa r : roupas){
            roupaDAO.insertRoupa(r);
        }
        pedidoDAO.insertPedido(pedido);
        
        cadastroPedidoDAO.saveOrder(pedido, roupas);
    }
    
    public static List<Pedido> allOrdersByClients(Cliente cliente){
        CadastroPedidoDAO cadastroPedidoDAO = new CadastroPedidoDAO();
        return cadastroPedidoDAO.listaAllOrders(cliente);
    }
    
    public static List<Pedido> allOrders(){
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.selectListPedido();
    }
    
    public static List<Pedido> allOpenOrders(Cliente client){
        CadastroPedidoDAO cadastroPedidoDAO = new CadastroPedidoDAO();
        return cadastroPedidoDAO.listaAllOpenOrders(client);
    }
    
    public static Pedido selectOrder(long id){
        CadastroPedidoDAO cadastroPedidoDAO = new CadastroPedidoDAO();
        return cadastroPedidoDAO.selectOrder(id);
    }
    
    public static void removeOrder(Long orderId){
        PedidoDAO pedidoDAO = new PedidoDAO();
        RoupaDAO roupaDAO = new RoupaDAO();
        Pedido pedido = pedidoDAO.selectPedido(orderId);
        List<Roupa> roupas = (List<Roupa>) pedido.getRoupas();
        pedidoDAO.updatePedido(pedido);
        pedidoDAO.deletePedido(pedido);
        for(Roupa r : roupas){
            r.getPedidos().clear();
            roupaDAO.updateRoupa(r);
            roupaDAO.deleteRoupa(r);
        }
    }
    
    public static void updateOrder(Pedido pedido){
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.updatePedido(pedido);
    }
}