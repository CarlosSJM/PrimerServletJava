/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.modelo.Producto;
import app.persistencia.TiendaDAO;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Web application lifecycle listener.
 *
 * @author Ploo
 */
public class NewServletListenerApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 1- obtener la lista de productos
        List<Producto> lista = new TiendaDAO().listarProductos();
        // 2- obtener el ámbito de la aplicación
        ServletContext ctx = sce.getServletContext();
        // 3- guardar como atributo la lista de productos obtenida de la capa de persistencia
        ctx.setAttribute("todosApp", lista);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
