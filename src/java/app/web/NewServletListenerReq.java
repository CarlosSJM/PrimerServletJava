/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.modelo.Producto;
import app.persistencia.TiendaDAO;
import java.util.List;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Web application lifecycle listener.
 *
 * @author Ploo
 */
public class NewServletListenerReq implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 1- definir un request
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        // 2- obtener la lista de productos
        List<Producto> lista = new TiendaDAO().listarProductos();
        // 3- guardar como atributo la lista de productos obtenida de la capa de persistencia
        request.setAttribute("todosRequest", lista);
    }
}
